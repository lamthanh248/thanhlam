package com.code.jpa.service.impl;

import com.code.jpa.dto.EmployeeDto;
import com.code.jpa.entity.Employee;
import com.code.jpa.mapper.EmployeeMapper;
import com.code.jpa.repository.EmployeeRepository;
import com.code.jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeeMapper mapper;

    @Override
    public EmployeeDto getEmployee(String empNo) {
        if (empNo != null) {
            Employee employee = repository.findById(empNo).orElse(new Employee());
            return mapper.convertEntityToDTO(employee);
        }
        return new EmployeeDto();
    }
//    @Override
//    public UserDTO findById(Long id) {
//        Optional<User> user = repository.findById(id);
//        return user != null && user.isPresent() ? mapper.convertEntityToDTO(user.get())
//                : new UserDTO();
//    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto emp) {
        if (emp != null) {
            Employee employee = mapper.convertDTOToEntity(emp);
            return mapper.convertEntityToDTO(repository.save(employee));
        }
        return new EmployeeDto();
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto emp) {
        if (emp != null) {
            Optional<Employee> find = repository.findById(emp.getEmpNo());
            if (find.isPresent()) {
                Employee employee = mapper.convertDTOToEntity(emp);
                repository.updateByEmpNo(employee.getEmpName(), employee.getPosition(), employee.getEmpNo());
                return mapper.convertEntityToDTO(employee);
            }
            return new EmployeeDto();
        }
        return new EmployeeDto();
    }

    @Override
    @Transactional
    public void deleteEmployee(EmployeeDto emp) {
        if (emp != null) {
            Employee employee = mapper.convertDTOToEntity(emp);
            repository.deleteAllByEmpName(employee.getEmpName());
            mapper.convertEntityToDTO(employee);
        }
    }
    @Override
    public boolean delete(EmployeeDto employeeDTO) {
        try {
            if (employeeDTO == null || employeeDTO.getEmpNo() == null) return false;
            Optional<Employee> employee = repository.findById(employeeDTO.getEmpNo());
            if (employee.isPresent()) {
                //delete
                repository.delete(employee.get());
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = repository.findAll();
        return (employees != null && employees.size() > 0) ?
                employees.stream()
                        .map(obj -> mapper.convertEntityToDTO(obj))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }

    @Override
    public List<EmployeeDto> findByName(String name) {
        List<Employee> employees = repository.findAllByEmpName(name);
        return (employees != null && employees.size() > 0) ?
                employees.stream()
                        .map(obj -> mapper.convertEntityToDTO(obj))
                        .collect(Collectors.toList())
                : new ArrayList<>();
    }
}
