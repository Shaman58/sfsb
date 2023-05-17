package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item extends AbstractEntity {

    @ManyToOne
    private Technology technology;
    private boolean isCustomerMaterial;
    private Integer quantity;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration estimatedDuration;
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private Duration actualDuration;
    private boolean isAccepted;
}