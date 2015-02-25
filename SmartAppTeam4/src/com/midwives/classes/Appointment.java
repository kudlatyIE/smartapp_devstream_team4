package com.midwives.classes;


public class Appointment {
	
	private int id, serviceProviderId, serviceUserId, clinicId;
	private String date, time;
//	private Priority priority;
//	private VisitType visitType; -----------------remove all Enums!!!!
	private String visitType;
	private String priority;
	private int [] serviceOptionIds;
	private ServiceUser serviceUser;
	private String[] visitLogs;
	
	private static Appointment appointment;
	
	private Links links;
	
	public Appointment(){} //used when is empty slot
	
	public Appointment(int clinicId, String date, int id, Links links, String priority , int serviceProviderId,
			ServiceUser serviceUser, int serviceUserId, String time, String visitType){
		this.clinicId=clinicId;
		this.date=date;
		this.id=id;
		this.links=links;
		this.priority=priority;
		this.serviceProviderId=serviceProviderId;
		this.serviceUser=serviceUser;
		this.serviceProviderId=serviceUserId;
		this.time=time;
		this.visitType=visitType;

	}
	
	
	/**
	 * I can't parse int[] from json yet.... Dont use for now
	 * @param clinicId
	 * @param date
	 * @param id
	 * @param links
	 * @param priority
	 * @param serviceOptionsIds
	 * @param serviceProviderId
	 * @param serviceUser
	 * @param serviceUserId
	 * @param time
	 * @param visitLogs
	 * @param visitType
	 */
	public Appointment(int clinicId, String date, int id, Links links, String priority, int[] serviceOptionsIds, int serviceProviderId,
						ServiceUser serviceUser, int serviceUserId, String time, String[] visitLogs, String visitType){
		this.clinicId=clinicId;
		this.date=date;
		this.id=id;
		this.links=links;
		this.priority=priority;
		this.serviceOptionIds=serviceOptionsIds;
		this.serviceProviderId=serviceProviderId;
		this.serviceUser=serviceUser;
		this.serviceProviderId=serviceUserId;
		this.time=time;
		this.visitLogs=visitLogs;
		this.visitType=visitType;
		
	}
	
	//a new constructor with Links class
	public Appointment(String date, int appId, int serviceId, int userId, String priority, String time, 
			String visitType, int clinicId, Links links){
		this.date=date;
		this.id=appId;
		this.serviceProviderId=serviceId;
		this.serviceUserId=userId;
		this.priority=priority;
		this.time=time;
		this.visitType=visitType;
		this.clinicId=clinicId;
		this.links=links;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int[] getServiceOptionIds() {
		return serviceOptionIds;
	}

	public void setServiceOptionIds(int[] serviceOptionIds) {
		this.serviceOptionIds = serviceOptionIds;
	}

	public ServiceUser getServiceUser() {
		return serviceUser;
	}

	public void setServiceUser(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
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
		return date;
	}

	public void setAppDate(String appDate) {
		this.date = appDate;
	}

	public String getAppTime() {
		return time;
	}

	public void setAppTime(String appTime) {
		this.time = appTime;
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
		return id;
	}
	public void setAppointmentId(int appointmentId) {
		this.id = appointmentId;
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
	
	public String getPriority() {
		return this.priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getVisitType() {
		return this.visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String[] getVisitLogs() {
		return visitLogs;
	}

	public void setVisitLogs(String[] visitLogs) {
		this.visitLogs = visitLogs;
	}

	public static Appointment getAppointment() {
		return appointment;
	}

	public static void setAppointment(Appointment appointment) {
		Appointment.appointment = appointment;
	}

	
}
