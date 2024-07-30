package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.mapper.OperationMapper;
import ru.erp.sfsb.model.Operation;
import ru.erp.sfsb.repository.repos.OperationEntityRepository;
import ru.erp.sfsb.service.OperationService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.erp.sfsb.LogTag.OPERATION_SERVICE;

@Service
@Slf4j
@Transactional
public class OperationServiceImpl extends AbstractService<OperationDto, Operation, OperationEntityRepository, OperationMapper>
        implements OperationService {

    public OperationServiceImpl(OperationMapper mapper, OperationEntityRepository repository) {
        super(mapper, repository, "Operation", OPERATION_SERVICE);
    }

    @Override
    public List<OperationDto> getAll() {
        log.info("[{}] Поиск всех сущностей типа Operation в БД", getLogTag());
        var entities = repository.findAllByIdGreaterThan2();
        return entities.stream().map(mapper::toDto).collect(toList());
    }

    @Override
    public OperationDto getSetupPrice() {
        log.info("[{}] Поиск цены наладки in БД", getLogTag());
        return mapper.toDto(repository.findById(1L).orElseThrow(
                () -> getEntityWithIdNotFoundException(1L)));
    }

    @Override
    public OperationDto updateSetupPrice(OperationDto price) {
        log.info("[{}] Сохранить цену наладки в БД", getLogTag());
        checkExistById(1L);
        price.setId(1L);
        return mapper.toDto(repository.save(mapper.toEntity(price)));
    }

    @Override
    public OperationDto getTechnologistPrice() {
        log.info("[{}] Поиск цены технолога in БД", getLogTag());
        return mapper.toDto(repository.findById(2L).orElseThrow(
                () -> getEntityWithIdNotFoundException(2L)));
    }

    @Override
    public OperationDto updateTechnologistPrice(OperationDto price) {
        log.info("[{}] Сохранить цену технолога в БД", getLogTag());
        checkExistById(2L);
        price.setId(2L);
        return mapper.toDto(repository.save(mapper.toEntity(price)));
    }
}