package com.midwives.classes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataManager {
	
	//need to do something with ClinicDatesActivity stuff.... Babe!
	
	private static Announcements announcments = null;
	private static Appointment appointment = null;
	private static Baby baby = null;
	private static ClinicalFields clinicalFields = null;
	private static Clinics clinics = null;
	private static Links links = null;
	private static PersonalFields personalFields = null;
	private static Pregnancies pregnancies = null;
	private static ServiceOptions serviceOptions = null;
	private static ServiceProvider serviceProvider = null;
	private static ServiceUser serviceUser = null;
	
	private static ArrayList<Appointment> appointmentList = null;
	private static ArrayList<Baby> babyList = null;
	private static ArrayList<Clinics> clinicList = null;
	private static ArrayList<Pregnancies> pregnanciesList = null;
	private static ArrayList<ServiceOptions>  serviceOptionsList = null;
	private static ArrayList<ServiceProvider> serviceProviderList = null;
	private static ArrayList<ServiceUser> serviceUserList = null;
	
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

}