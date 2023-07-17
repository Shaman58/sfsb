package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String position;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String phoneNumber;
    private String email;
}