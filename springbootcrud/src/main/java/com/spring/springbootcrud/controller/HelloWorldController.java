package com.spring.springbootcrud.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RestController
public class HelloWorldController {

	private MessageSource messageSource;
	
	@GetMapping("/goodMorning")
	public String goodMorning() {
		
		Locale locale = LocaleContextHolder.getLocale();
		System.out.println(locale.getDisplayLanguage());
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
	}
}
