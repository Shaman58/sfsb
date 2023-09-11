package ru.erp.sfsb.model;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CompositeType;

import javax.money.MonetaryAmount;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tool_type", discriminatorType = STRING)
@Table(name = "tools")
public abstract class Tool extends AbstractEntity {

    private String toolName;
    private String description;
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