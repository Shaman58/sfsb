package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
}