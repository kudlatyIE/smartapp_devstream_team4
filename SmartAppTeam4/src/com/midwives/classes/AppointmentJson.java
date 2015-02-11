package com.midwives.classes;

import java.io.Serializable;


public class AppointmentJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clinicID, date, id;
	
	private Links links;
	
	private String priority;
	private String[] serviceOptionsIDs;// not now
	private String serviceProviderID;
	
	private ServiceUser serviceUser;
	
	private String serviceUserID, time;
	private String[] visitLogs; // not now
	private String visitType;
	
	//short constructor without string Arrays....
	public AppointmentJson(String clinicID, String date, String id, Links links,
			String priority,String serviceProviderID, String serviceUserID,
			ServiceUser user, String time,String visitType) {

		this.clinicID = clinicID;
		this.date = date;
		this.id = id;
		this.links=links;
		this.priority = priority;
		this.serviceProviderID = serviceProviderID;
		this.serviceUser = user;
		this.serviceUserID = serviceUserID;
		this.time = time;
		this.visitType = visitType;
	}
	
	public AppointmentJson(String clinicID, String date, String id, Links links,
			String priority, String[] serviceOptionsIDs,
			String serviceProviderID, ServiceUser serviceUser,
			String serviceUserID, String time, String[] visitLogs,
			String visitType) {

		this.clinicID = clinicID;
		this.date = date;
		this.id = id;
		this.links = links;
		this.priority = priority;
		this.serviceOptionsIDs = serviceOptionsIDs;
		this.serviceProviderID = serviceProviderID;
		this.serviceUser = serviceUser;
		this.serviceUserID = serviceUserID;
		this.time = time;
		this.visitLogs = visitLogs;
		this.visitType = visitType;
	}

	public String getClinicID() {
		return clinicID;
	}

	public void setClinicID(String clinicID) {
		this.clinicID = clinicID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String[] getServiceOptionsIDs() {
		return serviceOptionsIDs;
	}

	public void setServiceOptionsIDs(String[] serviceOptionsIDs) {
		this.serviceOptionsIDs = serviceOptionsIDs;
	}

	public String getServiceProviderID() {
		return serviceProviderID;
	}

	public void setServiceProviderID(String serviceProviderID) {
		this.serviceProviderID = serviceProviderID;
	}

	public ServiceUser getServiceUser() {
		return serviceUser;
	}

	public void setServiceUser(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
	}

	public String getServiceUserID() {
		return serviceUserID;
	}

	public void setServiceUserID(String serviceUserID) {
		this.serviceUserID = serviceUserID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String[] getVisitLogs() {
		return visitLogs;
	}

	public void setVisitLogs(String[] visitLogs) {
		this.visitLogs = visitLogs;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	
	
	
	
}
