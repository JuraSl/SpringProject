package com.tutorial.project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
    private Client client;
	private EventLogger eventLogger;
	
	App(Client client, EventLogger eventLogger){
		super();
		this.client = client;
		this.eventLogger = eventLogger;
	};
	
	public static void main(String[] args) {
		
		// Create spring container object
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
	    // Get bean from container
	    App app = (App) ctx.getBean("app");
		
	    Event event = ctx.getBean(Event.class);
	    app.logEvent(event, "Some event for 1");
	   
	    event = ctx.getBean(Event.class);
	    app.logEvent(event, "Some event for 2");
		}
		
	private void logEvent(Event event, String msg) {
		String message = msg.replaceAll(client.getId(), client.getName());
		event.setMsg(message);
		eventLogger.logEvent(event);
	}
}
