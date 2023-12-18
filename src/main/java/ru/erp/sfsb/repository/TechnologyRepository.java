package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}