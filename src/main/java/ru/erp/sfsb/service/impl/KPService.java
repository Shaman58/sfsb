package ru.erp.sfsb.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.CompanyDto;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.service.OrderService;
import ru.erp.sfsb.utils.WordDocumentUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class KPService {

    private final OrderService orderService;

    public void generateKp(Long orderId, HttpServletResponse response) {
        try {
            var inputStream = new FileInputStream("kp-template.docx");
            WordDocumentUtil doc = new WordDocumentUtil(inputStream);
            var order = orderService.get(orderId);
            var company = order.getEmployee().getDepartment().getCompany();
            var contact = order.getContact();
            var employee = String.format("%s %s %s",
                    order.getEmployee().getPosition(),
                    order.getEmployee().getFirstName(),
                    order.getEmployee().getLastName());
            var bodyData = Map.of(
                    "[proposal]", order.getBusinessProposal(),
                    "[manager]", employee
            );
            var headerData = getCompanyMap(company);
            headerData.put("[app-number]", String.valueOf(order.getApplicationNumber()));
            headerData.put("[target]", String.format("%s %s %s",
                    order.getCustomer().getCompanyName(),
                    contact.getFirstName(),
                    contact.getLastName()));
            doc.generateKp(headerData, getItemList(order.getItems()), bodyData);
            response.setHeader("Content-Disposition", "attachment; filename=kp.docx");
            doc.save(response.getOutputStream());
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
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
        map.put("[ogrn]", "ОГРН: " + company.getOkpo());
        map.put("[payment_account]", "р/с: " + company.getPaymentAccount());
        map.put("[bank]", company.getBank());
        map.put("[correspondent_account]", "корсчет: " + company.getCorrespondentAccount());
        map.put("[bik]", "БИК: " + company.getBik());
        map.put("[kpp-inn-ogrn]", company.getKpp() + " "
                + company.getInn() + " " + company.getOkpo());
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