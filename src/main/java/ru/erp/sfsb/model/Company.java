package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;

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
    @OneToMany(cascade = ALL)
    private List<Department> departments;
    @OneToOne(cascade = PERSIST)
    private Employee director;
    private String email;
    @OneToMany(cascade = ALL)
    private List<Order> orders;
    @OneToMany(cascade = ALL)
    private List<ProductionArea> productionAreas;
}