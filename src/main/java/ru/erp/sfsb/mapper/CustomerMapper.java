package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.CustomerDto;
import ru.erp.sfsb.model.Customer;

@Component
public class CustomerMapper extends AbstractMapper<Customer, CustomerDto> {

    CustomerMapper(ModelMapper mapper) {
        super(mapper, Customer.class, CustomerDto.class);
    }
}