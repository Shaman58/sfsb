package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SpecialToolDto extends AbstractDto {

    private String toolName;
    private WorkpieceDto workpiece;
    private Duration processTime;
}