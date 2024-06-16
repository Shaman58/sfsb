package ru.erp.sfsb.repository.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.erp.sfsb.model.Geometry;
import ru.erp.sfsb.model.Material;
import ru.erp.sfsb.repository.EntityRepository;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MaterialEntityRepository extends EntityRepository<Material> {
    List<Material> findMaterialByPriceIsAndDeletedIsFalseOrPriceBeforeAndDeletedIsFalse(MonetaryAmount isMonetaryAmount, MonetaryAmount beforeMonetaryAmount);

    List<Material> findMaterialByUpdatedBeforeAndDeletedIsFalseOrUpdatedIsNullAndDeletedIsFalse(LocalDateTime dateTime);

    Page<Material> getAllByMaterialNameContainingIgnoreCaseAndGeometryEqualsAndDeletedIsFalseOrGost1ContainingIgnoreCaseAndGeometryEqualsAndDeletedIsFalseOrGost2ContainingIgnoreCaseAndGeometryEqualsAndDeletedIsFalse(
            String materialName, Geometry geometry1, String gost1, Geometry geometry2, String gost2, Geometry geometry3, Pageable pageable);

    Page<Material> getAllByMaterialNameContainingIgnoreCaseAndDeletedIsFalseOrGost1ContainingIgnoreCaseAndDeletedIsFalseOrGost2ContainingIgnoreCaseAndDeletedIsFalse(
            String materialName, String gost1, String gost2, Pageable pageable);
}