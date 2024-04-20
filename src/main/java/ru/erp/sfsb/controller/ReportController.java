package ru.erp.sfsb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.report.OrderReport;
import ru.erp.sfsb.dto.report.ToolsReport;
import ru.erp.sfsb.service.impl.ReportService;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "API взаимодействия с ReportService")
@RequestMapping("/api/doc")
public class ReportController {

    private final ReportService reportService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить OrderReport по 'orderId' и 'companyId'")
    @GetMapping("/cp")
    public OrderReport getCp(@RequestParam(value = "orderId") Long orderId,
                             @RequestParam(value = "companyId", required = false, defaultValue = "1") Long companyId) {
        return reportService.generateCpData(orderId, companyId);
    }

    @Operation(summary = "Отправить OrderReport с 'orderId' и 'companyId' в хранилище Компредов")
    @GetMapping("/cp/remote")
    public void sendCp(
            @RequestParam(value = "orderId") Long orderId,
            @RequestParam(value = "companyId") Long companyId) {
        reportService.sendCpToStore(orderId, companyId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить ToolsReport по 'orderId' и 'companyId'")
    @GetMapping("/tool-order")
    public ToolsReport getToolOrder(@AuthenticationPrincipal Jwt jwt,
                                    @RequestParam Long orderId,
                                    @RequestParam(required = false) String body,
                                    @RequestParam(required = false, defaultValue = "1") Long companyId) {
        var feId = jwt.getClaim("sub").toString();
        return reportService.generateToolOrder(feId, orderId, body, companyId);
    }

    @Operation(summary = "Получить производственный отчет по 'orderId'")
    @GetMapping("/manufacturing-report")
    public ResponseEntity<byte[]> getOperationReportTest(@RequestParam Long orderId) {
        return reportService.generateManufacturingReport(orderId);
    }

    @Operation(summary = "Получить операционный отчет по 'orderId'")
    @GetMapping("/operation-report")
    public ResponseEntity<byte[]> getOperationReport(@RequestParam Long orderId) {
        return reportService.generateOperationReport(orderId);
    }
}