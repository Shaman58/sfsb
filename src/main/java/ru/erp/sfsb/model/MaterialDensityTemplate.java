package ru.erp.sfsb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "material_density_templates")
public class MaterialDensityTemplate extends AbstractEntity {

    @Column(unique = true)
    private String materialTypeName;
    private Integer density;
}