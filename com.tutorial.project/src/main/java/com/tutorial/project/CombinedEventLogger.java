package com.tutorial.project;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger{

	private final Collection<EventLogger> loggers;
	
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
