package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.ProductionAreaDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.ProductionAreaMapper;
import ru.erp.sfsb.model.ProductionArea;
import ru.erp.sfsb.repository.ProductionAreaRepository;
import ru.erp.sfsb.service.ProductionAreaService;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
public class ProductionAreaServiceImpl extends AbstractService<ProductionAreaDto, ProductionArea, ProductionAreaRepository, ProductionAreaMapper>
        implements ProductionAreaService {

    private final ProductionAreaMapper mapper;
    private final ProductionAreaRepository repository;

    public ProductionAreaServiceImpl(ProductionAreaMapper mapper, ProductionAreaRepository repository) {
        super(mapper, repository, "Production area");
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public ProductionAreaDto get(@PathVariable Long id) {
        log.info("Looking Area with id={} in DB", id);
        return mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Area with id=%d in database", id)))));
    }

    @Override
    @Transactional
    public List<ProductionAreaDto> getAllByCompanyId(Long id) {
        log.info("Looking all Areas by Company Id = {} in DB", id);
        return repository.getProductionAreasByCompanyId(id).stream().map(mapper::toDto).toList();
    }
}