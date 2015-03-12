package com.midwives.classes;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataManager {
	
	//need to do something with ClinicDatesActivity stuff.... Babe!
	
	private static Announcements announcments = null;
	private static Appointment appointment = null;
	private static Baby baby = null;
	private static ClinicalFields clinicalFields = null;
	private static Clinics clinics = null;
	private static ClinicDates clinicDates=null;
	private static ClinicCalendar clinicCalendar=null;//hold specific date for current Appointment!
	private static Links links = null;//hold only URLs as a string
	
	private static PersonalFields personalFields = null;
	private static Pregnancies pregnancies = null;
	private static ServiceOptions serviceOptions = null;
	private static ServiceProvider serviceProvider = null;
	private static ServiceUser serviceUser = null;
	//-------------ArrayyLists will be repalced by HashMaps--------------------------------
	private static ArrayList<ClinicCalendar> clinicCalendarList=null;//hold full calendar for Appointments: Next/Previous
	private static ArrayList<Appointment> appointmentList = null;
	private static ArrayList<Appointment> appointmentShortList = null;
	private static ArrayList<Baby> babyList = null;
	private static ArrayList<Clinics> clinicList = null;
	private static ArrayList<Pregnancies> pregnanciesList = null;
	private static ArrayList<ServiceOptions>  serviceOptionsList = null;
	private static ArrayList<ServiceProvider> serviceProviderList = null;
	private static ArrayList<ServiceUser> serviceUserList = null;
	private static ArrayList<VisitLogs> visitLogs = null;
	
	//I don't need this linksURLsList - get access by appointmentsList!
	private static ArrayList<Links> linksURLsList = null;//belongs to specific appointment (by Appointment ID), holds 3 URLs
	private static ArrayList<Links> linksDataList = null;//belongs to specific appointment (by Appointment ID), holds 3 class objects
	private static ArrayList<Announcements> announcementslist=null;
	//-------------------------------------------------------------------------------------
	
	//try switch core data from ArrayList into HashMap
	private static HashMap<Integer,ServiceOptions> serviceOptionsMap = null;
	private static HashMap<Integer,ServiceProvider> serviceProviderMap = null;
	private static HashMap<Integer,ServiceUser> serviceUserMap = null;
	private static HashMap<Integer,Clinics> clinicsMap=null;
	private static HashMap<Integer,Appointment> appointmentFullMap = null;//appointment DATE String is a key!
	private static HashMap<Integer,Appointment> appointmentDateMap = null;//appointment DATE String is a key!
	private static HashMap<Integer,Appointment> appointmentShortMap = null;
	private static HashMap<Integer,Baby> babyMap = null;
	private static HashMap<Integer,Pregnancies> pregnanciesMap = null;
	private static HashMap<Integer,VisitLogs> visitLogsMap = null;
	
	private static HashMap<Integer,Links> linksURLsMap = null;//belongs to specific appointment (by Appointment ID), holds 3 URLs
	private static HashMap<Integer,Links> linksDataMap = null;//belongs to specific appointment (by Appointment ID), holds 3 class objects
	private static HashMap<Integer,Announcements> announcementsMap=null;
	
	
	
	private static JSONArray jArray;
	private static JSONObject jObject;
	private static DataManager dataManager;
	
	public DataManager(){}
	
	public DataManager getDataManager(){
		if (dataManager==null) dataManager = new DataManager();
		return dataManager;
	}

	public static Announcements getAnnouncments() {
		return announcments;
	}

	public static void setAnnouncments(Announcements announcments) {
		DataManager.announcments = announcments;
	}

	public static Appointment getAppointment() {
		return appointment;
	}

	public static void setAppointment(Appointment appointment) {
		DataManager.appointment = appointment;
	}

	public static Baby getBaby() {
		return baby;
	}

	public static void setBaby(Baby baby) {
		DataManager.baby = baby;
	}

	public static ClinicalFields getClinicalFields() {
		return clinicalFields;
	}

	public static void setClinicalFields(ClinicalFields clinicalFields) {
		DataManager.clinicalFields = clinicalFields;
	}

	public static Clinics getClinics() {
		return clinics;
	}

	public static void setClinics(Clinics clinics) {
		DataManager.clinics = clinics;
	}

	public static Links getLinks() {
		return links;
	}

	public static void setLinks(Links links) {
		DataManager.links = links;
	}

	public static PersonalFields getPersonalFields() {
		return personalFields;
	}

	public static void setPersonalFields(PersonalFields personalFields) {
		DataManager.personalFields = personalFields;
	}

	public static Pregnancies getPregnancies() {
		return pregnancies;
	}

	public static void setPregnancies(Pregnancies pregnancies) {
		DataManager.pregnancies = pregnancies;
	}

	public static ServiceOptions getServiceOptions() {
		return serviceOptions;
	}

	public static void setServiceOptions(ServiceOptions serviceOptions) {
		DataManager.serviceOptions = serviceOptions;
	}

	public static ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public static void setServiceProvider(ServiceProvider serviceProvider) {
		DataManager.serviceProvider = serviceProvider;
	}

	public static ServiceUser getServiceUser() {
		return serviceUser;
	}

	public static void setServiceUser(ServiceUser serviceUser) {
		DataManager.serviceUser = serviceUser;
	}

	public static JSONArray getjArray() {
		return jArray;
	}

	public static void setjArray(JSONArray jArray) {
		DataManager.jArray = jArray;
	}

	public static JSONObject getjObject() {
		return jObject;
	}

	public static void setjObject(JSONObject jObject) {
		DataManager.jObject = jObject;
	}

	public static void setDataManager(DataManager dataManager) {
		DataManager.dataManager = dataManager;
	}

	public static ClinicDates getClinicDates() {
		return clinicDates;
	}

	public static void setClinicdates(ClinicDates clinicDates) {
		DataManager.clinicDates = clinicDates;
	}

	public static ArrayList<VisitLogs> getVisitLogs() {
		return visitLogs;
	}

	public static void setVisitLogs(ArrayList<VisitLogs> visitLogs) {
		DataManager.visitLogs = visitLogs;
	}
	public static ArrayList<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public static void setAppointmentList(ArrayList<Appointment> appointmentList) {
		DataManager.appointmentList = appointmentList;
	}

	public static ArrayList<Baby> getBabyList() {
		return babyList;
	}

	public static void setBabyList(ArrayList<Baby> babyList) {
		DataManager.babyList = babyList;
	}

	public static ArrayList<Clinics> getClinicList() {
		return clinicList;
	}

	public static void setClinicList(ArrayList<Clinics> clinicList) {
		DataManager.clinicList = clinicList;
	}

	public static ArrayList<Pregnancies> getPregnanciesList() {
		return pregnanciesList;
	}

	public static void setPregnanciesList(ArrayList<Pregnancies> pregnanciesList) {
		DataManager.pregnanciesList = pregnanciesList;
	}

	public static ArrayList<ServiceOptions> getServiceOptionsList() {
		return serviceOptionsList;
	}

	public static void setServiceOptionsList(
			ArrayList<ServiceOptions> serviceOptionsList) {
		DataManager.serviceOptionsList = serviceOptionsList;
	}

	public static ArrayList<ServiceProvider> getServiceProviderList() {
		return serviceProviderList;
	}

	public static void setServiceProviderList(
			ArrayList<ServiceProvider> serviceProviderList) {
		DataManager.serviceProviderList = serviceProviderList;
	}

	public static ArrayList<ServiceUser> getServiceUserList() {
		return serviceUserList;
	}

	public static void setServiceUserList(ArrayList<ServiceUser> serviceUserList) {
		DataManager.serviceUserList = serviceUserList;
	}


	public static ArrayList<Links> getLinksDataList() {
		return linksDataList;
	}

	public static void setLinksDataList(ArrayList<Links> linksDataList) {
		DataManager.linksDataList = linksDataList;
	}

	public static ArrayList<Announcements> getAnnouncementslist() {
		return announcementslist;
	}

	public static void setAnnouncementslist(
			ArrayList<Announcements> announcementslist) {
		DataManager.announcementslist = announcementslist;
	}

	public static ArrayList<Appointment> getAppointmentShortList() {
		return appointmentShortList;
	}

	public static void setAppointmentShortList(
			ArrayList<Appointment> appointmentShortList) {
		DataManager.appointmentShortList = appointmentShortList;
	}

	public static ArrayList<Links> getLinksURLsList() {
		return linksURLsList;
	}

	public static void setLinksURLsList(ArrayList<Links> linksURLsList) {
		DataManager.linksURLsList = linksURLsList;
	}

	public static ClinicCalendar getClinicCalendar() {
		return clinicCalendar;
	}

	public static void setClinicCalendar(ClinicCalendar clinicCalendar) {
		DataManager.clinicCalendar = clinicCalendar;
	}

	public static ArrayList<ClinicCalendar> getClinicCalendarList() {
		return clinicCalendarList;
	}

	public static void setClinicCalendarList(
			ArrayList<ClinicCalendar> clinicCalendarList) {
		DataManager.clinicCalendarList = clinicCalendarList;
	}

	public static HashMap<Integer, ServiceOptions> getServiceOptionsMap() {
		return serviceOptionsMap;
	}

	public static void setServiceOptionsMap(
			HashMap<Integer, ServiceOptions> serviceOptionsMap) {
		DataManager.serviceOptionsMap = serviceOptionsMap;
	}

	public static HashMap<Integer, Clinics> getClinicsMap() {
		return clinicsMap;
	}

	public static void setClinicsMap(HashMap<Integer, Clinics> clinicsMap) {
		DataManager.clinicsMap = clinicsMap;
	}

	public static HashMap<Integer, ServiceProvider> getServiceProviderMap() {
		return serviceProviderMap;
	}

	public static void setServiceProviderMap(
			HashMap<Integer, ServiceProvider> serviceProviderMap) {
		DataManager.serviceProviderMap = serviceProviderMap;
	}

	public static HashMap<Integer, ServiceUser> getServiceUserMap() {
		return serviceUserMap;
	}

	public static void setServiceUserMap(
			HashMap<Integer, ServiceUser> serviceUserMap) {
		DataManager.serviceUserMap = serviceUserMap;
	}



	public static HashMap<Integer, Appointment> getAppointmentDateMap() {
		return appointmentDateMap;
	}

	public static void setAppointmentDateMap(
			HashMap<Integer, Appointment> appointmentDateMap) {
		DataManager.appointmentDateMap = appointmentDateMap;
	}

	public static HashMap<Integer, Appointment> getAppointmentShortMap() {
		return appointmentShortMap;
	}

	public static void setAppointmentShortMap(
			HashMap<Integer, Appointment> appointmentShortMap) {
		DataManager.appointmentShortMap = appointmentShortMap;
	}

	public static HashMap<Integer, Baby> getBabyMap() {
		return babyMap;
	}

	public static void setBabyMap(HashMap<Integer, Baby> babyMap) {
		DataManager.babyMap = babyMap;
	}

	public static HashMap<Integer, Pregnancies> getPregnanciesMap() {
		return pregnanciesMap;
	}

	public static void setPregnanciesMap(
			HashMap<Integer, Pregnancies> pregnanciesMap) {
		DataManager.pregnanciesMap = pregnanciesMap;
	}

	public static HashMap<Integer, Links> getLinksURLsMap() {
		return linksURLsMap;
	}

	public static void setLinksURLsMap(HashMap<Integer, Links> linksURLsMap) {
		DataManager.linksURLsMap = linksURLsMap;
	}

	public static HashMap<Integer, Links> getLinksDataMap() {
		return linksDataMap;
	}

	public static void setLinksDataMap(HashMap<Integer, Links> linksDataMap) {
		DataManager.linksDataMap = linksDataMap;
	}

	public static HashMap<Integer, Appointment> getAppointmentFullMap() {
		return appointmentFullMap;
	}

	public static void setAppointmentFullMap(
			HashMap<Integer, Appointment> appointmentFullMap) {
		DataManager.appointmentFullMap = appointmentFullMap;
	}

	public static HashMap<Integer, Announcements> getAnnouncementsMap() {
		return announcementsMap;
	}

	public static void setAnnouncementsMap(
			HashMap<Integer, Announcements> announcementsMap) {
		DataManager.announcementsMap = announcementsMap;
	}

	public static HashMap<Integer, VisitLogs> getVisitLogsMap() {
		return visitLogsMap;
	}

	public static void setVisitLogsMap(HashMap<Integer, VisitLogs> visitLogsMap) {
		DataManager.visitLogsMap = visitLogsMap;
	}

}
