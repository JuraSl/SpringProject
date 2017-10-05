package com.tutorial.project;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
	private int cacheSize;
	private List<Event> cache ;

	public CacheFileEventLogger(String filename, int cacheSize) {
		super(filename);
		this.cacheSize = cacheSize;
		this.cache = new ArrayList<Event>(cacheSize);
	}
	
	// Being calling when context is closing
		// write all contents to the file from cache
		public void destroy(){
			if(!cache.isEmpty()){
				writeEventsFromCache();
			}
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
