package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.model.File;
import ru.erp.sfsb.service.UserService;

@Component
public class FileMapper extends AbstractMapper<File, FileDto> {

    private final UserService userService;
    @Value("${webclient.file-service.file-url}")
    private String fileExternalUrl;

    FileMapper(UserService userService) {
        super(File.class, FileDto.class);
        this.userService = userService;
    }

    @PostConstruct
    public void setupMapper() {
        Converter<String, UserDto> uuidToUser = c -> c.getSource() == null ? null : userService.get(c.getSource());
        Converter<String, String> lincToFullLink = c -> String.format("%s?filename=%s", fileExternalUrl, c.getSource());
        mapper.createTypeMap(File.class, FileDto.class)
                .addMappings(
                        m -> m.using(lincToFullLink).map(File::getLink, FileDto::setLink)
                )
                .addMappings(
                        m -> m.using(uuidToUser).map(File::getUserUuid, FileDto::setUser)
                );
        Converter<UserDto, String> userToUuid = c -> c.getSource().getId();
        mapper.createTypeMap(FileDto.class, File.class)
                .addMappings(
                        m -> m.using(userToUuid).map(FileDto::getUser, File::setUserUuid)
                );
    }
}