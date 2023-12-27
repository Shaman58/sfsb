package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.exception.EntityBlockException;
import ru.erp.sfsb.mapper.TechnologyMapper;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.repository.TechnologyRepository;
import ru.erp.sfsb.service.TechnologyService;
import ru.erp.sfsb.service.UserService;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class TechnologyServiceImpl extends AbstractService<TechnologyDto, Technology, TechnologyRepository, TechnologyMapper>
        implements TechnologyService {

    private final UserService userService;

    public TechnologyServiceImpl(TechnologyMapper mapper, TechnologyRepository repository, UserService userService) {
        super(mapper, repository, "Technology");
        this.userService = userService;
    }

    @Override
    public TechnologyDto update(TechnologyDto dto, Jwt jwt) {
        log.info("Saving Technology into DB");
        var uuid = jwt.getClaim("sub").toString();
        var technology = get(dto.getId());
        checkUpdate(get(dto.getId()), uuid);
        var user = userService.get(uuid);
        dto.setUser(user);
        dto.setBlocked(technology.getBlocked());
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void block(Long id, Jwt jwt) {
        log.info("Block technology with id={}", id);
        var uuid = jwt.getClaim("sub").toString();
        var user = userService.get(uuid);
        var technology = get(id);
        checkBlocked(technology, uuid);
        technology.setBlocked(user);
        save(technology);
        log.info("Blocked");
    }

    @Override
    public void unblock(Long id, Jwt jwt) {
        log.info("Unblock technology with id={}", id);
        var uuid = jwt.getClaim("sub").toString();
        checkExistById(id);
        var technology = get(id);
        checkBlocked(technology, uuid);
        technology.setBlocked(null);
        save(technology);
        log.info("Unblocked");
    }

    @Override
    public TechnologyDto setComputed(Long id, Jwt jwt, boolean isComputed) {
        log.info("Set technology with id={} calculated {}", id, isComputed);
        var technology = get(id);
        var uuid = jwt.getClaim("sub").toString();
        checkUpdate(technology, uuid);
        technology.setComputed(isComputed);
        return update(technology, jwt);
    }

    private void checkUpdate(TechnologyDto technology, String uuid) {
        var blocked = technology.getBlocked();
        if (blocked == null) {
            throw new EntityBlockException("Назначьте технологию себе в работу");
        }
        checkBlocked(technology, uuid);
    }

    private void checkBlocked(TechnologyDto technology, String uuid) {
        log.info("Checking block");
        var blocked = technology.getBlocked();
        if (blocked != null && !Objects.equals(blocked.getId(), uuid)) {
            throw new EntityBlockException(String.format("Эта технология в работе у пользователя %s %s", blocked.getFirstName(), blocked.getLastName()));
        }
    }
}