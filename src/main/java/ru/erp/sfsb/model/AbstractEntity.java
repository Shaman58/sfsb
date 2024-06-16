package ru.erp.sfsb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Column(name = "updated", insertable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;
    @Column(name = "deleted")
    private boolean deleted;

    @PrePersist
    private void toCreate() {
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    private void toUpdate() {
        setUpdated(LocalDateTime.now());
    }
}