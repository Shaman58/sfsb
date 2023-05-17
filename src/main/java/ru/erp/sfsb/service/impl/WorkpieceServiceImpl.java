package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.model.Workpiece;
import ru.erp.sfsb.repository.WorkpieceRepository;
import ru.erp.sfsb.service.WorkpieceService;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Slf4j
@Service
public class WorkpieceServiceImpl implements WorkpieceService {

    private final WorkpieceRepository workpieceRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public List<WorkpieceDto> getAll() {
        log.info("Looking all workpieces in DB");
        return workpieceRepository.findAll().stream().map(p -> mapper.map(p, WorkpieceDto.class)).collect(toList());
    }

    @Override
    @Transactional
    public WorkpieceDto get(Long id) {
        log.info("Looking workpiece with id={} in DB", id);
        var workpiece = workpieceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no workpiece with id=%d in database", id)));
        return mapper.map(workpiece, WorkpieceDto.class);
    }

    @Override
    @Transactional
    public WorkpieceDto save(WorkpieceDto workpieceDto) {
        log.info("Saving workpiece into DB");
        return mapper.map(workpieceRepository.save(mapper.map(workpieceDto, Workpiece.class)), WorkpieceDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting workpiece with id {} in DB", id);
        workpieceRepository.deleteById(id);
    }
}