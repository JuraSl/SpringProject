package com.tutorial.project;

public class Client {
	
	public Client(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private String id;
	private String name;
	private String greeting;
	
	
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
