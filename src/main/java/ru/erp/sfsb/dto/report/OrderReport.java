package ru.erp.sfsb.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderReport {

    private int applicationNumber;
    private String createdBy;
    private String businessProposal;
    private Long companyId;
    private Long customerId;
    private List<ItemData> items;

    @AllArgsConstructor
    @Data
    public static class ItemData {
        private String name;
        private String decimal;
        private int amount;
        private BigDecimal itemPrice;
    }
}