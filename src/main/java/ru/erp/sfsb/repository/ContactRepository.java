package ru.erp.sfsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}