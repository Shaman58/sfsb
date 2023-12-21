package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.service.UserService;

@Component
public class TechnologyMapper extends AbstractMapper<Technology, TechnologyDto> {

    private final UserService userService;

    public TechnologyMapper(ModelMapper mapper, UserService userService) {
        super(Technology.class, TechnologyDto.class);
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        Converter<String, UserDto> uuidToUser = c -> userService.get(c.getSource());
        mapper.createTypeMap(Technology.class, TechnologyDto.class)
                .addMappings(
                        m -> m.using(uuidToUser).map(Technology::getUserUuid, TechnologyDto::setUser)
                );
        Converter<UserDto, String> userToUuid = c -> c.getSource().getId();
        mapper.createTypeMap(TechnologyDto.class, Technology.class)
                .addMappings(
                        m -> m.using(userToUuid).map(TechnologyDto::getUser, Technology::setUserUuid)
                );
    }

//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Technology.class, TechnologyDto.class)
//                .addMappings(m -> m.skip(TechnologyDto::setUser))
//                .setPostConverter(toDtoConverter());
//        mapper.createTypeMap(TechnologyDto.class, Technology.class)
//                .addMappings(m -> m.skip(Technology::setUserUuid))
//                .setPostConverter(toEntityConverter());
//    }

//    @Override
//    void mapSpecificFields(Technology source, TechnologyDto destination) {
//        if (source.getUserUuid() == null) {
//            destination.setUser(null);
//        } else {
//            destination.setUser(userService.get(source.getUserUuid()));
//        }
//    }
//
//    @Override
//    void mapSpecificFields(TechnologyDto source, Technology destination) {
//        destination.setUserUuid(source.getUser().getId());
//    }
}