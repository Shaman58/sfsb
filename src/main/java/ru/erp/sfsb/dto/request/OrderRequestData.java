package ru.erp.sfsb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderRequestData {

    private int applicationNumber;
    private String createdBy;
    private String businessProposal;
    private Long companyId;
    private Long customerId;
    private List<ItemRequestData> items;

    @AllArgsConstructor
    @Data
    public static class ItemRequestData {
        private String name;
        private String decimal;
        private int amount;
        private BigDecimal itemPrice;
    }
}