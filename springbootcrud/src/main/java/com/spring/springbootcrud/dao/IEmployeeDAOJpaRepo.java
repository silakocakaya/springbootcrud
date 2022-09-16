package com.spring.springbootcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springbootcrud.entity.Employee;

public interface IEmployeeDAOJpaRepo extends JpaRepository<Employee, Integer>{

}
