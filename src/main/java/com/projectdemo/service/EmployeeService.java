package com.projectdemo.service;

import java.util.List;
import com.projectdemo.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmployee();

	EmployeeDto getEmployeeById(long id);

	EmployeeDto updateEmployee(EmployeeDto employeeDto, long id);

}
