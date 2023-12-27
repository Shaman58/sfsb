package ru.erp.sfsb.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.exception.KeycloakOtherException;
import ru.erp.sfsb.service.UserService;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final UserService userService;

    @Bean
    public Converter<String, UserDto> uuidToUser() {
        return c -> c.getSource() == null ? null : getUser(c.getSource());
    }

    @Bean
    public Converter<UserDto, String> userToUuid() {
        return c -> c.getSource() == null ? null : c.getSource().getId();
    }

    private UserDto getUser(String uuid) {
        try {
            return userService.get(uuid);
        } catch (KeycloakOtherException e) {
            return new UserDto();
        }
    }
}
