package com.spring.springbootcrud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.springbootcrud.entity.Employee;

@Repository
public class EmployeeDAOJpa implements IEmployeeDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Employee> findAll() {
		
		Query query = entityManager.createQuery("from Employee");
		return query.getResultList();
	}

	@Override
	public Employee findById(int theId) {
		return entityManager.find(Employee.class, theId);
	}

	@Override
	public void save(Employee theEmployee) {
		entityManager.merge(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		
		Employee employee = findById(theId);
		
		if(employee == null) {
			throw new RuntimeException("Id bulunamadi: " + theId);
		}
		entityManager.remove(employee);
	}

}
