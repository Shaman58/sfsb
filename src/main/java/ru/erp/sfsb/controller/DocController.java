package ru.erp.sfsb.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public void getToolOrder(HttpServletResponse response,
                             @RequestParam Long teId,
                             @RequestParam Long feId,
                             @RequestParam Long orderId,
                             @RequestParam(required = false) String body) {
        reportService.generateToolOrder(response, teId, feId, orderId, body);
    }

    @GetMapping("/calculate")
    public void calculateItem(@RequestParam Long itemId) {
        reportService.calculateItem(itemId);
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