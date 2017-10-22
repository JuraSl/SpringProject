package com.tutorial.project;

import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger{

	public void logEvent(String msg) {
		System.out.println(msg);
		
	}

	public void logEvent(Event event) {
		
		System.out.println(event.toString());
	}
}
