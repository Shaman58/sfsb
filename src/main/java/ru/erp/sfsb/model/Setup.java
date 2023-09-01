package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    private Operation operation;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration setupTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration processTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration interoperativeTime;
    @ManyToMany(cascade = ALL)
    private List<MeasureTool> measureTools;
    @ManyToMany(cascade = ALL)
    private List<AdditionalTool> additionalTools;
    @ManyToMany(cascade = ALL)
    private List<SpecialTool> specialTools;
    @ManyToMany(cascade = ALL)
    private List<Tooling> toolings;
    @ManyToOne
    @JoinColumn(name = "production_unit_id")
    private ProductionUnit productionUnit;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    private boolean isGroup;
    private Integer perTime;
}