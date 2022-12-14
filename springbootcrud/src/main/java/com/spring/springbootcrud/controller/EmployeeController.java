package com.spring.springbootcrud.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.springbootcrud.entity.Employee;
import com.spring.springbootcrud.exception.EmployeeNotFoundException;
import com.spring.springbootcrud.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

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
			throw new EmployeeNotFoundException("Id bulunamadi: " + theId);
		}
		
		EntityModel<Employee> entityModel = EntityModel.of(employee);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
		entityModel.add(link.withRel("all-users"));
		
		return employee;
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		Employee employeeSaved = employeeService.saveThenReturn(employee);
		
		System.out.println(employeeSaved.getId());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(employeeSaved.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
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
	
	@GetMapping("/filtering") //field2
	public MappingJacksonValue filtering() {
		
		Employee employee = employeeService.findById(1);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employee);
		//eposta disindakileri getirmez
		SimpleBeanPropertyFilter filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("eposta");
		
		FilterProvider filters = 
				new SimpleFilterProvider().addFilter("EmployeeEpostaFilter", filter);
		
		mappingJacksonValue.setFilters(filters );
		
		return mappingJacksonValue;
	}
}
