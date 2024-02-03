package ru.erp.sfsb.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Tooling;

import java.util.List;

public interface ToolingRepository extends JpaRepository<Tooling, Long> {

    List<Tooling> getAllByToolNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String toolName, String description, Pageable pageable);
}