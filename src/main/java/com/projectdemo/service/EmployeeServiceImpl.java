package com.projectdemo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projectdemo.dto.EmployeeDto;
import com.projectdemo.entity.Employee;
import com.projectdemo.exception.ResourceNotFoundException;
import com.projectdemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Employee mapToEntity(EmployeeDto employeeDto) {
		Employee employee = mapper.map(employeeDto, Employee.class);
		return employee;
	}
	
	public EmployeeDto mapToDto(Employee employee) {
		EmployeeDto dto = mapper.map(employee, EmployeeDto.class);
		return dto;
	}
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = mapToEntity(employeeDto);
		Employee employeeEntity = employeeRepository.save(employee);
		EmployeeDto dto = mapToDto(employeeEntity);
		return dto;
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployeeById(long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee","id",id));
		EmployeeDto employeeDto = mapToDto(employee);
		return employeeDto;
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee","id",id));
		employee.setName(employeeDto.getName());
		employee.setEmail(employeeDto.getEmail());
		employee.setCompany(employeeDto.getCompany());
		Employee save = employeeRepository.save(employee);
		return mapToDto(save);
	}
}