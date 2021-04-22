package com.mastery.java.task.service;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    public Employee fromEmployeeDtoToEmployee(EmployeeDto usersDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(usersDto.getEmployeeId());
        employee.setFirstName(usersDto.getFirstName());
        employee.setLastName(usersDto.getLastName());
        employee.setDepartmentId(usersDto.getDepartmentId());
        employee.setJobTitle(usersDto.getJobTitle());
        employee.setGender(usersDto.getGender());
        employee.setDateOfBirth(usersDto.getDateOfBirth());
        return employee;
    }

    public EmployeeDto fromEmployeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .departmentId(employee.getDepartmentId())
                .jobTitle(employee.getJobTitle())
                .gender(employee.getGender())
                .dateOfBirth(employee.getDateOfBirth())
                .build();
    }
}
