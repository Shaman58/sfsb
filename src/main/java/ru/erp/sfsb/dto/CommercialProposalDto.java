package ru.erp.sfsb.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CommercialProposalDto {

    private List<Map<String, String>> itemList;
    private Map<String, String> bodyData;
}