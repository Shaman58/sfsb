package ru.erp.sfsb.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract interface Service<D> {

    List<D> getAll();

    List<D> getAll(Pageable pageable);

    D get(Long id);

    D save(D dto);

    D update(D dto);

    void delete(Long id);
}