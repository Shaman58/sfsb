package ru.erp.sfsb.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
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
        Converter<String, String> lincToFullLink = c -> String.format("%s?filename=%s", fileExternalUrl, c.getSource());
        mapper.createTypeMap(File.class, FileDto.class)
                .addMappings(
                        m -> m.using(lincToFullLink).map(File::getLink, FileDto::setLink)
                );
    }
}