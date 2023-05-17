package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "created", updatable = false)
    private LocalDateTime created;
    @Column(name = "updated", insertable = false)
    private LocalDateTime updated;

    @PrePersist
    private void toCreate() {
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    private void toUpdate() {
        setUpdated(LocalDateTime.now());
    }
}