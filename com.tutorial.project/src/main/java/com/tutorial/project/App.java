package com.tutorial.project;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
    private Client client;
	private EventLogger defaultLogger;
	private Map<EventType, EventLogger> loggers;
	
	App(Client client, CacheFileEventLogger eventLogger, Map<EventType, EventLogger> loggers){
		super();
		this.client = client;
		this.defaultLogger = eventLogger;
		this.loggers = loggers;
	};
	
	public static void main(String[] args) {
		
		// Create spring container object
		// this context has method registerShutdownHook() that invokes method close()
	    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
	    // Get bean from container
	    App app = (App) ctx.getBean("app");
		
	    Event event = ctx.getBean(Event.class);
	    //app.logEvent(EventType.ERROR, event, "Some event for 1");
	   
	    event = ctx.getBean(Event.class);
	    app.logEvent(EventType.INFO, event, "Some event for 2");
	    
	    event = ctx.getBean(Event.class);
	    app.logEvent(null, event, "Some event for 3");
	    
	    // closing context
	    ctx.close();
		}
		
	private void logEvent(EventType Eventtype, Event event, String msg) {
		String message = msg.replaceAll(client.getId(), client.getName());
		event.setMsg(message);
		defaultLogger.logEvent(event);
		
		EventLogger logger = loggers.get(Eventtype);
		
		if(logger == null){
		   logger = defaultLogger;
		}
		logger.logEvent(event);
	}
}
