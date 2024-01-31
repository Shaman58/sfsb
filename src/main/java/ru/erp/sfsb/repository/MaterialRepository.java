package ru.erp.sfsb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Geometry;
import ru.erp.sfsb.model.Material;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findMaterialByPriceIsOrPriceBefore(MonetaryAmount isMonetaryAmount, MonetaryAmount beforeMonetaryAmount);

    List<Material> findMaterialByUpdatedBeforeOrUpdatedIsNull(LocalDateTime dateTime);

    Page<Material> getAllByMaterialNameContainingIgnoreCaseAndGeometryEqualsOrGost1ContainingIgnoreCaseAndGeometryEqualsOrGost2ContainingIgnoreCaseAndGeometryEquals(
            String materialName, Geometry geometry1, String gost1, Geometry geometry2, String gost2, Geometry geometry3, Pageable pageable);

    Page<Material> getAllByMaterialNameContainingIgnoreCaseOrGost1ContainingIgnoreCaseOrGost2ContainingIgnoreCase(
            String materialName, String gost1, String gost2, Pageable pageable);
}