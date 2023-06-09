package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.TechnologyMapper;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.repository.TechnologyRepository;
import ru.erp.sfsb.service.EmployeeService;
import ru.erp.sfsb.service.SetupService;
import ru.erp.sfsb.service.TechnologyService;
import ru.erp.sfsb.service.WorkpieceService;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class TechnologyServiceImpl extends AbstractService<TechnologyDto, Technology, TechnologyRepository, TechnologyMapper>
        implements TechnologyService {
    private final TechnologyMapper mapper;
    private final TechnologyRepository repository;
    private final WorkpieceService workpieceService;
    private final EmployeeService employeeService;
    private final SetupService setupService;

    public TechnologyServiceImpl(TechnologyMapper mapper, TechnologyRepository repository, WorkpieceService workpieceService,
                                 EmployeeService employeeService, SetupService setupService) {
        super(mapper, repository, "Technology");
        this.mapper = mapper;
        this.repository = repository;
        this.workpieceService = workpieceService;
        this.employeeService = employeeService;
        this.setupService = setupService;
    }

    @Override
    @Transactional
    public List<TechnologyDto> getAll() {
        log.info("Looking all Technologies in DB");
        var technologies = repository.findAll();
        return technologies.stream()
                .map(mapper::toDto)
                .peek(t -> t.getSetups()
                        .forEach(s -> s.getProductionUnit()
                                .getProductionArea()
                                .setStore(null)))
                .collect(toList());
    }

    @Override
    @Transactional
    public TechnologyDto get(@PathVariable Long id) {
        log.info("Looking Technology with id={} in DB", id);
        var technology = mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Technology with id=%d in database", id)))));
        technology.getSetups()
                .forEach(setup -> setup.getProductionUnit()
                        .getProductionArea()
                        .setStore(null));
        return technology;
    }

    @Override
    @Transactional
    public TechnologyDto save(TechnologyDto technologyDto) {
        log.info("Saving Technology into DB");
        technologyDto.setSetups(technologyDto.getSetups().stream().map(e -> setupService.get(e.getId())).collect(toList()));
        technologyDto.setWorkpiece(workpieceService.get(technologyDto.getWorkpiece().getId()));
        technologyDto.setEmployee(employeeService.get(technologyDto.getEmployee().getId()));
        var technology = mapper.toDto(repository.save(mapper.toEntity(technologyDto)));
        technology.getSetups()
                .forEach(setup -> setup.getProductionUnit()
                        .getProductionArea()
                        .setStore(null));
        return technology;
    }
}