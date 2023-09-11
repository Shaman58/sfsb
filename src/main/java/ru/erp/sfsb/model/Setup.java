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
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "operation_id")
    private Operation operation;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration setupTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration processTime;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration interoperativeTime;
    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "setups_measure_tools",
            joinColumns = @JoinColumn(name = "setup_id"),
            inverseJoinColumns = @JoinColumn(name = "measure_tool_id"))
    private List<MeasureTool> measureTools;


    @OneToMany(cascade = ALL, mappedBy = "setup")
    private List<AdditionalTool> additionalTools;


    @OneToMany(cascade = ALL, mappedBy = "setup")
    private List<SpecialToolItem> specialToolItems;
    @OneToMany(cascade = ALL, mappedBy = "setup")
    private List<CutterToolItem> cutterToolItems;
    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "setups_toolings",
            joinColumns = @JoinColumn(name = "setup_id"),
            inverseJoinColumns = @JoinColumn(name = "tooling_id"))
    private List<Tooling> toolings;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;
    private boolean isGroup;
    private Integer perTime;
    private boolean isCooperate;
}