package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "setups")
public class Setup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Duration setupTime;
    private Duration processTime;
    private Duration interoperativeTime;
    @ManyToMany
    private List<CutterTool> cutterTools;
    @ManyToMany
    private List<MeasureTool> measureTools;
    @ManyToMany
    private List<AdditionalTool> additionalTools;
    @ManyToMany
    private List<SpecialTool> specialTools;
    @ManyToOne
    private ProductionUnit productionUnit;
}