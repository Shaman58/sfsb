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
@Table(name = "materials", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gost_1", "gost_2"})
})
public class Material extends AbstractEntity {

    private String materialName;
    @Column(name = "gost_1")
    private String gost1;
    @Column(name = "gost_2")
    private String gost2;
    @Enumerated(EnumType.STRING)
    private Geometry geometry;
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
    private Integer density;
}