package ru.erp.sfsb.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderPlannerData {

    private long orderId;
    private int applicationNumber;
    private List<ItemData> items;

    @Data
    @AllArgsConstructor
    public static class ItemData {
        private String itemName;
        private String decimal;
        private List<Task> technology;

        @Data
        @AllArgsConstructor
        public static class Task {
            private String operationName;
            private int amount;
            private Duration process;
            private Duration setup;
//            private boolean isCooperate;
        }
    }
}