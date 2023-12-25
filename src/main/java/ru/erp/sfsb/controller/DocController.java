package ru.erp.sfsb.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.erp.sfsb.service.impl.ReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doc")
public class DocController {

    private final ReportService reportService;

    @GetMapping("/kp")
    public void getKp(HttpServletResponse response,
                      @RequestParam Long orderId) {
        reportService.generateKp(orderId, response);
    }

    @GetMapping("/tool-order")
    public void getToolOrder(HttpServletResponse response, @AuthenticationPrincipal Jwt jwt,
                             @RequestParam Long orderId,
                             @RequestParam(required = false) String body) {
        var feId = jwt.getClaim("sub").toString();
        reportService.generateToolOrder(response, feId, orderId, body);
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