package ru.erp.sfsb.service.impl;

import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.mapper.MaterialMapper;
import ru.erp.sfsb.model.Material;
import ru.erp.sfsb.repository.MaterialRepository;
import ru.erp.sfsb.service.MaterialService;

@Service
public class MaterialServiceImpl extends AbstractService<MaterialDto, Material, MaterialRepository, MaterialMapper>
        implements MaterialService {

    public MaterialServiceImpl(MaterialMapper mapper, MaterialRepository repository) {
        super(mapper, repository, "Material");
    }
}