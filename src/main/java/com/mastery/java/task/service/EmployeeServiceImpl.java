package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDAO;
import com.mastery.java.task.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {
        employeeDAO.addNewEmployee(employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        employeeDAO.updateEmployee(employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeDto deleteEmployee(Long id) {
       return employeeDAO.deleteEmployee(id);
    }

}