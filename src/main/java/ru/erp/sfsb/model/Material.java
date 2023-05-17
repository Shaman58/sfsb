package ru.erp.sfsb.model;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CompositeType;

import javax.money.MonetaryAmount;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "materials")
public class Material extends AbstractEntity {

    private String materialName;
    private Integer density;
    @AttributeOverride(
            name = "amount",
            column = @Column(name = "price_amount")
    )
    @AttributeOverride(
            name = "currency",
            column = @Column(name = "price_currency")
    )
    @CompositeType(MonetaryAmountType.class)
    @SuppressWarnings("JpaAttributeTypeInspection")
    private MonetaryAmount price;
}