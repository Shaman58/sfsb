package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.model.Material;
import ru.erp.sfsb.repository.MaterialRepository;
import ru.erp.sfsb.service.MaterialService;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.DEFAULT_DIRECTION;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public List<MaterialDto> getAll() {
        log.info("Looking all materials in DB");
        var materials = materialRepository.findAll(Sort.by(DEFAULT_DIRECTION, "materialName"));
        return materials.stream().map(m -> mapper.map(m, MaterialDto.class)).collect(toList());
    }

    @Override
    @Transactional
    public MaterialDto get(@PathVariable Long id) {
        log.info("Looking material with id={} in DB", id);
        return mapper.map((materialRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no material with id=%d in database", id)))), MaterialDto.class);
    }

    @Override
    @Transactional
    public MaterialDto save(MaterialDto materialDto) {
        log.info("Saving material {} into DB", materialDto.getMaterialName());
        return mapper.map(materialRepository.save(mapper.map(materialDto, Material.class)), MaterialDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting material with id {} in DB", id);
        materialRepository.deleteById(id);
    }
}