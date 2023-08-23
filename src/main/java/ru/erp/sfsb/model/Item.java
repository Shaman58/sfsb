package ru.erp.sfsb.model;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CompositeType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import javax.money.MonetaryAmount;
import java.time.Duration;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item extends AbstractEntity {

    @ManyToOne
    private Order order;
    @ManyToOne(cascade = ALL)
    private Technology technology;
    private boolean isCustomerMaterial;
    private Integer quantity;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration estimatedDuration;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration actualDuration;
    private boolean isAccepted;
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