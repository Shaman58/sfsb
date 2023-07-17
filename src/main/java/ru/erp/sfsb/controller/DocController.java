package ru.erp.sfsb.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.erp.sfsb.service.impl.DocService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doc")
public class DocController {

    private final DocService docService;

    @GetMapping("/kp")
    public void getKp(HttpServletResponse response,
                      @RequestParam Long orderId) {
        docService.generateKp(orderId, response);
    }

    @GetMapping("/tool-order")
    public void getToolOrder(HttpServletResponse response,
                             @RequestParam Long teId,
                             @RequestParam Long feId,
                             @RequestParam Long orderId,
                             @RequestParam(required = false) String body) {
        docService.generateToolOrder(response, teId, feId, orderId, body);
    }
}