package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additionals")
public class AdditionalTool extends AbstractEntity {

    private String toolName;
    @ManyToOne
    private Workpiece workpiece;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration processTime;
}