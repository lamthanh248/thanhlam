package com.code.jpa.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private String empNo;
    private String empName;
    private String position;
}

