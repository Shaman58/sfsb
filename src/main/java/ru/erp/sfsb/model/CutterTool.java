package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javamoney.moneta.Money;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cutters")
public class CutterTool {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String toolName;
    private String description;
    private Money price;
}