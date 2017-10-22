/**
 * Make this class as configuration, creates beans Date, DateFormat and Client through property file.
 * It's used instead .xml configuration file.
 * Using @Autowired to get dependencies
 */
package com.tutorial.project;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

	@Autowired
	Environment environment;
	
	@Bean
	public Date newDate(){
		return new Date();
	}
	
	@Bean
	public DateFormat dateFormat(){
		return DateFormat.getDateTimeInstance();
	}
	
	@Bean
	public Client newClient(){
		Client client = new Client();
		client.setId(environment.getRequiredProperty("id"));
		client.setName(environment.getRequiredProperty("name"));
		client.setGreeting(environment.getProperty("greeting"));
		return client;
	}
}
