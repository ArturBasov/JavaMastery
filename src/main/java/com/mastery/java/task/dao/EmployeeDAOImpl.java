package com.mastery.java.task.dao;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final EmployeeConverter employeeConverter;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAOImpl(EmployeeConverter employeeConverter, JdbcTemplate jdbcTemplate) {
        this.employeeConverter = employeeConverter;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        String sql = "SELECT * FROM public.employee ORDER BY employee_id ASC";
        List<EmployeeDto> allEmployees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class))
                .stream()
                .map(employeeConverter::fromEmployeeToEmployeeDto)
                .collect(Collectors.toList());
        return allEmployees;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        String sql = "SELECT * FROM public.employee WHERE employee_id = ?";
        try {
            Employee employee = (Employee) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper(Employee.class));
            return employeeConverter.fromEmployeeToEmployeeDto(employee);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.fromEmployeeDtoToEmployee(employeeDto);

        String sql = "INSERT INTO public.employee " +
                "(first_name, last_name, department_id, job_title, gender, date_of_birth)" +
                "values (?,?,?,?,?,TO_DATE(?, 'yyyy-mm-dd'))";
        jdbcTemplate.update(sql,
                new Object[]{employee.getFirstName(), employee.getLastName(),
                        employee.getDepartmentId(), employee.getJobTitle(), String.valueOf(employee.getGender()),
                        employee.getDateOfBirth()});
        return employeeConverter.fromEmployeeToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        try {
            Employee employee = employeeConverter.fromEmployeeDtoToEmployee(employeeDto);
            String sql = "UPDATE public.employee " +
                    "SET first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?, " +
                    "date_of_birth = TO_DATE(?, 'yyyy-mm-dd') " +
                    "WHERE employee_id = ?";
            jdbcTemplate.update(sql,
                    new Object[]{employee.getFirstName(), employee.getLastName(),
                            employee.getDepartmentId(), employee.getJobTitle(), String.valueOf(employee.getGender()),
                            employee.getDateOfBirth(), employee.getEmployeeId()});
            return employeeConverter.fromEmployeeToEmployeeDto(employee);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EmployeeDto deleteEmployee(Long id) {
        try {
            String sql1 = "SELECT * FROM public.employee WHERE employee_id = ?";
            Employee employee = (Employee) jdbcTemplate.queryForObject(
                    sql1,
                    new Object[]{id},
                    new BeanPropertyRowMapper(Employee.class));
            String sql2 = "DELETE FROM public.employee WHERE employee_id = ?";
            jdbcTemplate.update(sql2, new Object[]{id});
            return employeeConverter.fromEmployeeToEmployeeDto(employee);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
