package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.mapper.MaterialMapper;
import ru.erp.sfsb.model.Material;
import ru.erp.sfsb.repository.MaterialRepository;
import ru.erp.sfsb.service.MaterialService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@Slf4j
public class MaterialServiceImpl extends AbstractService<MaterialDto, Material, MaterialRepository, MaterialMapper>
        implements MaterialService {

    public MaterialServiceImpl(MaterialMapper mapper, MaterialRepository repository) {
        super(mapper, repository, "Material");
    }

    @Override
    public List<MaterialDto> getMaterialWithoutPrice() {
        var money = Money.of(0, "RUB");
        return repository.findMaterialByPriceIsOrPriceBefore(money, money).stream()
                .map(material -> mapper.toDto(material))
                .toList();
    }

    @Override
    public List<MaterialDto> getMaterialWithExpiredDate() {
        return repository.findMaterialByUpdatedBefore(LocalDateTime.now().minus(1, ChronoUnit.MONTHS)).stream()
                .map(material -> mapper.toDto(material))
                .toList();
    }

    @Override
    public MaterialDto save(MaterialDto materialDto) {
        log.info("Saving Material into DB");
        materialDto.setPrice(Money.of(0, "RUB"));
        return mapper.toDto(repository.save(mapper.toEntity(materialDto)));
    }

    @Override
    public MaterialDto updatePrice(MaterialDto materialDto) {
        log.info("Update price in material with id={}", materialDto.getId());
        var material = get(materialDto.getId());
        material.setPrice(materialDto.getPrice());
        return save(materialDto);
    }
}