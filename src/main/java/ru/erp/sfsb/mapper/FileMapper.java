package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.model.File;

@Component
public class FileMapper extends AbstractMapper<File, FileDto> {

    private final UserConverter userConverter;
    @Value("${webclient.file-service.file-url}")
    private String fileExternalUrl;

    FileMapper(UserConverter userConverter) {
        super(File.class, FileDto.class);
        this.userConverter = userConverter;
    }

    @PostConstruct
    public void setupMapper() {
        Converter<String, String> lincToFullLink = c -> String.format("%s?filename=%s", fileExternalUrl, c.getSource());
        mapper.createTypeMap(File.class, FileDto.class)
                .addMappings(
                        m -> m.using(lincToFullLink).map(File::getLink, FileDto::setLink)
                )
                .addMappings(
                        m -> m.using(userConverter.uuidToUser()).map(File::getUserUuid, FileDto::setUser)
                );
        mapper.createTypeMap(FileDto.class, File.class)
                .addMappings(
                        m -> m.using(userConverter.userToUuid()).map(FileDto::getUser, File::setUserUuid)
                );
    }
}