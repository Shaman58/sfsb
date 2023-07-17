package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CutterToolDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.CutterToolsMapper;
import ru.erp.sfsb.model.CutterTool;
import ru.erp.sfsb.model.Setup;
import ru.erp.sfsb.repository.CutterToolRepository;
import ru.erp.sfsb.repository.OrderRepository;
import ru.erp.sfsb.service.CutterToolService;

import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class CutterToolServiceImpl extends AbstractService<CutterToolDto, CutterTool, CutterToolRepository, CutterToolsMapper>
        implements CutterToolService {

    private final OrderRepository orderRepository;

    public CutterToolServiceImpl(CutterToolsMapper mapper, CutterToolRepository repository, OrderRepository orderRepository) {
        super(mapper, repository, "Cutter tool");
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public Set<CutterToolDto> getAllToolsByOrderId(Long id) {
        var order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Order with id=%d in database", id)));
        var toolList = order.getItems()
                .stream().map(item -> item.getTechnology().getSetups()
                        .stream().map(Setup::getCutterTools)
                        .collect(toList()))
                .flatMap(List::stream)
                .flatMap(List::stream)
                .map(mapper::toDto)
                .collect(toList());
        return Set.copyOf(toolList);
    }
}