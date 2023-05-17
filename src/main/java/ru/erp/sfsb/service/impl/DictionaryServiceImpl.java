package ru.erp.sfsb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.model.Dictionary;
import ru.erp.sfsb.repository.DictionaryRepository;
import ru.erp.sfsb.service.DictionaryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {


    private final DictionaryRepository dictionaryRepository;

    @Override
    public List<Dictionary> getAll() {
        log.info("Looking all dictionary fields in DB");
        return dictionaryRepository.findAll();
    }
}