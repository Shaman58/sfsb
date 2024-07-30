package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.MaterialDto;
import ru.erp.sfsb.mapper.MaterialMapper;
import ru.erp.sfsb.model.Geometry;
import ru.erp.sfsb.model.Material;
import ru.erp.sfsb.repository.repos.MaterialEntityRepository;
import ru.erp.sfsb.service.MaterialService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.erp.sfsb.LogTag.MATERIAL_SERVICE;

@Service
@Transactional
@Slf4j
public class MaterialServiceImpl extends AbstractService<MaterialDto, Material, MaterialEntityRepository, MaterialMapper>
        implements MaterialService {

    public MaterialServiceImpl(MaterialMapper mapper, MaterialEntityRepository repository) {
        super(mapper, repository, "Material", MATERIAL_SERVICE);
    }

    @Override
    public List<MaterialDto> getMaterialWithoutPrice() {
        log.info("[{}] Поиск всех сущностей типа {} без цены в БД", getLogTag(), getEntityName());
        var money = Money.of(0, "RUB");
        return getRepository().findMaterialByPriceIsAndDeletedIsFalseOrPriceBeforeAndDeletedIsFalse(money, money).stream()
                .map(material -> getMapper().toDto(material))
                .toList();
    }

    @Override
    public List<MaterialDto> getMaterialWithExpiredDate() {
        log.info("[{}] Поиск всех сущностей типа {} с истекшей датой в БД", getLogTag(), getEntityName());
        return getRepository().findMaterialByUpdatedBeforeAndDeletedIsFalseOrUpdatedIsNullAndDeletedIsFalse(LocalDateTime.now().minusMonths(1)).stream()
                .map(material -> getMapper().toDto(material))
                .toList();
    }

    @Override
    public MaterialDto save(MaterialDto materialDto) {
        log.info("[{}] Сохранение сущности типа {} в БД", getLogTag(), getEntityName());
        materialDto.setPrice(Money.of(0, "RUB"));
        return getMapper().toDto(getRepository().save(getMapper().toEntity(materialDto)));
    }

    @Override
    public MaterialDto updatePrice(MaterialDto materialDto) {
        log.info("[{}] Обновление цены сущности типа {} с id={}", getLogTag(), getEntityName(), materialDto.getId());
        var material = get(materialDto.getId());
        material.setPrice(materialDto.getPrice());
        return update(materialDto);
    }

    @Override
    public List<MaterialDto> getByFilter(String filter, Geometry geometry, Pageable pageable) {
        log.info("[{}] Поиск всех сущностей типа {} в БД по фильтру {}", getLogTag(), getEntityName(), filter);
        Page<Material> page;
        if (geometry != null) {
            page = getRepository()
                    .getAllByMaterialNameContainingIgnoreCaseAndGeometryEqualsAndDeletedIsFalseOrGost1ContainingIgnoreCaseAndGeometryEqualsAndDeletedIsFalseOrGost2ContainingIgnoreCaseAndGeometryEqualsAndDeletedIsFalse(
                            filter, geometry, filter, geometry, filter, geometry, pageable);
        } else {
            page = getRepository()
                    .getAllByMaterialNameContainingIgnoreCaseAndDeletedIsFalseOrGost1ContainingIgnoreCaseAndDeletedIsFalseOrGost2ContainingIgnoreCaseAndDeletedIsFalse(
                            filter, filter, filter, pageable);
        }
        return getMapper().toDto(page.getContent());
    }
}