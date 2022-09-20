package com.spring.springbootcrud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersioningController {

	@GetMapping("/v1/person")
	@ResponseStatus(code = HttpStatus.ACCEPTED) //Default status changed
	public String getFirstVersionOfPerson() {
		return new String("Bob Charlie");
	}

	@GetMapping("/v2/person")
	public String getSecondVersionOfPerson() {
		return new String("Bob and Charlie");
	}

	@GetMapping(path = "/person", params = "version=1")
	public String getFirstVersionOfPersonRequestParameter() {
		return new String("Bob Charlie");
	}

	@GetMapping(path = "/person", params = "version=2")
	public String getSecondVersionOfPersonRequestParameter() {
		return new String("Bob and Charlie");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public String getFirstVersionOfPersonRequestHeader() {
		return new String("Bob Charlie");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public String getSecondVersionOfPersonRequestHeader() {
		return new String("Bob and Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public String getFirstVersionOfPersonAcceptHeader() {
		return new String("Bob Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public String getSecondVersionOfPersonAcceptHeader() {
		return new String("Bob and Charlie");
		
	}
	
}
