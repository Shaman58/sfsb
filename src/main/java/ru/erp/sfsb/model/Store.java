package ru.erp.sfsb.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;
    @ElementCollection
    @JoinTable(name = "store_workpiece_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "workpiece_id")
    @Column(name = "amount")
    private Map<Workpiece, Integer> workpieces;
    @ElementCollection
    @JoinTable(name = "store_cutter_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cutter_id")
    @Column(name = "amount")
    private Map<CutterTool, Integer> cuttingTools;
    @ElementCollection
    @JoinTable(name = "store_measurer_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "measurer_id")
    @Column(name = "amount")
    private Map<MeasureTool, Integer> measuringTools;
}