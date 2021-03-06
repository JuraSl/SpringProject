package com.tutorial.project;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Event {
	// create thread safe Integer object
	public static final AtomicInteger AUTO_ID = new AtomicInteger(0);
	
	private int id;
	private String msg;
	
	@Autowired                // injecting bean by type
	@Qualifier("newDate")     // stating name of the bean (Spring annotation)
	private Date date;
	
	@Autowired
	private DateFormat df;
	
	Event(){
		this.id = AUTO_ID.getAndIncrement();
	}
	
	Event(Date date, DateFormat df){
		this();
		this.date = date;
		this.df = df;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", msg=" + msg + ", date=" + date
				+ ", dateFormat=" + df.format(date) + "]";
	}
	
	public static boolean isDay(int start, int end){
		LocalTime time = LocalTime.now();
		return time.getHour() > start && time.getHour() < end;
	}

}
