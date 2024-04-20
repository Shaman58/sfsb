package ru.erp.sfsb.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
public class ToolsReport {

    private String header;
    private String body;
    private String footer;
    private List<ToolData> tools;

    @AllArgsConstructor
    @Data
    public static class ToolData {
        private String name;
        private String description;
        private int amount;
        private BigDecimal itemPrice;
    }
}