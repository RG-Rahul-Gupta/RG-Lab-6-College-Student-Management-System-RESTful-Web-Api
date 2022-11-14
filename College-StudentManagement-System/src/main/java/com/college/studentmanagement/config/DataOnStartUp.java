package com.college.studentmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.college.studentmanagement.service.UserService;

@Configuration
public class DataOnStartUp {
	
	@Autowired
	private UserService userService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void addDefaultData(ApplicationReadyEvent event){
		userService.createDefaultAdmin();
	}

}
