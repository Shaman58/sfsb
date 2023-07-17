package ru.erp.sfsb.model;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CompositeType;

import javax.money.MonetaryAmount;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
public class ProductionUnit extends AbstractEntity {

    private String unitName;
    private Integer unitNumber;
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
    @ManyToOne
    @JoinColumn(name = "production_area_id")
    private ProductionArea productionArea;
    @AttributeOverride(
            name = "amount",
            column = @Column(name = "payment_amount")
    )
    @AttributeOverride(
            name = "currency",
            column = @Column(name = "payment_currency")
    )
    @CompositeType(MonetaryAmountType.class)
    @SuppressWarnings("JpaAttributeTypeInspection")
    private MonetaryAmount paymentPerHour;
}