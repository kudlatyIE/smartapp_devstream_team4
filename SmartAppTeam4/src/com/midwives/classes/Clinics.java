package com.midwives.classes;

import java.util.Date;

public class Clinics {
	
	private int clinicId, appointmentInterval;
	private String clinicName, clinicAddress, type;
	private Date openingTime, closingTime;
	private Days openDays;
	private Recurrence recurrence;
	private int[] serviceOptionIds;
	private Announcements announcement;
	private String week;//test stuff
	
	Clinics(){}
	
	
	public Clinics(String clinicName, String week){//for test only!!!!!!!!!!
		this.clinicName=clinicName;
		this.week=week;
	}
	public String getWeek() {//test shit
		return week;
	}


	public void setWeek(String week) {//test shit
		this.week = week;
	}


	/**
	 * Contractor for test only
	 * @param id
	 * @param name
	 * @param address
	 * @param reccurence
	 * @param days
	 */
	public Clinics (int id, String name, String address, Recurrence reccurence, Days days){ // short, for test only!
		this.clinicId=id;
		this.clinicName=name;
		this.clinicAddress=address;
		this.openDays=days;
		this.recurrence=reccurence;
		
	}
	public Clinics(int id, String name, String address, Date openTime, Date closeTime,Recurrence recurrence,
			String type, int appointmentInterval, Days day, int [] serviceOption){
		
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
	
	
	
	
	

}
