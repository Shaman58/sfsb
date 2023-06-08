package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
    private Store store;
}