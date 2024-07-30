package ru.erp.sfsb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.dto.AbstractDto;

import java.util.List;

public interface Service<D extends AbstractDto> {

    List<D> getAll();

    List<D> getAll(Pageable pageable);

    Page<D> getAllInPage(Pageable pageable);

    D get(Long id);

    D save(D dto);

    D update(D dto);

    void delete(Long id);
}