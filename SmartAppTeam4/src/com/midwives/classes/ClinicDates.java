package com.midwives.classes;

public class ClinicDates {
	
	private String appointmentDate, weekDay, clinicName;
	
	//temporary constructor, uset to pass data from ClinicDate to AppointmentCalendar
	public ClinicDates(String date, String day, String name){
		this.appointmentDate=date;
		this.weekDay=day;
		this.clinicName=name;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

}
