package ru.erp.sfsb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.erp.sfsb.model.Dictionary;
import ru.erp.sfsb.service.DictionaryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/dictionary")
    public ResponseEntity<List<Dictionary>> getAllDictionaryFields() {
        return ResponseEntity.ok().body(dictionaryService.getAll());
    }
}