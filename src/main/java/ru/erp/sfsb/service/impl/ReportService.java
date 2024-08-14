package ru.erp.sfsb.service.impl;

import com.github.petrovich4j.Case;
import com.github.petrovich4j.Gender;
import com.github.petrovich4j.NameType;
import com.github.petrovich4j.Petrovich;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.LogTag;
import ru.erp.sfsb.dto.AdditionalToolDto;
import ru.erp.sfsb.dto.CutterToolItemDto;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.dto.MeasureToolItemDto;
import ru.erp.sfsb.dto.OperationDto;
import ru.erp.sfsb.dto.OrderDto;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.dto.SpecialToolItemDto;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.dto.ToolItemDto;
import ru.erp.sfsb.dto.UserDto;
import ru.erp.sfsb.dto.WorkpieceDto;
import ru.erp.sfsb.dto.report.OrderPlannerData;
import ru.erp.sfsb.dto.report.OrderReport;
import ru.erp.sfsb.dto.report.ToolsReport;
import ru.erp.sfsb.exception.EntityNullException;
import ru.erp.sfsb.exception.FileReadException;
import ru.erp.sfsb.exception.ReportGenerateException;
import ru.erp.sfsb.model.OperationTimeManagement;
import ru.erp.sfsb.service.CompanyService;
import ru.erp.sfsb.service.OperationService;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.service.UserService;
import ru.erp.sfsb.utils.CpStoreUtil;
import ru.erp.sfsb.utils.DurationRuCustomFormatter;
import ru.erp.sfsb.utils.TaskPlannerUtil;
import ru.erp.sfsb.utils.XlsxReportUtil;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportService {

    private final OrderService orderService;
    private final OperationService operationService;
    private final CompanyService companyService;
    private final Petrovich petrovich;
    private final DurationRuCustomFormatter durationFormatter;
    private final UserService userService;
    private final CpStoreUtil cpStoreUtil;
    private final TaskPlannerUtil taskPlannerUtil;
    private final static LogTag LOG_TAG = LogTag.REPORT_SERVICE;

    public OrderReport generateCpData(Long orderId, Long companyId) {
        log.info("[{}] Генерация компреда по ордеру с id {}", LOG_TAG, orderId);
        var order = orderService.get(orderId);
        checkComputed(order);
        return calculateOrderData(order, companyId);
    }

    public ToolsReport generateToolOrder(String fromEmployeeId, Long orderId, String body, Long companyId) {
        log.info("[{}] Генерация заказ наряда на инструмент по orderId {}", LOG_TAG, orderId);
        var order = orderService.get(orderId);

        var fromEmployee = userService.get(fromEmployeeId);
        var companyName = companyService.get(companyId).getCompanyName();
        var headerData = getHeaderFromEmployees(fromEmployee, companyName);
        if (Objects.equals(body, null)) {
            body = "Прошу Вас, разрешить отделу снабжения приобрести следующие позиции:";
        }
        var footer = getFooterFromEmployee(fromEmployee);
        return new ToolsReport(
                headerData,
                body,
                footer,
                getAllToolsFromOrder(order)
        );
    }

    public void sendCpToStore(Long orderId, Long companyId) {
        log.info("[{}] Отправка компреда по ордеру с id {} в cp-store", LOG_TAG, orderId);
        var order = orderService.get(orderId);
        checkComputed(order);
        var orderDto = calculateOrderData(order, companyId);
        cpStoreUtil.uploadCp(orderDto);
    }

    public ResponseEntity<byte[]> generateOperationReport(Long orderId) {
        log.info("[{}] Генерация операционного отчета по ордеру с id {}", LOG_TAG, orderId);
        var order = orderService.get(orderId);
        try {
            var data = createOperationTable(order);
            return getResponseEntity(order, data, "операционный отчет");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<byte[]> generateManufacturingReport(Long orderId) {
        log.info("[{}] Генерация производственного отчета по ордеру с id {}", LOG_TAG, orderId);
        var order = orderService.get(orderId);
        try {
            var data = getOrderManData(order);
            return getResponseEntity(order, data, "производственного отчет");
        } catch (IOException e) {
            throw new FileReadException("Не удалось скачать файл. Ошибка записи файла в ответ");
        }
    }

    public void generateOrderReport(Long orderId) {
        var order = orderService.get(orderId);
        taskPlannerUtil.uploadOrderPlannerData(getOrderPlannerData(order));

    }

    private ResponseEntity<byte[]> getResponseEntity(OrderDto order, List<List<String>> data, String reportName) throws IOException {
        var bytes = XlsxReportUtil.saveToFile(data);
        var date = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH-mm"));
        var filename = String.format("%s №%s от %s.xlsx", reportName, order.getApplicationNumber(), date);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(
                ContentDisposition.builder("attachment").filename(filename, StandardCharsets.UTF_8).build());
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    private void checkComputed(OrderDto order) {
        var items = order.getItems().stream()
                .filter(item -> !item.getTechnology().isComputed())
                .map(item ->
                        String.format("%s %s", item.getTechnology().getDrawingNumber(), item.getTechnology().getDrawingName()))
                .toList();
        if (!items.isEmpty()) {
            throw new ReportGenerateException(
                    String.format("[%s] Технологии %s не рассчитаны!", LOG_TAG, items));
        }
    }

    private OrderReport calculateOrderData(OrderDto order, Long companyId) {
        log.info("[{}] Расчет стоимости заказа {}", LOG_TAG, order.getId());
        var items = order.getItems().stream()
                .map(this::calculateItemData)
                .toList();
        return new OrderReport(
                order.getApplicationNumber(),
                order.getUser().getId(),
                order.getBusinessProposal(),
                companyId,
                order.getCustomer().getId(),
                items
        );
    }

    private OrderReport.ItemData calculateItemData(ItemDto item) {
        log.info("[{}] Расчет стоимости позиции {} {}", LOG_TAG, item.getTechnology().getDrawingName(),
                item.getTechnology().getDrawingNumber());
        try {
            MonetaryAmount computed;
            if (item.isCustomerMaterial() || item.getTechnology().isAssembly()) {
                computed = calculateItemPrice(item);
            } else {
                var materialPrice = getItemWorkpiecesPrice(item);
                var itemPrice = calculateItemPrice(item);
                computed = itemPrice.add(materialPrice);
            }
            return new OrderReport.ItemData(
                    item.getTechnology().getDrawingName(),
                    item.getTechnology().getDrawingNumber(),
                    item.getQuantity(),
                    computed.divide(item.getQuantity())
                            .getNumber().numberValue(BigDecimal.class).setScale(2, RoundingMode.HALF_UP));
        } catch (Exception e) {
            throw new ReportGenerateException(
                    String.format("[%s] Расчет не удался, проверьте данные позиции %s %s", LOG_TAG, item.getTechnology().getDrawingName(), item.getTechnology().getDrawingNumber()));
        }
    }

    private List<List<String>> createOperationTable(OrderDto order) {
        var operations = operationService.getAll();
        var firstHeader = new ArrayList<>(List.of("", "", "", ""));
        var secondHeader = new ArrayList<>(List.of(
                "Наименование",
                "Инструмент и меритель",
                "Кол-во по заказу",
                "Кол-во с наладкой и браком"
        ));
        operations.forEach(operation -> firstHeader.addAll(List.of(operation.getOperationName(), "", "", "", "", "")));
        operations.forEach(operation -> secondHeader.addAll(List.of("1 дет Маш-е", "1 дет МОР", "Общ.на 1дет", "Тмаш + МОР", "М.+н", "Нал.")));
        firstHeader.add("");
        secondHeader.add("Общее время");
        var data = new ArrayList<>(order
                .getItems()
                .stream()
                .collect(toMap(this::getItemHeadLine, this::getItemData))
                .entrySet()
                .stream()
                .map(entry -> getItemLine(entry.getValue(), entry.getKey(), operations))
                .toList());
        data.add(getTotalLine(data));
        data.add(0, firstHeader);
        data.add(1, secondHeader);
        return data;
    }

    private List<String> getTotalLine(List<List<String>> lines) {
        var total = sumTotalTime(lines);
        var horSize = lines.get(0).size();
        var line = new ArrayList<>(IntStream.range(0, horSize)
                .mapToObj(i -> "")
                .toList());
        line.set(horSize - 1, total);
        return line;
    }

    private String sumTotalTime(List<List<String>> lines) {
        var horSize = lines.get(0).size();
        var duration = lines.stream()
                .map(line -> durationFormatter.getDurationFromString(line.get(horSize - 1)))
                .reduce(Duration.ZERO, Duration::plus);
        return durationFormatter.getRusTimeFormat(duration);
    }

    private List<String> getItemLine(Map<String, List<Duration>> itemData, List<String> itemHead, List<OperationDto> operations) {
        List<String> result = new ArrayList<>(itemHead);
        var totalTime = durationFormatter.getRusTimeFormat(itemData.values().stream()
                .map(list -> list.get(4))
                .reduce(Duration.ZERO, Duration::plus));
        var data = operations.stream()
                .flatMap(operation -> itemData.get(operation.getOperationName()) == null
                        ? Stream.of("", "", "", "", "", "")
                        : itemData.get(operation.getOperationName()).stream()
                        .map(durationFormatter::getRusTimeFormat))
                .toList();
        result.addAll(data);
        result.add(totalTime);
        return result;
    }

    private Map<String, List<Duration>> getItemData(ItemDto item) {
        var setups = item.getTechnology().getSetups();
        return setups.stream()
                .filter(setup -> !setup.isCooperate())
                .collect(toMap(setup -> setup.getOperation().getOperationName(),
                        setup -> getSetupInfo(setup, item.getQuantity(), item.getTechnology().getQuantityOfPartsFromWorkpiece()),
                        this::sumAllTimes));
    }

    private List<String> getItemHeadLine(ItemDto item) {
        var cutters = new ArrayList<>(getAllToolsFromTechnologyByType(item.getTechnology(), CutterToolItemDto.class)
                .stream()
                .map(tool -> String.format("%s(%s)", tool.getTool().getToolName(), tool.getTool().getDescription()))
                .toList());
        var measurers = getAllToolsFromTechnologyByType(item.getTechnology(), MeasureToolItemDto.class)
                .stream()
                .map(tool -> String.format("%s(%s)", tool.getTool().getToolName(), tool.getTool().getDescription()))
                .toList();
        cutters.addAll(measurers);
        var tools = cutters
                .stream()
                .reduce("", (s1, s2) -> s1.isEmpty() ? s2 : s1.concat(", ").concat(s2));
        return List.of(
                String.format("%s %s", item.getTechnology().getDrawingNumber(), item.getTechnology().getDrawingName()),
                tools,
                item.getQuantity().toString(),
                String.valueOf(item.getQuantity() +
                        item.getTechnology().getQuantityOfSetUpParts() +
                        item.getTechnology().getQuantityOfDefectiveParts())
        );
    }

    private List<Duration> sumAllTimes(List<Duration> existing, List<Duration> replacement) {
        var modified = new ArrayList<Duration>();
        for (int i = 0; i < existing.size(); i++) {
            modified.add(existing.get(i).plus(replacement.get(i)));
        }
        return modified;
    }

    private List<Duration> getSetupInfo(SetupDto setup, Integer quantity, Integer quantityOfPartsFromWorkpiece) {
        return switch (setup.getOperation().getOperationTimeManagement()) {
            case FULL -> getSetupInfo_Full(setup, quantity, quantityOfPartsFromWorkpiece);
            case PROCESS_TIME_ONLY -> getSetupInfo_PROCESS_TIME_ONLY(setup, quantity);
            case COMPUTED -> getSetupInfo_COMPUTED(setup, quantity, quantityOfPartsFromWorkpiece);
            case NONE -> new ArrayList<>();
        };
    }

    private List<Duration> getSetupInfo_COMPUTED(SetupDto setup, Integer quantity, Integer quantityOfPartsFromWorkpiece) {
        var times = getTimes_COMPUTED(quantity, quantityOfPartsFromWorkpiece, setup);
        var fullTimeForAll = setup.getProcessTime().multipliedBy(times);
        var processTime = fullTimeForAll.dividedBy(quantity);
        return List.of(
                processTime,
                Duration.of(0, ChronoUnit.MILLIS),
                processTime,
                fullTimeForAll,
                fullTimeForAll,
                Duration.of(0, ChronoUnit.MILLIS)
        );
    }

    private List<Duration> getSetupInfo_PROCESS_TIME_ONLY(SetupDto setup, Integer quantity) {
        return List.of(
                setup.getProcessTime(),
                Duration.of(0, ChronoUnit.MILLIS),
                setup.getProcessTime(),
                setup.getProcessTime().multipliedBy(quantity),
                setup.getProcessTime().multipliedBy(quantity),
                Duration.of(0, ChronoUnit.MILLIS)
        );
    }

    private List<Duration> getSetupInfo_Full(SetupDto setup, Integer quantity, Integer quantityOfPartsFromWorkpiece) {
        var times = getTimes_FULL(quantity, quantityOfPartsFromWorkpiece);
        Duration processTime;
        Duration interoperativeTime;
        if (setup.isGroup()) {
            processTime = setup.getProcessTime().dividedBy(times);
            interoperativeTime = setup.getInteroperativeTime().dividedBy(times);
        } else {
            processTime = setup.getProcessTime();
            interoperativeTime = setup.getInteroperativeTime();
        }
        var fullTime = processTime.plus(interoperativeTime);
        var fullTimeForAll = fullTime.multipliedBy(quantity);
        return List.of(
                processTime,
                interoperativeTime,
                fullTime,
                fullTimeForAll,
                fullTimeForAll.plus(setup.getSetupTime()),
                setup.getSetupTime());
    }

    private List<List<String>> getOrderManData(OrderDto order) {
        var data = order
                .getItems()
                .stream()
                .map(this::getItemDataList)
                .flatMap(List::stream)
                .collect(toList());
        data.add(0, List.of(
                "Наименование",
                "Длительность, ч",
                "Количество (с учетом наладки и брака), шт",
                "Время наладки, ч",
                "Время обработки с учетом межоперационного времени, ч"));
        return data;
    }

    private OrderPlannerData getOrderPlannerData(OrderDto order) {
        return new OrderPlannerData(
                order.getApplicationNumber(),
                order.getItems().stream()
                        .map(this::getTasks)
                        .toList()
        );
    }

    private OrderPlannerData.ItemData getTasks(ItemDto item) {
        return new OrderPlannerData.ItemData(
                item.getTechnology().getDrawingName(),
                item.getTechnology().getDrawingNumber(),
                item.getTechnology().getSetups().stream()
                        .sorted(Comparator.comparing(SetupDto::getSetupNumber))
                        .map(setupDto -> getTask(setupDto, getAmount(item)))
                        .toList()
        );
    }

    private int getAmount(ItemDto item) {
        return item.getQuantity() +
                item.getTechnology().getQuantityOfDefectiveParts() +
                item.getTechnology().getQuantityOfSetUpParts();
    }

    private List<List<String>> getItemDataList(ItemDto item) {
        var itemData = item
                .getTechnology()
                .getSetups()
                .stream()
                .map(setup -> getSetupDataList(setup,
                        item.getQuantity() +
                                item.getTechnology().getQuantityOfDefectiveParts() +
                                item.getTechnology().getQuantityOfSetUpParts()))
                .collect(toList());
        itemData.add(0, List.of(
                String.format("%s %s",
                        item.getTechnology().getDrawingName(),
                        item.getTechnology().getDrawingNumber()), "", "", "", ""));
        itemData.add(1, List.of(
                "Технолог",
                durationFormatter.getRusTimeFormat(item.getTechnology().getTechnologistTime()),
                "", "", ""
        ));
        return itemData;
    }

    private List<String> getSetupDataList(SetupDto setup, Integer amount) {
        return List.of(
                setup.getOperation().getOperationName(),
                durationFormatter.getRusTimeFormat(
                        setup.getInteroperativeTime()
                                .plus(setup.getProcessTime())
                                .multipliedBy(amount)),
                amount.toString(),
                durationFormatter.getRusTimeFormat(setup.getSetupTime()),
                durationFormatter.getRusTimeFormat(setup.getProcessTime()
                        .plus(setup.getInteroperativeTime()))
        );
    }

    private OrderPlannerData.ItemData.Task getTask(SetupDto setup, Integer amount) {
        return new OrderPlannerData.ItemData.Task(
                setup.getOperation().getOperationName(),
                amount,
                setup.getInteroperativeTime()
                        .plus(setup.getProcessTime())
                        .multipliedBy(amount),
                setup.getSetupTime(),
                setup.isCooperate()
        );
    }

    private List<ToolsReport.ToolData> getAllToolsFromOrder(OrderDto order) {
        var measurers = getToolList(getUniqueToolItems(getAllToolsFromOrderByType(order, MeasureToolItemDto.class)));
        var cutters = getToolList(getUniqueToolItems(getAllToolsFromOrderByType(order, CutterToolItemDto.class)));
        cutters.addAll(measurers);
        if (cutters.isEmpty()) {
            throw new ReportGenerateException(
                    String.format("[%s] В заявке отсутствуют инструменты", LOG_TAG));
        }
        return cutters;
    }

    private List<ToolsReport.ToolData> getToolList(List<? extends ToolItemDto> tools) {
        return tools.stream().map(this::getToolMap).collect(toList());
    }

    private ToolsReport.ToolData getToolMap(ToolItemDto tool) {
        return new ToolsReport.ToolData(
                tool.getTool().getToolName(),
                tool.getTool().getDescription(),
                tool.getAmount(),
                tool.getPrice().getNumber().numberValue(BigDecimal.class).setScale(2, RoundingMode.HALF_UP)
        );
    }

    private List<? extends ToolItemDto> getUniqueToolItems(List<? extends ToolItemDto> toolItems) {
        return toolItems.stream()
                .collect(toMap(ToolItemDto::getTool,
                        item -> item,
                        (existing, replacement) -> {
                            existing.setAmount(existing.getAmount() + replacement.getAmount());
                            return existing;
                        }))
                .values().stream().toList();
    }

    private List<? extends ToolItemDto> getAllToolsFromOrderByType(OrderDto order, Class<? extends ToolItemDto> toolClass) {
        return order
                .getItems().stream()
                .flatMap(item -> getAllToolsFromTechnologyByType(item.getTechnology(), toolClass).stream())
                .collect(toList());
    }

    private List<? extends ToolItemDto> getAllToolsFromTechnologyByType(TechnologyDto technology, Class<? extends ToolItemDto> toolClass) {
        return technology
                .getSetups().stream()
                .flatMap(setup -> getAllToolItemsFromSetupByType(setup, toolClass).stream())
                .collect(toList());
    }

    private List<? extends ToolItemDto> getAllToolItemsFromSetupByType(SetupDto setup, Class<? extends ToolItemDto> toolClass) {
        if (!setup.isCooperate() &&
                ((setup.getOperation().getOperationTimeManagement() == OperationTimeManagement.PROCESS_TIME_ONLY) ||
                        (setup.getOperation().getOperationTimeManagement() == OperationTimeManagement.FULL))) {
            if (toolClass == CutterToolItemDto.class) {
                return setup.getCutterToolItems();
            }
            if (toolClass == MeasureToolItemDto.class) {
                return setup.getMeasureToolItems();
            }
            if (toolClass == SpecialToolItemDto.class) {
                return setup.getSpecialToolItems();
            }
        }
        return new ArrayList<>();
    }

    private MonetaryAmount calculateItemPrice(ItemDto item) {
        return item
                .getTechnology()
                .getSetups().stream()
                .map(setup -> calculateSetup(setup, item.getQuantity(), item.getTechnology().getQuantityOfPartsFromWorkpiece()))
                .reduce(MonetaryAmount::add)
                .orElseThrow(() -> new EntityNullException(
                        String.format("[%s] Нет информации о стоимости инструмента с id=%s", LOG_TAG, item.getId())))
                .add(operationService.getTechnologistPrice().getPaymentPerHour()
                        .divide(60 * 60 * 1000)
                        .multiply(item.getTechnology().getTechnologistTime().toMillis()))
                .add(item.getTechnology().getOutsourcedCosts());
    }

    private MonetaryAmount calculateSetup(SetupDto setup, Integer itemQuantity, Integer
            quantityOfPartsFromWorkpiece) {
        if (setup.isCooperate()) {
            return setup.getCooperatePrice().multiply(itemQuantity);
        }
        var timePrice = switch (setup.getOperation().getOperationTimeManagement()) {
            case FULL -> calculateSetupPrice_FULL(setup, itemQuantity, quantityOfPartsFromWorkpiece);
            case PROCESS_TIME_ONLY -> calculateSetupPrice_PROCESS_TIME_ONLY(setup, itemQuantity);
            case COMPUTED -> calculateSetupPrice_COMPUTED(setup, itemQuantity, quantityOfPartsFromWorkpiece);
            case NONE -> throw new ReportGenerateException(
                    String.format("[%s] Установка с id=%s не может иметь значение NONE в поле OperationTimeManagement", LOG_TAG, setup.getId()));
        };
        log.debug(setup.getSetupNumber() + " " + timePrice);
        return timePrice;
    }

    //PROCESS_TIME_ONLY
    //Считается что время цикла ставится для одной детали. Время цикла умножается на цену за час.
    private MonetaryAmount calculateSetupPrice_PROCESS_TIME_ONLY(SetupDto setup, Integer itemQuantity) {
        var cuttersPrice = getToolsMonetary(setup.getCutterToolItems());
        var measurersPrice = getToolsMonetary(setup.getMeasureToolItems());
        var specialsPrice = getToolsMonetary(setup.getSpecialToolItems());
        return calculateSetupPriceGeneral(
                setup.getOperation(),
                setup.getProcessTime().multipliedBy(itemQuantity).toMillis())
                .add(cuttersPrice)
                .add(measurersPrice)
                .add(specialsPrice);
    }

    //COMPUTED
    //Время цикла ставится на несколько заготовок. Причем из каждой заготовки может изготовливаться несколько деталей
    private MonetaryAmount calculateSetupPrice_COMPUTED(SetupDto setup, Integer itemQuantity, Integer quantityOfPartsFromWorkpiece) {
        long millis;
        var times = getTimes_COMPUTED(itemQuantity, quantityOfPartsFromWorkpiece, setup);
        millis = setup.getProcessTime().multipliedBy(times).toMillis();
        return calculateSetupPriceGeneral(setup.getOperation(), millis);
    }

    private int getTimes_COMPUTED(Integer itemQuantity, Integer quantityOfPartsFromWorkpiece, SetupDto setup) {
        int times;
        if (setup.isAggregate()) {
            times = itemQuantity / ((setup.isGroup() ? quantityOfPartsFromWorkpiece : 1) * setup.getPerTime());
            if (itemQuantity % ((setup.isGroup() ? quantityOfPartsFromWorkpiece : 1) * setup.getPerTime()) != 0) {
                times++;
            }
        } else {
            times = itemQuantity / (setup.isGroup() ? quantityOfPartsFromWorkpiece : 1);
            if (itemQuantity % (setup.isGroup() ? quantityOfPartsFromWorkpiece : 1) != 0) {
                times++;
            }
        }
        return times;
    }

    //FULL
    //Время цикла + межоперационка. Время может быть на заготовку или на деталь
    private MonetaryAmount calculateSetupPrice_FULL(SetupDto setup, Integer itemQuantity, Integer
            quantityOfPartsFromWorkpiece) {
        long millis;
        var times = getTimes_FULL(itemQuantity, quantityOfPartsFromWorkpiece);
        if (setup.isGroup()) {
            millis = setup.getProcessTime().plus(setup.getInteroperativeTime()).multipliedBy(times).toMillis();
        } else {
            millis = setup.getProcessTime().plus(setup.getInteroperativeTime()).multipliedBy(itemQuantity).toMillis();
        }
        var productionPrice = calculateSetupPriceGeneral(setup.getOperation(), millis);
        var setupPrice = operationService.getSetupPrice().getPaymentPerHour()
                .divide(60 * 60 * 1000)
                .multiply(setup.getSetupTime().toMillis());
        var additionalPrice = getAdditionalsMonetary(setup.getAdditionalTools());
        var cuttersPrice = getToolsMonetary(setup.getCutterToolItems());
        var measurersPrice = getToolsMonetary(setup.getMeasureToolItems());
        var specialsPrice = getToolsMonetary(setup.getSpecialToolItems());
        return productionPrice
                .add(setupPrice)
                .add(additionalPrice)
                .add(cuttersPrice)
                .add(measurersPrice)
                .add(specialsPrice);
    }

    private int getTimes_FULL(Integer itemQuantity, Integer quantityOfPartsFromWorkpiece) {
        var times = itemQuantity / quantityOfPartsFromWorkpiece;
        if (itemQuantity % quantityOfPartsFromWorkpiece != 0) {
            times++;
        }
        return times;
    }

    private MonetaryAmount getAdditionalsMonetary(List<AdditionalToolDto> tools) {
        return tools.stream()
                .map(tool -> tool.getWorkpiece()
                        .getMaterial()
                        .getPrice()
                        .multiply(getWorkpieceWeight(tool.getWorkpiece()))
                        .multiply(tool.getAmount())
                        .add(operationService.getSetupPrice().getPaymentPerHour()
                                .divide(60 * 60 * 1000)
                                .multiply(tool.getProcessTime().toMillis())))
                .reduce(MonetaryAmount::add)
                .orElse(Monetary.getDefaultAmountFactory().setCurrency("RUB").setNumber(0).create());
    }

    private <E extends ToolItemDto> MonetaryAmount getToolsMonetary(List<E> tools) {
        return tools
                .stream().map(item -> item.getPrice().multiply(item.getAmount()))
                .reduce(MonetaryAmount::add)
                .orElse(Monetary.getDefaultAmountFactory().setCurrency("RUB").setNumber(0).create());
    }

    private MonetaryAmount calculateSetupPriceGeneral(OperationDto operation, Long millis) {
        return operation
                .getPaymentPerHour()
                .divide(60 * 60 * 1000)
                .multiply(millis);
    }

    private String getFooterFromEmployee(UserDto employee) {
        return String.format("%s %s", getInitials(employee.getFirstName()), employee.getLastName());
    }

    private String getHeaderFromEmployees(UserDto fromEmployee, String companyName) {
        return String.format("%s \nот \n%s %s",
                companyName,
                petrovich.say(fromEmployee.getLastName(), NameType.LastName, petrovich.gender(fromEmployee.getLastName(), Gender.Male), Case.Genitive),
                getInitials(fromEmployee.getFirstName()));
    }

    private String getInitials(String name) {
        var names = name.split(" ");
        if (names.length == 2) {
            return String.format("%s. %s.", names[0].charAt(0), names[1].charAt(0));
        } else {
            return String.format("%s.", names[0].charAt(0));
        }
    }

    private MonetaryAmount getItemWorkpiecesPrice(ItemDto item) {
        var times = item.getQuantity() / item.getTechnology().getQuantityOfPartsFromWorkpiece();
        if (item.getQuantity() % item.getTechnology().getQuantityOfPartsFromWorkpiece() != 0) {
            times++;
        }
        var materialPrice = item.getTechnology().getWorkpiece().getMaterial().getPrice();
        var totalWeight = getWorkpieceWeight(item.getTechnology().getWorkpiece()) * times;
        return materialPrice.multiply(totalWeight);
    }

    private double getWorkpieceWeight(WorkpieceDto workpiece) {
        return switch (workpiece.getMaterial().getGeometry()) {
            case BLANK, LIST, SQUARE, TAPE ->
                    (((double) workpiece.getGeom1() * (double) workpiece.getGeom2() * (double) workpiece.getGeom3())) * (double) workpiece.getMaterial().getDensity() / 1000000000;
            case TUBE ->
                    PI * (pow(((double) workpiece.getGeom1()) / 2, 2) - pow(((double) workpiece.getGeom2()) / 2, 2)) * (double) workpiece.getGeom3() * (double) workpiece.getMaterial().getDensity() / 1000000000;
            case CYLINDER, ROD ->
                    PI * pow(((double) workpiece.getGeom1()) / 2, 2) * (double) workpiece.getGeom2() * (double) workpiece.getMaterial().getDensity() / 1000000000;
            case HEXAGON ->
                    2 * Math.sqrt(3) * pow((double) workpiece.getGeom1(), 2) * (double) workpiece.getGeom2() * (double) workpiece.getMaterial().getDensity() / 1000000000;
            case PROFILE, OTHER -> 0;
        };
    }
}