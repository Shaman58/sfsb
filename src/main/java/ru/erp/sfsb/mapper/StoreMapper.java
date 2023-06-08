package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.StoreDto;
import ru.erp.sfsb.model.Store;

@Component
public class StoreMapper extends AbstractMapper<Store, StoreDto> {

    public StoreMapper() {
        super(Store.class, StoreDto.class);
    }
}