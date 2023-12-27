package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.model.Technology;

@Component
public class TechnologyMapper extends AbstractMapper<Technology, TechnologyDto> {

    private final UserConverter userConverter;

    public TechnologyMapper(UserConverter userConverter) {
        super(Technology.class, TechnologyDto.class);
        this.userConverter = userConverter;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Technology.class, TechnologyDto.class)
                .addMappings(
                        m -> m.using(userConverter.uuidToUser()).map(Technology::getUserUuid, TechnologyDto::setUser)
                ).addMappings(
                        m -> m.using(userConverter.uuidToUser()).map(Technology::getBlocked, TechnologyDto::setBlocked)
                );
        mapper.createTypeMap(TechnologyDto.class, Technology.class)
                .addMappings(
                        m -> m.using(userConverter.userToUuid()).map(TechnologyDto::getUser, Technology::setUserUuid)
                ).addMappings(
                        m -> m.using(userConverter.userToUuid()).map(TechnologyDto::getBlocked, Technology::setBlocked)
                );
    }
}