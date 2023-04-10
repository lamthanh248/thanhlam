package com.code.jpa.controller;


import com.code.jpa.dto.EmployeeDto;
import com.code.jpa.entity.Employee;
import com.code.jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping({"/","/employee/list"})
    public String getAll(Model model) {
        try {
            List<EmployeeDto> employees = service.findAll();
            model.addAttribute("employees", employees);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "employees";
    }

    @GetMapping("/employee/new")
    public String newFormAdd(Model model) {
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee", employee);
        model.addAttribute("pageTitle", "Add New Employee");
        return "employee-create-form";
    }

    @PostMapping("/employee/save")
    public String addEmployee(Model model,@ModelAttribute("employee") EmployeeDto emp) {
        if (emp.getEmpNo() != null && emp.getEmpNo().length() >0 &&
                emp.getEmpName() != null && emp.getEmpName().length() >0 &&
                emp.getPosition() != null && emp.getPosition().length() >0) {
            service.addEmployee(emp);
            return "redirect:/employee/list";
        }
        model.addAttribute("pageTitle", "Add New Employee");
        model.addAttribute("errorMessage", errorMessage);
        return "employee-create-form";
    }


    @PostMapping("/employee/update")
    public String updateEmployee(Model model,@ModelAttribute("employee") EmployeeDto emp) {
        if (emp.getEmpName() != null && emp.getEmpName().length() > 0 &&
                emp.getPosition() != null && emp.getPosition().length() > 0) {
            service.updateEmployee(emp);
            return "redirect:/employee/list";
        }
        model.addAttribute("pageTitle", "Edit Employee (ID: " + emp.getEmpNo() + ")");
        model.addAttribute("errorMessage", errorMessage);
        return "employee-update-form";
    }

    @GetMapping("/employee/new-form-update/{empNo}")
    public String update(Model model, @PathVariable(value ="empNo") String empNo) {

        EmployeeDto employee = service.getEmployee(empNo);
        model.addAttribute("employee", employee);
        model.addAttribute("pageTitle", "Edit Employee (ID: " + empNo + ")");

        return "employee-update-form";
    }

    @GetMapping("/employee/delete/{empNo}")
    public String deleteEmployee(@PathVariable(value ="empNo") String empNo) {
        EmployeeDto employee = service.getEmployee(empNo);
        service.delete(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/employee/search-by-name")
    public String findEmployeeByName(Model model,@RequestParam String keyword) {
        List<EmployeeDto> employees = service.findByName(keyword);
        model.addAttribute("employees", employees);
        return "employees";
    }
}
