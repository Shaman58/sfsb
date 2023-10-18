package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true)
    private List<Item> items;
    private String description;
    private String businessProposal;
    @ManyToOne
    private Customer customer;
    private Integer applicationNumber;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Contact contact;
}