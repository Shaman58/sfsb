package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.OperationDto;

public interface OperationService extends Service<OperationDto> {

    OperationDto getSetupPrice();

    OperationDto updateSetupPrice(OperationDto operationDto);

    OperationDto getTechnologistPrice();

    OperationDto updateTechnologistPrice(OperationDto operationDto);
}