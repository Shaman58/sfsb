package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    private Employee employee;
    @OneToMany(mappedBy = "technology", cascade = ALL)
    private List<Setup> setups;
    private Integer quantityOfDefectiveParts;
    private Integer quantityOfSetUpParts;
    private Integer quantityOfPartsFromWorkpiece;
    @ManyToOne(cascade = ALL)
    private Workpiece workpiece;
    private boolean isComputed;
}