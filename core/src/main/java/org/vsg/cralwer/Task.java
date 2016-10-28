package org.vsg.cralwer;

import java.util.Date;

public class Task {
	
	public static final byte STATE_NOTEXISTED = 0;
	
	public static final byte STATE_PENDDING = 1;
	
	public static final byte STATE_RUNNING = 2;
	
	public static final byte STATE_SUPPEND = 3;
	
	public static final byte STATE_COMPLETE = 4;
	
	/**
	 * define task name
	 */
	private String name;
	
	/**
	 * show the task current status
	 */
	private byte state;
	
	
	private Date startTime;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte getState() {
		return state;
	}


	public void setState(byte state) {
		this.state = state;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	
	

}
