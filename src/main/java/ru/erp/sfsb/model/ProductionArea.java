package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas")
public class ProductionArea extends AbstractEntity {

    private String areaName;
    @OneToMany(mappedBy = "productionArea", cascade = MERGE)
    private List<ProductionUnit> productionUnits;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "productionArea", cascade = ALL)
    private List<Store> stores;
}