package ru.erp.sfsb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.model.AdditionalTool;
import ru.erp.sfsb.repository.AdditionalToolRepository;
import ru.erp.sfsb.service.AdditionalToolService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdditionalToolServiceImpl implements AdditionalToolService {

    private final ModelMapper mapper;
    private final AdditionalToolRepository additionalToolRepository;

    @Override
    public List<AdditionalToolDto> getAll() {
        log.info("Looking all Additional tools in DB");
        return additionalToolRepository.findAll()
                .stream().map(a -> mapper.map(a, AdditionalToolDto.class)).collect(Collectors.toList());
    }

    @Override
    public AdditionalToolDto get(Long id) {
        log.info("Looking Additional tool with id={} in DB", id);
        return mapper.map(additionalToolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no Additional tool with id=%d in database", id))), AdditionalToolDto.class);
    }

    @Override
    public AdditionalToolDto save(AdditionalToolDto additionalToolDto) {
        log.info("Saving Additional tool {} into DB", additionalToolDto.getToolName());
        return mapper.map(additionalToolRepository.save(mapper.map(additionalToolDto, AdditionalTool.class)), AdditionalToolDto.class);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Additional tool with id {} in DB", id);
        additionalToolRepository.deleteById(id);
    }
}