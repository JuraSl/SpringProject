package com.tutorial.project;

import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger extends AbstractEventLogger{

	private String name;
	
	public void logEvent(String msg) {
		System.out.println(msg);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void logEvent(Event event) {
		
		System.out.println(event.toString());
	}
}
