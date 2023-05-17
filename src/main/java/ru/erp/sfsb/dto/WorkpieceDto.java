package ru.erp.sfsb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkpieceDto extends AbstractDto {

    private MaterialDto materialDto;
    private String geometry;
    private Integer geom1;
    private Integer geom2;
    private Integer geom3;
}