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
@Table(name = "setups")
public class Setup extends AbstractEntity {

    private Integer setupNumber;
    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration setupTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration processTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration interoperativeTime;
    @OneToMany(mappedBy = "setup", cascade = ALL, orphanRemoval = true)
    private List<MeasureToolItem> measureToolItems;
    @OneToMany(mappedBy = "setup", cascade = ALL, orphanRemoval = true)
    private List<AdditionalTool> additionalTools;
    @OneToMany(mappedBy = "setup", cascade = ALL, orphanRemoval = true)
    private List<SpecialToolItem> specialToolItems;
    @OneToMany(mappedBy = "setup", cascade = ALL, orphanRemoval = true)
    private List<CutterToolItem> cutterToolItems;
    @ManyToMany
    @JoinTable(
            name = "setups_toolings",
            joinColumns = @JoinColumn(name = "setup_id"),
            inverseJoinColumns = @JoinColumn(name = "tooling_id"))
    private List<Tooling> toolings;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    private boolean isGroup;
    private boolean isAggregate;
    private Integer perTime;
    private boolean isCooperate;
    private String text;
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
    private MonetaryAmount cooperatePrice;
    private String additionalComments;
}