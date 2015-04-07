package com.midwives.classes;

import java.util.Date;

public class ClinicCalendar {
	String dateString;
	Date date;
	
	public ClinicCalendar(Date date, String day){
		this.date=date;
		this.dateString=day;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
