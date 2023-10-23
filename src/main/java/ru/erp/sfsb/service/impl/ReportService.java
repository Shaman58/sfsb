package ru.erp.sfsb.service.impl;

import com.github.petrovich4j.Case;
import com.github.petrovich4j.Gender;
import com.github.petrovich4j.NameType;
import com.github.petrovich4j.Petrovich;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.*;
import ru.erp.sfsb.exception.EntityNullException;
import ru.erp.sfsb.service.*;
import ru.erp.sfsb.utils.WordDocumentUtil;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportService {

    private final OrderService orderService;
    private final ItemService itemService;
    private final EmployeeService employeeService;
    private final OperationService operationService;
    private final CompanyService companyService;
    private final Petrovich petrovich;

    public void generateKp(Long orderId, HttpServletResponse response) {
        log.info("Generating kp with order id {}", orderId);
        try {
            var inputStream = getClass().getResourceAsStream("/kp-template.docx");
            log.info("inputStream");
            var doc = new WordDocumentUtil(inputStream);
            log.info("doc");
            var order = orderService.get(orderId);
            var company = order.getEmployee().getDepartment().getCompany();
            log.info("company");
            var contact = order.getContact();
            log.info("contact");
            var employee = String.format("%s %s %s",
                    order.getEmployee().getPosition(),
                    order.getEmployee().getFirstName(),
                    order.getEmployee().getLastName());
            log.info("employee");
            var bodyData = Map.of(
                    "[proposal]", order.getBusinessProposal(),
                    "[manager]", employee
            );
            log.info("bodyData");
            var headerData = getCompanyMap(company);
            log.info("headerData1");
            headerData.put("[app-number]", String.valueOf(order.getApplicationNumber()));
            log.info("headerData2");
            headerData.put("[target]", String.format("%s %s %s",
                    order.getCustomer().getCompanyName(),
                    contact.getFirstName(),
                    contact.getLastName()));
            log.info("headerData3");
            doc.generateKp(headerData, getItemList(order.getItems()), bodyData);
            log.info("doc.generateKp");
            response.setHeader("Content-Disposition", "attachment; filename=kp.docx");
            log.info("setHeader");
            doc.save(response.getOutputStream());
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateToolOrder(HttpServletResponse response, Long targetEmployeeId, Long fromEmployeeId, Long orderId, String body) {
        try {
            var inputStream = getClass().getResourceAsStream("/tool-order-template.docx");
            var doc = new WordDocumentUtil(inputStream);
            var targetEmployee = employeeService.get(targetEmployeeId);
            var fromEmployee = employeeService.get(fromEmployeeId);
            var companyName = companyService.getCompany().getCompanyName();
            var headerData = getHeaderFromEmployees(targetEmployee, fromEmployee, companyName);
            if (Objects.equals(body, null)) {
                body = "Прошу Вас, разрешить отделу снабжения приобрести следующие позиции:";
            }
            var footer = getFooterFromEmployee(fromEmployee);
            doc.generateToolOrder(getAllToolsFromOrder(orderService.get(orderId)), headerData, body, footer);
            response.setHeader("Content-Disposition", "attachment; filename=tool-order.docx");
            doc.save(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void calculateItem(Long itemId) {
        var item = itemService.get(itemId);
        item.getTechnology().setComputed(true);
        if (item.isCustomerMaterial()) {
            item.setPrice(calculateItemPrice(item));
        } else {
            var materialPrice = getItemWorkpiecesPrice(item);
            var itemPrice = calculateItemPrice(item);
            System.out.println("material price " + materialPrice);
            System.out.println("item price " + itemPrice);
            item.setPrice(itemPrice.add(materialPrice));
        }
        itemService.update(item);
    }

    private List<Map<String, String>> getAllToolsFromOrder(OrderDto order) {
        var measurers = getToolList(getUniqueToolItems(getAllToolsFromOrderByType(order, MeasureToolItemDto.class)));
        var cutters = getToolList(getUniqueToolItems(getAllToolsFromOrderByType(order, CutterToolItemDto.class)));
        cutters.addAll(measurers);
        return cutters;
    }

    private List<Map<String, String>> getToolList(List<? extends ToolItemDto> tools) {
        return IntStream.range(0, tools.size()).mapToObj(i -> getToolMap(tools, i)).collect(toList());
    }

    private Map<String, String> getToolMap(List<? extends ToolItemDto> tools, Integer pos) {
        return Map.of(
                "[no]", String.valueOf(pos + 1),
                "[name]", tools.get(pos).getTool().getToolName(),
                "[description]", tools.get(pos).getTool().getDescription(),
                "[amount]", String.valueOf(tools.get(pos).getAmount()),
                "[tool-price]", tools.get(pos).getPrice().toString()
        );
    }

    private List<? extends ToolItemDto> getUniqueToolItems(List<? extends ToolItemDto> toolItems) {
        return toolItems.stream()
                .collect(Collectors.toMap(ToolItemDto::getTool,
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
        if (toolClass == CutterToolItemDto.class) {
            return setup.getCutterToolItems();
        }
        if (toolClass == MeasureToolItemDto.class) {
            return setup.getMeasureToolItems();
        }
        if (toolClass == SpecialToolItemDto.class) {
            return setup.getSpecialToolItems();
        }
        return new ArrayList<>();
    }

    private MonetaryAmount calculateItemPrice(ItemDto item) {
        return item
                .getTechnology()
                .getSetups().stream()
                .map(setup -> calculateSetup(setup, item.getQuantity(), item.getTechnology().getQuantityOfPartsFromWorkpiece()))
                .reduce(MonetaryAmount::add)
                .orElseThrow(() -> new EntityNullException(String.format("Price of Item with id=%s is missed", item.getId())))
                .add(operationService.getTechnologyPrice().getPaymentPerHour()
                        .divide(60 * 60 * 1000)
                        .multiply(item.getTechnology().getTechnologistTime().toMillis()))
                .add(item.getTechnology().getOutsourcedCosts());
    }

    private MonetaryAmount calculateSetup(SetupDto setup, Integer itemQuantity, Integer quantityOfPartsFromWorkpiece) {
        if (setup.isCooperate()) {
            return setup.getCooperatePrice().multiply(itemQuantity);
        }
        var timePrice = switch (setup.getOperation().getOperationTimeManagement()) {
            case FULL -> calculateSetupPrice_FULL(setup, itemQuantity, quantityOfPartsFromWorkpiece);
            case PROCESS_TIME_ONLY -> calculateSetupPrice_PROCESS_TIME_ONLY(setup, itemQuantity);
            case COMPUTED -> calculateSetupPrice_COMPUTED(setup, itemQuantity, quantityOfPartsFromWorkpiece);
            case NONE -> Monetary.getDefaultAmountFactory().setCurrency("RUB").setNumber(0).create();
        };
        System.out.println(setup.getSetupNumber() + " " + timePrice);
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
        millis = setup.getProcessTime().multipliedBy(times).toMillis();
        return calculateSetupPriceGeneral(setup.getOperation(), millis);
    }

    //FULL
    //Время цикла + межоперационка. Время может быть на заготовку или на деталь
    private MonetaryAmount calculateSetupPrice_FULL(SetupDto setup, Integer itemQuantity, Integer quantityOfPartsFromWorkpiece) {
        long millis;
        var times = itemQuantity / quantityOfPartsFromWorkpiece;
        if (itemQuantity % quantityOfPartsFromWorkpiece != 0) {
            times++;
        }
        if (setup.isGroup()) {
            millis = setup.getProcessTime().plus(setup.getInteroperativeTime()).multipliedBy(times).toMillis();
        } else {
            millis = setup.getProcessTime().plus(setup.getInteroperativeTime()).multipliedBy(itemQuantity).toMillis();
        }
        var productionPrice = calculateSetupPriceGeneral(setup.getOperation(), millis);
        var setupPrice = operationService.getSetupPrice().getPaymentPerHour()
                .divide(60 * 60 * 1000)
                .multiply(setup.getSetupTime().toMillis());
        if (setup.getAdditionalTools().size() == 0) {
            return productionPrice.add(setupPrice);
        }
        var additionalPrice = setup
                .getAdditionalTools().stream()
                .map(tool -> tool.getWorkpiece()
                        .getMaterial()
                        .getPrice()
                        .multiply(getWorkpieceWeight(tool.getWorkpiece()))
                        .multiply(tool.getAmount()))
                .reduce(MonetaryAmount::add)
                .orElseThrow(() -> new EntityNullException("Price of setup additional is missed"));
        System.out.println("additional " + additionalPrice);
        var cuttersPrice = getToolsMonetary(setup.getCutterToolItems());
        System.out.println("cutter " + cuttersPrice);
        var measurersPrice = getToolsMonetary(setup.getMeasureToolItems());
        System.out.println("measure " + measurersPrice);
        var specialsPrice = getToolsMonetary(setup.getSpecialToolItems());
        System.out.println("spec " + specialsPrice);
        return productionPrice
                .add(setupPrice)
                .add(additionalPrice)
                .add(cuttersPrice)
                .add(measurersPrice)
                .add(specialsPrice);
    }

    private <E extends ToolItemDto> MonetaryAmount getToolsMonetary(List<E> tools) {
        if (tools.size() == 0) {
            return Monetary.getDefaultAmountFactory()
                    .setCurrency("RUB").setNumber(0).create();
        }
        return tools
                .stream().map(item -> item.getPrice().multiply(item.getAmount()))
                .reduce(MonetaryAmount::add)
                .orElseThrow(() -> new EntityNullException("Price of tools is missed"));
    }

    private MonetaryAmount calculateSetupPriceGeneral(OperationDto operation, Long millis) {
        return operation
                .getPaymentPerHour()
                .divide(60 * 60 * 1000)
                .multiply(millis);
    }

    private String getFooterFromEmployee(EmployeeDto employee) {
        return String.format("%s %s %s", employee.getPosition(), getInitials(employee.getFirstName()), employee.getLastName());
    }

    private String getHeaderFromEmployees(EmployeeDto targetEmployee, EmployeeDto fromEmployee, String companyName) {
        return String.format("%s \n%s \n%s %s \nот %s \n%s %s",
                targetEmployee.getPosition(),
                companyName,
                petrovich.say(targetEmployee.getLastName(), NameType.LastName, petrovich.gender(targetEmployee.getLastName(), Gender.Male), Case.Dative),
                getInitials(targetEmployee.getFirstName()),
                fromEmployee.getPosition().toLowerCase(),
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

    private Map<String, String> getCompanyMap(CompanyDto company) {
        var map = new HashMap<String, String>();
        map.put("[company_name]", company.getCompanyName());
        map.put("[address]", company.getAddress());
        map.put("[phone]", "тел: " + company.getPhoneNumber());
        map.put("[email]", "e-mail: " + company.getEmail());
        map.put("[inn]", "ИНН: " + company.getInn());
        map.put("[kpp]", "КПП: " + company.getKpp());
        map.put("[ogrn]", "ОГРН: " + company.getOgrn());
        map.put("[payment_account]", "р/с: " + company.getPaymentAccount());
        map.put("[bank]", company.getBank());
        map.put("[correspondent_account]", "корсчет: " + company.getCorrespondentAccount());
        map.put("[bik]", "БИК: " + company.getBik());
        map.put("[kpp-inn-ogrn]", company.getKpp() + " "
                + company.getInn() + " " + company.getOgrn());
        return map;
    }

    private List<Map<String, String>> getItemList(List<ItemDto> items) {
        return IntStream.range(0, items.size()).mapToObj(i -> getOrderMap(items, i)).collect(toList());
    }

    private Map<String, String> getOrderMap(List<ItemDto> items, Integer pos) {
        return Map.of(
                "[no]", String.valueOf(pos + 1),
                "[name]", items.get(pos).getTechnology().getDrawingName(),
                "[decimal]", String.valueOf(items.get(pos).getTechnology().getDrawingNumber()),
                "[amount]", String.valueOf(items.get(pos).getQuantity()),
                "[item-price]", items.get(pos).getPrice().divide(items.get(pos).getQuantity()).toString(),
                "[total-price]", items.get(pos).getPrice().toString()
        );
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
        var weight = switch (workpiece.getMaterial().getGeometry()) {
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
        System.out.println("weight " + weight);
        return weight;
    }
}