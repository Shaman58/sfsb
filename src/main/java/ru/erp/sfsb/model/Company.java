package ru.erp.sfsb.model;

import jakarta.persistence.*;
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
@Table(name = "companies")
public class Company extends AbstractEntity {

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
    @OneToOne
    @JoinColumn
    private Employee director;
    @OneToMany(mappedBy = "company", cascade = MERGE)
    private List<Department> departments;
}