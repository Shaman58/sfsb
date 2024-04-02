package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.exception.EntityBlockException;
import ru.erp.sfsb.exception.ReportGenerateException;
import ru.erp.sfsb.mapper.TechnologyMapper;
import ru.erp.sfsb.model.Technology;
import ru.erp.sfsb.repository.TechnologyRepository;
import ru.erp.sfsb.service.TechnologyService;
import ru.erp.sfsb.service.UserService;

import java.util.Objects;

import static ru.erp.sfsb.LogTag.TECHNOLOGY_SERVICE;

@Service
@Transactional
@Slf4j
public class TechnologyServiceImpl extends AbstractService<TechnologyDto, Technology, TechnologyRepository, TechnologyMapper>
        implements TechnologyService {

    private final UserService userService;

    public TechnologyServiceImpl(TechnologyMapper mapper, TechnologyRepository repository, UserService userService) {
        super(mapper, repository, "Technology", TECHNOLOGY_SERVICE);
        this.userService = userService;
    }

    @Override
    public TechnologyDto update(TechnologyDto dto, Jwt jwt) {
        log.info("[{}] Обновление сущности типа {} в БД", getLogTag(), getEntityName());
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
        log.info("[{}] Блокирование технологии с id={}", getLogTag(), id);
        var uuid = jwt.getClaim("sub").toString();
        var technology = get(id);
        checkBlocked(technology, uuid);
        technology.setBlocked(uuid);
        save(technology);
    }

    @Override
    public void unblock(Long id, Jwt jwt) {
        log.info("[{}] Разблокирование технологии с id={}", getLogTag(), id);
        var uuid = jwt.getClaim("sub").toString();
        checkExistById(id);
        var technology = get(id);
        checkBlocked(technology, uuid);
        technology.setBlocked(null);
        save(technology);
    }

    @Override
    public TechnologyDto setComputed(Long id, Jwt jwt, boolean isComputed) {
        log.info("[{}] Установка флага isComputed у сушности типа {} c id={} {}", getEntityName(), getLogTag(), id, isComputed);
        var technology = get(id);
        var uuid = jwt.getClaim("sub").toString();
        checkUpdate(technology, uuid);
        isTechnologyCanBeeComputed(technology);
        technology.setComputed(isComputed);
        return update(technology, jwt);
    }

    private void isTechnologyCanBeeComputed(TechnologyDto technology) {
        if (technology.getWorkpiece() == null) {
            throw new ReportGenerateException(
                    String.format("[%s] Нельзя пометить технологию без заготовки рассчитанной!", getLogTag()));
        }
        if (technology.getSetups().size() == 0) {
            throw new ReportGenerateException(
                    String.format("[%s] Нельзя пометить технологию без установок рассчитанной!", getLogTag()));
        }
    }

    private void checkUpdate(TechnologyDto technology, String uuid) {
        var status = technology.getBlocked();
        if (status == null) {
            throw new EntityBlockException(
                    String.format("[%s] Назначьте технологию себе в работу", getLogTag()));
        }
        checkBlocked(technology, uuid);
    }

    private void checkBlocked(TechnologyDto technology, String uuid) {
        log.info("Checking block");
        var status = technology.getBlocked();
        if (status != null && !Objects.equals(status, uuid)) {
            var user = userService.get(status);
            throw new EntityBlockException(
                    String.format("[%s] Эта технология в работе у пользователя %s %s", getLogTag(), user.getFirstName(), user.getLastName()));
        }
    }
}