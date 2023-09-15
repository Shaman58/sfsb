package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MeasureToolItem extends ToolItem {

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "tool_id")
    private MeasureTool tool;
}