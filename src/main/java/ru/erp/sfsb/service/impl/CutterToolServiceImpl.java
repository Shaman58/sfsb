package ru.erp.sfsb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.repository.CutterToolRepository;
import ru.erp.sfsb.service.CutterToolService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class CutterToolServiceImpl implements CutterToolService {

    private final CutterToolRepository cutterToolRepository;
    private final ModelMapper mapper;

    @Override
    public List<CutterToolDto> getAll() {
        log.info("Looking all Cutter tools in DB");
        return cutterToolRepository.findAll()
                .stream().map(a -> mapper.map(a, CutterToolDto.class)).collect(Collectors.toList());
    }

    @Override
    public CutterToolDto get(Long id) {
        log.info("Looking Cutter tool with id={} in DB", id);
        return mapper.map(cutterToolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no Cutter tool with id=%d in database", id))), CutterToolDto.class);
    }

    @Override
    public CutterToolDto save(CutterToolDto cutterToolDto) {
        log.info("Saving Cutter tool {} into DB", cutterToolDto.getToolName());
        return mapper.map(cutterToolRepository.save(mapper.map(cutterToolDto, CutterTool.class)), CutterToolDto.class);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Cutter tool with id {} in DB", id);
        cutterToolRepository.deleteById(id);
    }
}