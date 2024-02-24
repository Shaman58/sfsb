package ru.erp.sfsb.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.erp.sfsb.dto.CommercialProposalDto;
import ru.erp.sfsb.service.impl.ReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doc")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/kp")
    public void getKp(HttpServletResponse response,
                      @RequestParam(value = "orderId") Long orderId,
                      @RequestParam(value = "companyId", required = false, defaultValue = "1") Long companyId) {
        reportService.generateCp(orderId, companyId, response);
    }

    @PostMapping("/kp/remote")
    public void getKp(
            HttpServletResponse response,
            @RequestParam(value = "companyId") Long companyId,
            @RequestParam(value = "applicationNumber") Long applicationNumber,
            @RequestBody CommercialProposalDto commercialProposal) {
        reportService.generateCp(
                commercialProposal.getBodyData(),
                commercialProposal.getItemList(),
                companyId,
                applicationNumber,
                response);
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