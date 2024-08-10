package ru.erp.sfsb.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderPlannerData {

    private int applicationNumber;
    private List<ItemData> items;

    @Data
    @AllArgsConstructor
    public static class ItemData {
        private String name;
        private String decimal;
        private List<Task> sequence;

        @Data
        @AllArgsConstructor
        public static class Task {
            private String name;
            private int amount;
            private Duration process;
            private Duration setup;
            private boolean isCooperate;
        }
    }
}