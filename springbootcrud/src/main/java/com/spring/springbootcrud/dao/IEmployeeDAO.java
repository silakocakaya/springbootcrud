package com.spring.springbootcrud.dao;

import java.util.List;

import com.spring.springbootcrud.entity.Employee;

public interface IEmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
}
