package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDAO;
import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.entity.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @Autowired
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;
    List<EmployeeDto> employeeDtoList;

    @BeforeEach
    public void setUp() {
        employeeDtoList = new ArrayList<>();
        employeeDto1 = new EmployeeDto(1L, "Tina", "Brinkley", 10,
                "Media Manager", Gender.FEMALE, "11-02-1980");
        employeeDto2 = new EmployeeDto(2L, "Cass", "Gerger", 20,
                "Legal Assistant", Gender.MALE, "20-08-1995");
        employeeDtoList.add(employeeDto1);
        employeeDtoList.add(employeeDto2);
    }

    @AfterEach
    public void tearDown() {
        employeeDto1 = employeeDto2 = null;
        employeeDtoList = null;
    }

    @Test
    public void givenGetAllEmployeesShouldReturnListOfAllEmployees() {
        employeeDAO.addNewEmployee(employeeDto1);
        Mockito.when(employeeDAO.getAllEmployees()).thenReturn(employeeDtoList);
        List<EmployeeDto> employeeDtoList1 = employeeService.getAllEmployees();
        assertEquals(employeeDtoList1, employeeDtoList);
        Mockito.verify(employeeDAO, times(1)).addNewEmployee(employeeDto1);
        Mockito.verify(employeeDAO, times(1)).getAllEmployees();
    }

    @Test
    public void givenGetEmployeeShouldReturnEmployeeOfThatId() {
        Mockito.when(employeeService.getEmployee(1L)).thenReturn(employeeDto1);
        assertThat(employeeService.getEmployee(employeeDto1.getEmployeeId()), is(employeeDto1));
    }

    @Test
    void givenAddNewEmployeeShouldReturnAddedProduct() {
        Mockito.when(employeeDAO.addNewEmployee(employeeDto1)).thenReturn(employeeDto1);
        employeeService.addNewEmployee(employeeDto1);
        Mockito.verify(employeeDAO, times(1)).addNewEmployee(employeeDto1);
    }

    @Test
    void updateEmployee() {
        Mockito.when(employeeDAO.updateEmployee(employeeDto1)).thenReturn(employeeDto1);
        employeeService.updateEmployee(employeeDto1);
        Mockito.verify(employeeDAO, times(1)).updateEmployee(employeeDto1);
    }

    @Test
    public void givenDeleteEmployeeShouldDeleteEmployee() {
        Mockito.when(employeeService.deleteEmployee(employeeDto1.getEmployeeId())).thenReturn(employeeDto1);
        employeeService.deleteEmployee(employeeDto1.getEmployeeId());
        Mockito.verify(employeeDAO, times(1)).deleteEmployee(employeeDto1.getEmployeeId());
    }



}