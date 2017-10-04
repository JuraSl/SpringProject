package com.tutorial.project;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
	// create thread safe Integer object
	public static final AtomicInteger AUTO_ID = new AtomicInteger(0);
	
	private int id;
	private String msg;
	private Date date;
	private DateFormat df;
	
	Event(Date date, DateFormat df){
		this.id = AUTO_ID.getAndIncrement();
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

}
