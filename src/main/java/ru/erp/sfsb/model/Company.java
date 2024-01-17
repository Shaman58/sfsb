package ru.erp.sfsb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.REMOVE;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company extends Organization {

    @ManyToOne(cascade = REMOVE)
    @JoinColumn
    private File logo;
}