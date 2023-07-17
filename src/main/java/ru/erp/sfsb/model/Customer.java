package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {

    private String companyName;
    private String address;
    private String inn;
    private String kpp;
    private String ogrn;
    private String paymentAccount;
    private String bank;
    private String bik;
    private String correspondentAccount;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "customer", cascade = MERGE)
    private List<Contact> contacts;
}