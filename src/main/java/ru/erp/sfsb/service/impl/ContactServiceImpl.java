package ru.erp.sfsb.service.impl;

import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ContactDto;
import ru.erp.sfsb.mapper.ContactMapper;
import ru.erp.sfsb.model.Contact;
import ru.erp.sfsb.repository.ContactRepository;
import ru.erp.sfsb.service.ContactService;

@Service
public class ContactServiceImpl extends AbstractService<ContactDto, Contact, ContactRepository, ContactMapper>
        implements ContactService {

    public ContactServiceImpl(ContactMapper mapper, ContactRepository repository) {
        super(mapper, repository, "Contact");
    }
}