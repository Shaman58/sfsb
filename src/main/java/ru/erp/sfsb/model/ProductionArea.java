package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas")
public class ProductionArea extends AbstractEntity {

    private String areaName;
    @Transient
    private List<ProductionUnit> productionUnits;
    @OneToOne(cascade = MERGE)
    @JoinColumn
    private Store store;
}