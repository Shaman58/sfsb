package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.mapper.CustomerMapper;
import ru.erp.sfsb.model.Customer;
import ru.erp.sfsb.repository.CustomerRepository;
import ru.erp.sfsb.service.CustomerService;

@Service
@Slf4j
public class CustomerServiceImpl extends AbstractService<CustomerDto, Customer, CustomerRepository, CustomerMapper>
        implements CustomerService {

    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository repository) {
        super(mapper, repository, "Customer");
    }
}