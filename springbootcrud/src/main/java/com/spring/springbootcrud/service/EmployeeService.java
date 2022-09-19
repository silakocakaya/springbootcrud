package com.spring.springbootcrud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.springbootcrud.dao.EmployeeDAOHibernate;
import com.spring.springbootcrud.dao.IEmployeeDAO;
import com.spring.springbootcrud.entity.Employee;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	@Qualifier("employeeDAOHibernate")  //employeeDAOHibernate for hibernate
	IEmployeeDAO employeeDAO;
	
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

	@Override
	public Employee saveThenReturn(Employee theEmployee) {
		theEmployee.setId(0);
		//Works with hibernate
		employeeDAO.save(theEmployee);
		
		return theEmployee;
	}

}
