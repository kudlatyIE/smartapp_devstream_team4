package com.midwives.classes;

import java.util.Date;

public class Appointment {
	
	int appointmentId, serviceProviderId, serviceUserId, clinicId;
	Date appointmentDate, appointmentTime;
	Priority priority;
	VisitType visitType;
	int[] serviceOptionId;
	ServiceOptions links;
	
	Appointment(int id, Date date, Date time,int provider,int user,Priority priority, VisitType visit,
			int[] option,int clinic, ServiceOptions link){
		
		this.appointmentId=id;
		this.appointmentDate=date;
		this.appointmentTime=time;
		this.serviceUserId=user;
		this.priority=priority;
		this.serviceProviderId=provider;
		this.visitType=visit;
		this.serviceOptionId=option;
		this.clinicId=clinic;
		this.links=link;
		
	}
	
	public ServiceOptions getLinks() {
		return this.links;
	}
	public void setLinks(ServiceOptions links) {
		this.links = links;
	}
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
	public int[] getServiceOptionId() {
		return this.serviceOptionId;
	}
	public void setServiceOptionId(int[] serviceOptionId) {
		this.serviceOptionId = serviceOptionId;
	}

	
}
