package ru.erp.sfsb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import java.util.Map;

import static org.hibernate.annotations.CascadeType.ALL;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

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
    @CollectionTable(name = "store_workpiece_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "workpiece_id")
    @Column(name = "amount")
    @JoinColumn(name = "store_id")
    @OnDelete(action = CASCADE)
    @Cascade(value = ALL)
    private Map<Workpiece, Integer> workpieces;
    @ElementCollection
    @JsonManagedReference
    @CollectionTable(name = "store_cutter_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "cutter_id")
    @Column(name = "amount")
    @JoinColumn(name = "store_id")
    @OnDelete(action = CASCADE)
    @Cascade(value = ALL)
    private Map<CutterTool, Integer> cutterTools;
    @ElementCollection
    @JsonManagedReference
    @CollectionTable(name = "store_measurer_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "measurer_id")
    @Column(name = "amount")
    @JoinColumn(name = "store_id")
    @OnDelete(action = CASCADE)
    @Cascade(value = ALL)
    private Map<MeasureTool, Integer> measureTools;
    @ElementCollection
    @JsonManagedReference
    @CollectionTable(name = "store_tooling_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "tooling_id")
    @Column(name = "amount")
    @JoinColumn(name = "store_id")
    @OnDelete(action = CASCADE)
    @Cascade(value = ALL)
    private Map<Tooling, Integer> toolings;
    @ElementCollection
    @JsonManagedReference
    @CollectionTable(name = "store_special_mapping",
            joinColumns = {@JoinColumn(name = "store_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "special_id")
    @Column(name = "amount")
    @JoinColumn(name = "store_id")
    @OnDelete(action = CASCADE)
    @Cascade(value = ALL)
    private Map<SpecialTool, Integer> specialTools;
    @ManyToOne
    @JoinColumn
    private ProductionArea productionArea;
}