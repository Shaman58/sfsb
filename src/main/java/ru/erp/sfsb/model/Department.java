package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department extends AbstractEntity {

    private String departmentName;
    @OneToMany(mappedBy = "department", cascade = ALL)
    private List<Employee> employees;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}