package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workpieces")
public class Workpiece extends AbstractEntity {

    @ManyToOne
    private Material material;
    private String geometry;
    private Integer geom1;
    private Integer geom2;
    private Integer geom3;
}