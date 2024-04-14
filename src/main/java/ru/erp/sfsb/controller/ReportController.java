package ru.erp.sfsb.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.request.OrderRequestData;
import ru.erp.sfsb.service.impl.ReportService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doc")
public class ReportController {

    private final ReportService reportService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cp")
    public OrderRequestData getCp(@RequestParam(value = "orderId") Long orderId,
                                  @RequestParam(value = "companyId", required = false, defaultValue = "1") Long companyId) {
        return reportService.generateCpData(orderId, companyId);
    }

    @GetMapping("/cp/remote")
    public void sendCp(
            @RequestParam(value = "orderId") Long orderId,
            @RequestParam(value = "companyId") Long companyId) {
        reportService.sendCpToStore(orderId, companyId);
    }

    @GetMapping("/tool-order")
    public void getToolOrder(HttpServletResponse response, @AuthenticationPrincipal Jwt jwt,
                             @RequestParam Long orderId,
                             @RequestParam(required = false) String body,
                             @RequestParam(required = false, defaultValue = "1") Long companyId) {
        var feId = jwt.getClaim("sub").toString();
        reportService.generateToolOrder(response, feId, orderId, body, companyId);
    }

    @GetMapping("/manufacturing-report")
    public void getManufacturingReport(HttpServletResponse response,
                                       @RequestParam Long orderId) {
        reportService.generateManufacturingReport(response, orderId);
    }

    @GetMapping("/operation-report")
    public void getOperationReport(HttpServletResponse response,
                                   @RequestParam Long orderId) {
        reportService.generateOperationReport(response, orderId);
    }
}