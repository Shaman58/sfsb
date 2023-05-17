package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas")
public class ProductionArea extends AbstractEntity {

    private String areaName;
    @OneToMany(cascade = ALL)
    private List<ProductionUnit> productionUnits;
    @OneToOne(cascade = PERSIST)
    private Store store;
}