package ru.erp.sfsb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores")
public class Store extends AbstractEntity {

    private String storeName;
    @ElementCollection
    @JsonManagedReference
    @JoinTable(name = "store_workpiece_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "workpiece_id")
    @Column(name = "amount")
    private Map<Workpiece, Integer> workpieces;
    @ElementCollection
    @JsonManagedReference
    @JoinTable(name = "store_cutter_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cutter_id")
    @Column(name = "amount")
    private Map<CutterTool, Integer> cutterTools;
    @ElementCollection
    @JsonManagedReference
    @JoinTable(name = "store_measurer_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "measurer_id")
    @Column(name = "amount")
    private Map<MeasureTool, Integer> measureTools;
    @ElementCollection
    @JsonManagedReference
    @JoinTable(name = "store_tooling_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "tooling_id")
    @Column(name = "amount")
    private Map<Tooling, Integer> toolings;
    @ElementCollection
    @JsonManagedReference
    @JoinTable(name = "store_special_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "special_id")
    @Column(name = "amount")
    private Map<SpecialTool, Integer> specialTools;
}