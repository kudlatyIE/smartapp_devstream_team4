package com.midwives.classes;

import java.util.Calendar;

public class ClinicCalendar {
	Calendar calendar;
	String dateString;
	
	public ClinicCalendar(Calendar calendar, String day){
		this.calendar=calendar;
		this.dateString=day;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

}
