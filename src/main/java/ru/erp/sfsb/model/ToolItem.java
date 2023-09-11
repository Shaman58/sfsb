package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "tool_type", discriminatorType = STRING)
@Table(name = "tool_items")
public abstract class ToolItem extends AbstractEntity {

    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "setup_id")
    private Setup setup;
}