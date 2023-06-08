package ru.erp.sfsb.dto;

import lombok.*;

import java.time.Duration;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalToolDto extends AbstractDto {

    private String toolName;
    private WorkpieceDto workpiece;
    private Duration processTime;
}