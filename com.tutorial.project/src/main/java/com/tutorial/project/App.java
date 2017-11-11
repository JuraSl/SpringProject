package com.tutorial.project;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SuppressWarnings({ "restriction", "unused" })
@Service
public class App {
	
	// injecting Client bean
	@Autowired
    private Client client;
	
	@Resource(name="defaultLogger")
	private EventLogger defaultLogger;
	
	@Resource(name="loggerMap")
	private Map<EventType, EventLogger> loggers;

	private String startupMessage;
	
	public App() {}
	
	App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers){
		this.client = client;
		this.defaultLogger = eventLogger;
		this.loggers = loggers;
	};
	
	public static void main(String[] args) {
		
		// Create spring container object
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class, LoggerConfig.class);
		ctx.scan("com.tutorial.project");
		ctx.refresh();
		*/
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
	    // Get bean from container
	    App app = (App) ctx.getBean("app");
	    
	    System.out.println(app.startupMessage);
		
	    Client client = ctx.getBean(Client.class);
	    System.out.println("Client says: " + client.getGreeting());
	    
	    Event event = ctx.getBean(Event.class);
	    app.logEvent(EventType.ERROR, event, "Some event for 1");
	   
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
	
	

	public void setStartupMessage(String startupMessage) {
		this.startupMessage = startupMessage;
	}
}
