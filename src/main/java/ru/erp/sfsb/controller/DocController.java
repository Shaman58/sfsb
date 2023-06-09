package ru.erp.sfsb.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.erp.sfsb.service.impl.KPService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doc")
public class DocController {

    private final KPService kpService;

    @GetMapping("/kp")
    public void getKp(HttpServletResponse response, @RequestParam Long orderId) {
        kpService.generateKp(orderId, response);
    }
}