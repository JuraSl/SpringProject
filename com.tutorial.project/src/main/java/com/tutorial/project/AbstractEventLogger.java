package com.tutorial.project;

public abstract class AbstractEventLogger implements EventLogger {

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		 return name;
   }
	
	
}
