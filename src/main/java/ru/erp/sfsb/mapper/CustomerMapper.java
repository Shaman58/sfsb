package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.model.Customer;

@Component
public class CustomerMapper extends AbstractMapper<Customer, CustomerDto> {

    CustomerMapper() {
        super(Customer.class, CustomerDto.class);
    }
}