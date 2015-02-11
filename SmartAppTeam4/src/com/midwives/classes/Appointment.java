package com.midwives.classes;

import java.util.Date;

public class Appointment {
	
	private int appointmentId, serviceProviderId, serviceUserId, clinicId;
	private Date appointmentDate, appointmentTime;
	private Priority priority;
	private VisitType visitType;
	private int serviceOptionId;
	// new class Links
	private Links links;
//	private ServiceOptions links;
	// fake stuff
	private String appDate, appTime;
	
	public Appointment(){} //used when is empty slot
	
	//a new constructor with Links class
	public Appointment(String date, int appId, int serviceId, int userId, Priority priority, String time, 
			VisitType visitType, int clinicId, Links links){
		this.appDate=date;
		this.appointmentId=appId;
		this.serviceProviderId=serviceId;
		this.serviceUserId=userId;
		this.priority=priority;
		this.appTime=time;
		this.visitType=visitType;
		this.clinicId=clinicId;
		this.links=links;
	}
	
	// constructor for test only Dates as String...............
	public Appointment(String date, int appId, int serviceId, int userId, Priority priority, String time, 
			VisitType visitType, int clinicId){
		this.appDate=date;
		this.appointmentId=appId;
		this.serviceProviderId=serviceId;
		this.serviceUserId=userId;
		this.priority=priority;
		this.appTime=time;
		this.visitType=visitType;
		this.clinicId=clinicId;
		
	}
	
	public Links getLinks(){
		return links;
	}
	public void setLinks(Links links){
		this.links=links;
	}
	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

//	public Appointment(int id, Date date, Date time,int provider,int user,Priority priority, VisitType visit,
//			int option,int clinic, ServiceOptions link){
//		
//		this.appointmentId=id;
//		this.appointmentDate=date;
//		this.appointmentTime=time;
//		this.serviceUserId=user;
//		this.priority=priority;
//		this.serviceProviderId=provider;
//		this.visitType=visit;
//		this.serviceOptionId=option;
//		this.clinicId=clinic;
//		this.links=link;
//		
//	}
	
//	public ServiceOptions getLinks() {
//		return this.links;
//	}
//	public void setLinks(ServiceOptions links) {
//		this.links = links;
//	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getServiceProviderId() {
		return this.serviceProviderId;
	}
	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	public int getServiceUserId() {
		return this.serviceUserId;
	}
	public void setServiceUserId(int serviceUserId) {
		this.serviceUserId = serviceUserId;
	}
	public Date getAppointmentDate() {
		return this.appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Date getAppointmentTime() {
		return this.appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Priority getPriority() {
		return this.priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public VisitType getVisitType() {
		return this.visitType;
	}
	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}
	public int getServiceOptionId() {
		return this.serviceOptionId;
	}
	public void setServiceOptionId(int serviceOptionId) {
		this.serviceOptionId = serviceOptionId;
	}

	
}
