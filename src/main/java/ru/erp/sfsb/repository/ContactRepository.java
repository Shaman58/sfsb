package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByCustomerId(Long id);
}