package com.tutorial.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SuppressWarnings({ "unused", "restriction" })
// defining bean as Component
@Component
public class CacheFileEventLogger extends FileEventLogger{
	
	// Use system property cache.size or 5 if property is not set
	@Value("${cache.size:5}")
	private int cacheSize;
	private List<Event> cache ;

	public CacheFileEventLogger() {}
	
	public CacheFileEventLogger(String filename, int cacheSize) {
		super(filename);
		this.cacheSize = cacheSize;
	}
	
	// Being calling when context is closing
	// write all contents to the file from cache
	@PreDestroy    
	public void destroy(){
		if(!cache.isEmpty()){
			writeEventsFromCache();
		}
	}
	
	@PostConstruct
	public void initCache() {
	    this.cache = new ArrayList<Event>(cacheSize);
	}
	
	@Override
	public void logEvent(Event event) {
		cache.add(event);
		if(cacheSize == cache.size()){
			writeEventsFromCache();
			cache.clear();
		}
	}
		
	// send all cache contents to the file
	private void writeEventsFromCache() {
		for(Event e: cache){
			super.logEvent(e);
		}
	}
}
