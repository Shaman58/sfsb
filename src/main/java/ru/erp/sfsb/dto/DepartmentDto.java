package ru.erp.sfsb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto extends AbstractDto {

    @NotBlank(message = "Имя не может быть пустым")
    private String departmentName;
    private CompanyDto company;
    @JsonManagedReference
    private List<EmployeeDto> employees;
}