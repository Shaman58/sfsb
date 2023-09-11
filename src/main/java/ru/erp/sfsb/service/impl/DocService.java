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
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.dto.EmployeeDto;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.dto.SetupDto;
import ru.erp.sfsb.exception.EntityNullException;
import ru.erp.sfsb.service.EmployeeService;
import ru.erp.sfsb.service.ItemService;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.utils.WordDocumentUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocService {

    private final OrderService orderService;
    private final ItemService itemService;
    private final Petrovich petrovich;
    private final EmployeeService employeeService;

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

//    public void generateToolOrder(HttpServletResponse response, Long targetEmployeeId, Long fromEmployeeId, Long orderId, String body) {
//        try {
//            var inputStream = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("tool-order-template.docx")).getFile());
//            var doc = new WordDocumentUtil(inputStream);
//            var targetEmployee = employeeService.get(targetEmployeeId);
//            var fromEmployee = employeeService.get(fromEmployeeId);
//            var companyName = orderService.get(orderId).getEmployee().getDepartment().getCompany().getCompanyName();
//            var headerData = getHeaderFromEmployees(targetEmployee, fromEmployee, companyName);
//            if (Objects.equals(body, null)) {
//                body = "Прошу Вас, разрешить отделу снабжения приобрести следующие позиции:";
//            }
//            var footer = getFooterFromEmployee(fromEmployee);
//            doc.generateToolOrder(getToolString(cutterToolService.getAllToolsByOrderId(orderId)), headerData, body, footer);
//            response.setHeader("Content-Disposition", "attachment; filename=tool-order.docx");
//            doc.save(response.getOutputStream());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void calculateItem(Long itemId) {
//        var item = itemService.get(itemId);
//        item.setPrice(calculateItemPrice(item));
//        item.setEstimatedDuration(calculateItemEstimatedDuration(item));
//        item.getTechnology().setComputed(true);
//        itemService.update(item);
//    }

//    private MonetaryAmount calculateItemPrice(ItemDto item) {
//        return item
//                .getTechnology()
//                .getSetups().stream()
//                .map(setup -> calculateSetupPrice(setup, item.getQuantity()))
//                .reduce(MonetaryAmount::add)
//                .orElseThrow(() -> new EntityNullException(String.format("Price of Item with id=%s is missed", item.getId())));
//    }
//
//    private MonetaryAmount calculateSetupPrice(SetupDto setup, Integer itemQuantity) {
//        var totalTime = setup.getInteroperativeTime()
//                .plus(setup.getSetupTime().dividedBy(itemQuantity + setup.getTechnology().getQuantityOfSetUpParts()))
//                .plus(setup.getProcessTime()).toMinutes();
//        return setup
//                .getProductionUnit()
//                .getPaymentPerHour()
//                .divide(60).multiply(totalTime);
//    }

    private Duration calculateItemEstimatedDuration(ItemDto item) {
        return item.getTechnology().getSetups().stream()
                .map(this::calculateSetupDuration)
                .reduce(Duration::plus)
                .orElseThrow(() -> new EntityNullException(String.format("Durations of Item with id=%s is missed", item.getId())))
                .multipliedBy(item.getQuantity() + item.getTechnology().getQuantityOfSetUpParts())
                .plus(
                        item.getTechnology().getSetups().stream()
                                .map(SetupDto::getSetupTime)
                                .reduce(Duration::plus)
                                .orElseThrow(() -> new EntityNullException(String.format("Durations of Item with id=%s is missed", item.getId())))
                );
    }

    private Duration calculateSetupDuration(SetupDto setup) {
        return setup.getInteroperativeTime()
                .plus(setup.getProcessTime());
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
                "[item-price]", items.get(pos).getPrice().toString(),
                "[total-price]", items.get(pos).getPrice().multiply(items.get(pos).getQuantity()).toString()
        );
    }
}