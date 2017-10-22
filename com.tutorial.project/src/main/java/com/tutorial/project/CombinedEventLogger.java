package com.tutorial.project;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@SuppressWarnings("restriction")
@Component
public class CombinedEventLogger implements EventLogger{

	
	@Resource(name="combinedLoggers")
	private Collection<EventLogger> loggers;
	
	CombinedEventLogger(Collection<EventLogger> loggers){
		super();
		this.loggers = loggers;
	}
	public void logEvent(Event event) {
		for(EventLogger eventLogger: loggers){
			eventLogger.logEvent(event);
		}
	}

}
