package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "contacts")
public class Contact extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String position;
    private String phoneNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}