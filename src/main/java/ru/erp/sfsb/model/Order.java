package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @ManyToMany(cascade = PERSIST)
    private List<Item> items;
    private String description;
    private String businessProposal;
    private String recipient;
    private Integer applicationNumber;
}