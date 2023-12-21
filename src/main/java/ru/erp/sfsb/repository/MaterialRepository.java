package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Material;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findMaterialByPriceIsOrPriceBefore(MonetaryAmount isMonetaryAmount, MonetaryAmount beforeMonetaryAmount);

    List<Material> findMaterialByUpdatedBefore(LocalDateTime dateTime);
}