package ru.erp.sfsb.service.impl;

import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.mapper.CustomerMapper;
import ru.erp.sfsb.model.Customer;
import ru.erp.sfsb.repository.CustomerRepository;
import ru.erp.sfsb.service.CustomerService;

@Service
public class CustomerServiceImpl extends AbstractService<CustomerDto, Customer, CustomerRepository, CustomerMapper>
        implements CustomerService {

    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository repository) {
        super(mapper, repository, "Customer");
    }
}