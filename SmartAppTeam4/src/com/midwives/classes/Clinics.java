package com.midwives.classes;

import java.util.Date;  //Chris 

public class Clinics {
	
	private int clinicId, appointmentInterval;
	private String clinicName, clinicAddress, type;

	private String  openingTime, closingTime;	   //changed from type Date to type string  - chris
	private String[] openDays = new String[7];     // changed  from type Days to type String array of type Days.name -chris
	private Recurrence recurrence;
	private int [] serviceOptionIds;
	private Announcements announcement;
	private String week;//test stuff
	
	Clinics(){}

//	// midwives constructor display just clinic name, clinic address, recurrence, and open days
//	public Clinics (int id, String name, String address, Recurrence reccurence, String[] days){ 
//
//		this.clinicId=id;
//		this.clinicName=name;
//		this.clinicAddress=address;
//		this.openDays=days;
//		this.recurrence=reccurence;
//		
//	}

	//constructor used for parsing data!!!
	public Clinics(int id, String name, String address, String openTime, String closeTime,Recurrence recurrence,
			String type, int appointmentInterval, String[] day, int [] serviceOption){		
		this.clinicId=id;
		this.clinicName=name;
		this.clinicAddress=address;
		this.openingTime=openTime;
		this.closingTime=closeTime;
		this.recurrence=recurrence;
		this.type=type;
		this.appointmentInterval=appointmentInterval;
		this.openDays=day;
		this.serviceOptionIds=serviceOption;
		
	}
	
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public int getAppointmentInterval() {
		return this.appointmentInterval;
	}
	public void setAppointmentInterval(int appointmentInterval) {
		this.appointmentInterval = appointmentInterval;
	}
	public String getClinicName() {
		return this.clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getClinicAddress() {
		return this.clinicAddress;
	}
	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}
	public Recurrence getRecurrence() {
		return this.recurrence;
	}
	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}
	public String getOpeningTime() {
		return this.openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return this.closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public String[] getOpenDays() {
		return this.openDays;
	}
	public void setOpenDays(String[] openDays) {
		this.openDays = openDays;
	}
	public int[] getServiceOptionIds() {
		return this.serviceOptionIds;
	}
	public void setServiceOptionIds(int[] serviceOptionIds) {
		this.serviceOptionIds = serviceOptionIds;
	}
	public Announcements getAnnouncement() {
		return this.announcement;
	}
	public void setAnnouncement(Announcements announcement) {
		this.announcement = announcement;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
} //close class clinics
