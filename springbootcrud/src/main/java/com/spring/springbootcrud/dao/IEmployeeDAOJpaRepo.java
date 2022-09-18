package com.spring.springbootcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.springbootcrud.entity.Employee;

@RepositoryRestResource(path = "members")
public interface IEmployeeDAOJpaRepo extends JpaRepository<Employee, Integer>{

}
