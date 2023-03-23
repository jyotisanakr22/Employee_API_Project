package com.projectdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
