package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.mapper.SetupMapper;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.SetupRepository;
import ru.erp.sfsb.service.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class SetupServiceImpl extends AbstractService<SetupDto, Setup, SetupRepository, SetupMapper>
        implements SetupService {

    private final CutterToolService cutterToolService;
    private final MeasureToolService measureToolService;
    private final AdditionalToolService additionalToolService;
    private final SpecialToolService specialToolService;
    private final ToolingService toolingService;
    private final ProductionUnitService productionUnitService;

    public SetupServiceImpl(SetupMapper mapper, SetupRepository repository, CutterToolService cutterToolService,
                            MeasureToolService measureToolService, AdditionalToolService additionalToolService, SpecialToolService specialToolService,
                            ToolingService toolingService, ProductionUnitService productionUnitService) {
        super(mapper, repository, "Setup");
        this.cutterToolService = cutterToolService;
        this.additionalToolService = additionalToolService;
        this.toolingService = toolingService;
        this.specialToolService = specialToolService;
        this.measureToolService = measureToolService;
        this.productionUnitService = productionUnitService;
    }

//    @Override
//    @Transactional
//    public SetupDto save(SetupDto setupDto) {
//        log.info("Saving Setup into DB");
//        setupDto.setCutterTools(setupDto.getCutterTools().stream().map(
//                e -> cutterToolService.get(e.getId())
//        ).toList());
//        setupDto.setToolings(setupDto.getToolings().stream().map(
//                e -> toolingService.get(e.getId())
//        ).toList());
//        setupDto.setMeasureTools(setupDto.getMeasureTools().stream().map(
//                e -> measureToolService.get(e.getId())
//        ).toList());
//        setupDto.setSpecialTools(setupDto.getSpecialTools().stream().map(
//                e -> specialToolService.get(e.getId())
//        ).toList());
//        setupDto.setAdditionalTools(setupDto.getAdditionalTools().stream().map(
//                e -> additionalToolService.get(e.getId())
//        ).toList());
//        setupDto.setProductionUnit(productionUnitService.get(setupDto.getProductionUnit().getId()));
//        return mapper.toDto(repository.save(mapper.toEntity(setupDto)));
//    }

    @Override
    @Transactional
    public List<SetupDto> getTechnologySetups(Long id) {
        log.info("Looking all setups of technology id = {} in DB", id);
        return repository.getSetupsByTechnologyId(id).stream().map(setup -> mapper.toDto(setup)).collect(toList());
    }
}