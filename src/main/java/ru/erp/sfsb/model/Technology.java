package ru.erp.sfsb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String drawingNumber;
    private String drawingName;
    @ManyToOne
    private Material material;
    @ManyToOne
    private Employee employee;
    @OneToMany(cascade = ALL)
    @JoinTable(name = "technology_setup_mapping",
            joinColumns = {@JoinColumn(name = "technology_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "setup_id", referencedColumnName = "id")})
    @Column(name = "setup_number")
    private Map<Integer, Setup> setups;
    private Integer quantityOfDefectiveParts;
    private Integer quantityOfSetUpParts;
    private Integer quantityOfPartsFromWorkpiece;
    @ManyToOne
    private Workpiece workpiece;
    private LocalDateTime dateTime;
}