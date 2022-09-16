package com.spring.springbootcrud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.springbootcrud.entity.Employee;

@Repository
public class EmployeeDAOHibernate implements IEmployeeDAO {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Employee> findAll() {

		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee findById(int theId) {

		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, theId);
		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {

		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = findById(theId);
		
		if(employee == null) {
			throw new RuntimeException("Id bulunamadi: " + theId);
		}
		session.delete(employee);
	}

}
