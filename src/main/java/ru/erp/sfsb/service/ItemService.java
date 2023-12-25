package ru.erp.sfsb.service;

import org.springframework.security.oauth2.jwt.Jwt;
import ru.erp.sfsb.dto.ItemDto;

import java.util.List;

public interface ItemService extends Service<ItemDto> {

    List<ItemDto> getAllByTechnologyUser(Jwt jwt);
}