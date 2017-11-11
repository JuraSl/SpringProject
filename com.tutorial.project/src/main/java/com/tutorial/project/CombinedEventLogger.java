package com.tutorial.project;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@SuppressWarnings("restriction")
@Component
public class CombinedEventLogger extends AbstractEventLogger{

	// JDK Annotation using just on fields and setters(name's bean state)
	@Resource(name="combinedLoggers")
	private final Collection<EventLogger> loggers;
	
	private String name;

	public String getName() {
		return name;
	}

	public CombinedEventLogger(Collection<EventLogger> loggers) {
		super();
		this.loggers = loggers;
	}
	
	public void logEvent(Event event) {
		for(EventLogger eventLogger: loggers){
			eventLogger.logEvent(event);
		}
	}
	
	public Collection<EventLogger> getLoggers(){
		return Collections.unmodifiableCollection(loggers);
	}

}
