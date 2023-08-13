package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Technology;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    List<Technology> findAllByEmployeeId(Long id);
}