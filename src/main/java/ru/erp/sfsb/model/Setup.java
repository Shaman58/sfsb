package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "setups")
public class Setup extends AbstractEntity {

    private Integer setupNumber;
    private String setupName;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration setupTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration processTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration interoperativeTime;
    @ManyToMany
    private List<CutterTool> cutterTools;
    @ManyToMany
    private List<MeasureTool> measureTools;
    @ManyToMany
    private List<AdditionalTool> additionalTools;
    @ManyToMany
    private List<SpecialTool> specialTools;
    @ManyToMany
    private List<Tooling> toolings;
    @ManyToOne
    private ProductionUnit productionUnit;
}