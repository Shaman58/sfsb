package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.model.Technology;

@Component
public class TechnologyMapper extends AbstractMapper<Technology, TechnologyDto> {

    private final UserConverter userConverter;

    public TechnologyMapper(ModelMapper mapper, UserConverter userConverter) {
        super(mapper, Technology.class, TechnologyDto.class);
        this.userConverter = userConverter;
    }

    @PostConstruct
    public void setupMapper() {
        getMapper().createTypeMap(Technology.class, TechnologyDto.class)
                .addMappings(
                        m -> m.using(userConverter.uuidToUser()).map(Technology::getUserUuid, TechnologyDto::setUser)
                );
        getMapper().createTypeMap(TechnologyDto.class, Technology.class)
                .addMappings(
                        m -> m.using(userConverter.userToUuid()).map(TechnologyDto::getUser, Technology::setUserUuid)
                );
    }
}