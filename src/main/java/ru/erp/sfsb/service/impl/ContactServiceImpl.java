package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ContactDto;
import ru.erp.sfsb.mapper.ContactMapper;
import ru.erp.sfsb.model.Contact;
import ru.erp.sfsb.repository.ContactRepository;
import ru.erp.sfsb.service.ContactService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ContactServiceImpl extends AbstractService<ContactDto, Contact, ContactRepository, ContactMapper>
        implements ContactService {

    public ContactServiceImpl(ContactMapper mapper, ContactRepository repository) {
        super(mapper, repository, "Contact");
    }

    @Override
    public List<ContactDto> getCustomerContacts(Long id) {
        log.info("Looking all contacts with customer id = {} in DB", id);
        return repository.findAllByCustomerId(id).stream().map(mapper::toDto).collect(toList());
    }
}