package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ContactDto;
import ru.erp.sfsb.model.Contact;

@Component
public class ContactMapper extends AbstractMapper<Contact, ContactDto> {

    ContactMapper() {
        super(Contact.class, ContactDto.class);
    }
}