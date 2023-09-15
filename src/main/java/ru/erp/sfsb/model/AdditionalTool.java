package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additionals")
public class AdditionalTool extends AbstractEntity {

    private String toolName;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "workpiece_id")
    private Workpiece workpiece;
    @ManyToOne
    @JoinColumn(name = "setup_id")
    private Setup setup;
    private Integer amount;
}