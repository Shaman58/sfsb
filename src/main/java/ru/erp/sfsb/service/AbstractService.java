package ru.erp.sfsb.service;

import java.util.List;

public abstract interface AbstractService<D> {

    List<D> getAll();

    D get(Long id);

    D save(D dto);

    void delete(Long id);
}