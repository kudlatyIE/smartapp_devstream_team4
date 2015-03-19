package com.midwives.classes;

import java.util.Calendar;
import java.util.Date;

public class ClinicCalendar {
	Calendar calendar;
	String dateString;
	Date date;
	
//	public ClinicCalendar(Calendar calendar, String day){
//		this.calendar=calendar;
//		this.dateString=day;
//	}
	public ClinicCalendar(Date date, String day){
		this.date=date;
		this.dateString=day;
	}

//	public Calendar getCalendar() {
//		return calendar;
//	}
//
//	public void setCalendar(Calendar calendar) {
//		this.calendar = calendar;
//	}

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
