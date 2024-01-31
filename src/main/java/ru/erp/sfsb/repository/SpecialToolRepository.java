package ru.erp.sfsb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.SpecialTool;

import java.util.List;

public interface SpecialToolRepository extends JpaRepository<SpecialTool, Long> {

    List<SpecialTool> getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String toolName, String description, Pageable pageable);
}