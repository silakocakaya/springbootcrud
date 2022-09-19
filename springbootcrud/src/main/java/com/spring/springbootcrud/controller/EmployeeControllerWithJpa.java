package com.spring.springbootcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springbootcrud.entity.Employee;
import com.spring.springbootcrud.service.EmployeeService;

@RestController
@RequestMapping("/apijpa")
public class EmployeeControllerWithJpa {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{theId}")
	public Employee findById(@PathVariable int theId) {
		Employee employee = employeeService.findById(theId);
		if(employee == null) {
			throw new RuntimeException("Id bulunamadi: " + theId);
		}
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.update(employee);
		return employee;
	}
	
	@DeleteMapping("/employees/{theId}")
	public String deleteEmployee(@PathVariable int theId) {
		employeeService.deleteById(theId);
		
		return "Employee deleted Id: " + theId;
	}
}
