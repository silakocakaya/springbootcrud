package com.spring.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springbootcrud.dao.IEmployeeDAOJpaRepo;
import com.spring.springbootcrud.entity.Employee;

@Service
public class EmployeeServiceWithJpaRepo implements IEmployeeService {

	@Autowired
	IEmployeeDAOJpaRepo employeeDAOJpaRepo;
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAOJpaRepo.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		Optional<Employee> optional = employeeDAOJpaRepo.findById(theId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new RuntimeException("Employee bulunamadi: " + theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		//Id gonderilirse update etmesin diye, id 0 ise insert eder
		theEmployee.setId(0);
		employeeDAOJpaRepo.save(theEmployee);
	}

	@Override
	@Transactional
	public void update(Employee theEmployee) {
		employeeDAOJpaRepo.save(theEmployee);
	}
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAOJpaRepo.deleteById(theId);
	}

	@Override
	public Employee saveThenReturn(Employee theEmployee) {
		employeeDAOJpaRepo.save(theEmployee);
		return theEmployee;
	}

}
