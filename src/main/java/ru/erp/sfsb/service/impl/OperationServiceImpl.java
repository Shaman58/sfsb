package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.OperationMapper;
import ru.erp.sfsb.model.Operation;
import ru.erp.sfsb.repository.OperationRepository;
import ru.erp.sfsb.service.OperationService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
public class OperationServiceImpl extends AbstractService<OperationDto, Operation, OperationRepository, OperationMapper>
        implements OperationService {

    public OperationServiceImpl(OperationMapper mapper, OperationRepository repository) {
        super(mapper, repository, "Operation");
    }

    @Override
    public List<OperationDto> getAll() {
        log.info("Looking all operations in DB");
        var entities = repository.findAllByIdGreaterThan2();
        return entities.stream().map(mapper::toDto).collect(toList());
    }

    @Override
    public OperationDto getSetupPrice() {
        log.info("Looking setup price in DB");
        return mapper.toDto(repository.findById(1L).orElseThrow(
                () -> new EntityNotFoundException("There is no setup price in DB")));
    }

    @Override
    public OperationDto updateSetupPrice(OperationDto price) {
        log.info("Saving setup price into DB");
        checkExistById(1L);
        price.setId(1L);
        return mapper.toDto(repository.save(mapper.toEntity(price)));
    }

    @Override
    public OperationDto getTechnologyPrice() {
        log.info("Looking technology price in DB");
        return mapper.toDto(repository.findById(2L).orElseThrow(
                () -> new EntityNotFoundException("There is no technology price in DB")));
    }

    @Override
    public OperationDto updateTechnologyPrice(OperationDto price) {
        log.info("Saving technology price into DB");
        checkExistById(2L);
        price.setId(2L);
        return mapper.toDto(repository.save(mapper.toEntity(price)));
    }
}