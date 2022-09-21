package com.spring.springbootcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
//Lombok
@Getter
@Setter
@NoArgsConstructor
@ToString
//@JsonIgnoreProperties("id") //Static filtering @JsonIgnore yerine buraya da yazabiliriz
@JsonFilter("EmployeeEpostaFilter") //Dynamic filtering
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Size(min = 2, message = "First Name have at least 2 characters")
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	//Static filtering
	//@JsonIgnore //Static filtering
	private String lastName;
	
	@Email(message = "Email should be in e-mail format")
	@Column(name="email")
	@JsonProperty("eposta")
	private String email;
}
