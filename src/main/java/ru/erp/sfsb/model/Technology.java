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
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technologies")
public class Technology extends AbstractEntity {

    private String drawingNumber;
    private String drawingName;
    private String userUuid;
    private Integer quantityOfDefectiveParts;
    private Integer quantityOfSetUpParts;
    private Integer quantityOfPartsFromWorkpiece;
    @ManyToOne(cascade = ALL)
    private Workpiece workpiece;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration technologistTime;
    private boolean isComputed;
    private boolean isAssembly;
    @OneToMany(mappedBy = "technology", cascade = ALL, orphanRemoval = true)
    private List<Setup> setups;
    @AttributeOverride(
            name = "amount",
            column = @Column(name = "outsourced_costs_amount")
    )
    @AttributeOverride(
            name = "currency",
            column = @Column(name = "outsourced_costs_currency")
    )
    @CompositeType(MonetaryAmountType.class)
    @SuppressWarnings("JpaAttributeTypeInspection")
    private MonetaryAmount outsourcedCosts;
    private String outsourcedCostsDescription;
    private String blocked;
}