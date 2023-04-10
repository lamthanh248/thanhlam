package com.code.jpa.controller;

import com.code.jpa.consts.ApiPath;
import com.code.jpa.dto.EmployeeDto;
import com.code.jpa.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeRestController {
    @Autowired
    EmployeeService service;

    @GetMapping(value = ApiPath.PING)
    public String ping() {
        return "Ping!!!!!!!!";
    }

    /**
     * Find all employees
     * @return
     */
    @GetMapping(value = ApiPath.EMPLOYEE_GET_ALL)
    public ResponseEntity<List<EmployeeDto>> getAll() {
        try {
            List<EmployeeDto> list = service.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error when get all user:", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get employee
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.EMPLOYEE_GET_ONE)
    public ResponseEntity<EmployeeDto> getEmployeeByID(@RequestBody EmployeeDto request) {
        try {
            if (null == request || request.getEmpNo() == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            EmployeeDto employeeDto = service.getEmployee(request.getEmpNo());
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error when get employee:", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create employee
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.EMPLOYEE_CREATE)
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto request) {
        try {
            EmployeeDto create = service.addEmployee(request);
            if (create == null ||null == request) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(create, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error when create employee:", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update employee
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.EMPLOYEE_UPDATE)
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto request) {
        try {
            EmployeeDto update = service.updateEmployee(request);
            if (update == null || null == request || request.getEmpNo() == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error when update employee:", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete employee
     * @param request
     * @return
     */
    @DeleteMapping (value = ApiPath.EMPLOYEE_DELETE)
    public ResponseEntity<EmployeeDto> deleteEmployee(@RequestBody EmployeeDto request) {
        try {
            if (null == request || request.getEmpName() == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            service.deleteEmployee(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error when delete employee:", ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //end
}
