package ru.erp.sfsb.model;

import jakarta.persistence.*;
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
    @Column(unique = true)
    private Integer applicationNumber;
    private String userUuid;
    @ManyToMany
    @JoinTable(
            name = "order_files",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<File> files;
}