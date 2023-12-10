package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.FileDto;
import ru.erp.sfsb.model.File;

@Component
public class FileMapper extends AbstractMapper<File, FileDto> {

    private final ModelMapper mapper;
    @Value("${webclient.file-service.file-url}")
    private String fileExternalUrl;

    FileMapper(ModelMapper mapper) {
        super(File.class, FileDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(File.class, FileDto.class)
                .addMappings(m -> m.skip(FileDto::setLink)).setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFields(File source, FileDto destination) {
        destination.setLink(String.format("%s?filename=%s", fileExternalUrl, source.getLink()));
    }
}