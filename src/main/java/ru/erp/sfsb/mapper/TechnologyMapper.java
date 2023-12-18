package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.service.UserService;

@Component
public class TechnologyMapper extends AbstractMapper<Technology, TechnologyDto> {

    private final UserService userService;

    public TechnologyMapper(UserService userService) {
        super(Technology.class, TechnologyDto.class);
        this.userService = userService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Technology.class, TechnologyDto.class)
                .addMappings(m -> m.skip(TechnologyDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TechnologyDto.class, Technology.class)
                .addMappings(m -> m.skip(Technology::setUserUuid)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Technology source, TechnologyDto destination) {
        destination.setUser(userService.get(source.getUserUuid()));
    }

    @Override
    void mapSpecificFields(TechnologyDto source, Technology destination) {
        destination.setUserUuid(source.getUser().getId());
    }
}