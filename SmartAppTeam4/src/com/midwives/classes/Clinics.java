package com.midwives.classes;

import java.util.Date;

public class Clinics {
	
	private int clinicId, appointmentInterval;
	private String clinicName, clinicAddress, recurrence,type;
	private Date openingTime, closingTime;
	private Days openDays;
	private int [][] serviceOptionIds;
	private Announcements announcement;
	
	Clinics(){}
	Clinics(int id, String name, String address, Date openTime, Date closeTime,String recurrence,
			String type, int appointmentInterval, Days day, int [][] serviceOption){
		
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
	public String getRecurrence() {
		return this.recurrence;
	}
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	public Date getOpeningTime() {
		return this.openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}
	public Date getClosingTime() {
		return this.closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
	public Days getOpenDays() {
		return this.openDays;
	}
	public void setOpenDays(Days openDays) {
		this.openDays = openDays;
	}
	public int[][] getServiceOptionIds() {
		return this.serviceOptionIds;
	}
	public void setServiceOptionIds(int[][] serviceOptionIds) {
		this.serviceOptionIds = serviceOptionIds;
	}
	public Announcements getAnnouncement() {
		return this.announcement;
	}
	public void setAnnouncement(Announcements announcement) {
		this.announcement = announcement;
	}
	
	
	
	
	

}
