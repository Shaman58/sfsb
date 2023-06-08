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
    private String okpo;
    private String paymentAccount;
    private String bank;
    private String bik;
    private String correspondentAccount;
    private String phoneNumber;
    @OneToMany(cascade = MERGE)
    private List<Department> departments;
    @OneToOne
    @JoinColumn
    private Employee director;
    private String email;
    @OneToMany(cascade = MERGE)
    private List<Order> orders;
    @OneToMany(cascade = MERGE)
    private List<ProductionArea> productionAreas;
}