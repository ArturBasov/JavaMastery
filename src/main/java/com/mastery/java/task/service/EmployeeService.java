package com.mastery.java.task.service;

import com.mastery.java.task.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto getEmployee(Long id);

    public EmployeeDto addNewEmployee(EmployeeDto employeeDto);

    public EmployeeDto updateEmployee(EmployeeDto employeeDto);

    public EmployeeDto deleteEmployee(Long id);
}
