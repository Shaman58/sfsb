package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.ContactDto;

import java.util.List;

public interface ContactService extends Service<ContactDto> {

    List<ContactDto> getCustomerContacts(Long id);
}