package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.mapper.CustomerMapper;
import ru.erp.sfsb.model.Customer;
import ru.erp.sfsb.repository.repos.CustomerEntityRepository;
import ru.erp.sfsb.service.CustomerService;

import static ru.erp.sfsb.LogTag.CUSTOMER_SERVICE;

@Service
@Transactional
public class CustomerServiceImpl extends AbstractService<CustomerDto, Customer, CustomerEntityRepository, CustomerMapper>
        implements CustomerService {

    public CustomerServiceImpl(CustomerMapper mapper, CustomerEntityRepository repository) {
        super(mapper, repository, "Customer", CUSTOMER_SERVICE);
    }
}