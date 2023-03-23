package com.projectdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectdemo.dto.EmployeeDto;
import com.projectdemo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDto employeeDto){
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);	
	}
	
	@GetMapping("/list")
	public List<EmployeeDto>getAllEmployee(){
		List<EmployeeDto> employeeDto = employeeService.getAllEmployee();
		return employeeDto;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto>getEmployeeById(@PathVariable("id")long id){
		return ResponseEntity.ok(employeeService.getEmployeeById(id));	
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable("id")long id){
		EmployeeDto dto = employeeService.updateEmployee(employeeDto, id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}