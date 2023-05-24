package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.mapper.SetupMapper;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.SetupRepository;
import ru.erp.sfsb.service.*;

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

    @Override
    @Transactional
    public SetupDto save(SetupDto setupDto) {
        log.info("Saving Setup into DB");
        setupDto.setCutterToolDtoList(setupDto.getCutterToolDtoList().stream().map(
                e -> cutterToolService.get(e.getId())
        ).toList());
        setupDto.setToolingDtoList(setupDto.getToolingDtoList().stream().map(
                e -> toolingService.get(e.getId())
        ).toList());
        setupDto.setMeasureToolDtoList(setupDto.getMeasureToolDtoList().stream().map(
                e -> measureToolService.get(e.getId())
        ).toList());
        setupDto.setSpecialToolDtoList(setupDto.getSpecialToolDtoList().stream().map(
                e -> specialToolService.get(e.getId())
        ).toList());
        setupDto.setAdditionalToolDtoList(setupDto.getAdditionalToolDtoList().stream().map(
                e -> additionalToolService.get(e.getId())
        ).toList());
        setupDto.setProductionUnitDto(productionUnitService.get(setupDto.getProductionUnitDto().getId()));
        return mapper.toDto(repository.save(mapper.toEntity(setupDto)));
    }
}