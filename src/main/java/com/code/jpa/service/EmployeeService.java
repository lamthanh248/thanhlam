package com.code.jpa.service;

import com.code.jpa.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployee(String empNo);
    EmployeeDto addEmployee(EmployeeDto emp);
    EmployeeDto updateEmployee(EmployeeDto emp);
    void deleteEmployee(EmployeeDto emp);

    boolean delete(EmployeeDto employeeDTO);

    List<EmployeeDto> findAll();
    List<EmployeeDto> findByName(String name);
}
