package ru.erp.sfsb.service;

import org.springframework.security.oauth2.jwt.Jwt;
import ru.erp.sfsb.dto.TechnologyDto;

public interface TechnologyService extends Service<TechnologyDto> {

    TechnologyDto update(TechnologyDto technology, Jwt jwt);

    void block(Long id, Jwt jwt);

    void unblock(Long id, Jwt jwt);

    TechnologyDto setCalculated(Long id, Jwt jwt);
}