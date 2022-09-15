package com.spring.springbootcrud.service;

import java.util.List;

import com.spring.springbootcrud.entity.Employee;

public interface IEmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void update(Employee theEmployee);
	
	public void deleteById(int theId);
}
