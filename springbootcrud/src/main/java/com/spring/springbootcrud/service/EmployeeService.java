package com.spring.springbootcrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springbootcrud.dao.EmployeeDAO;
import com.spring.springbootcrud.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		//Id gonderilirse update etmesin diye, id 0 ise insert eder
		theEmployee.setId(0);
		employeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void update(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

}
