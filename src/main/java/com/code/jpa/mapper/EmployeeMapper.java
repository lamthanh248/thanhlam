package com.code.jpa.mapper;

import com.code.jpa.dto.EmployeeDto;
import com.code.jpa.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {
    public EmployeeMapper() {
        super(Employee.class, EmployeeDto.class);
    }
}