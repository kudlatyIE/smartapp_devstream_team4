/**
 * SmartDownloader to get all json data, parse it and save it into DataManager (as relevant ArrayList)
 */
package com.midwives.classes;

import java.util.ArrayList;
import java.util.HashMap;

import com.midwives.parsers.*;
import com.midwives.smartappteam4.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SmartDownloader {
	
	private Context context;
	private String token, apiKey, server, jsonString;
	private String jsonServiceOptions, jsonClinics, jsonServiceProviders, jsonServiceUser,jsonAppointments,jsonBabies, jsonPregnancies;
	private String urlAppointment, urlServiceOptions, urlClinics, urlAnnouncments, urlServiceUser, 
					urlServiceProviders, urlPregnancies, urlBabies;
	private String linkServiceOptionsURL, linkServiceProvidersURL,linkServiceUsersURL;//set after Appointments AsyncTask
	
	private final String TAG_BABY="babies",TAG_USER="service_user",TAG_APPOINTMENTS="appointments",TAG_CLINIC="clinics",
						TAG_PREGNANCIES="pregnancies",TAG_SERVICE_OPTIONS="service_options",
						TAG_SERVICE_PROVIDER="service_provider",TAG_ANNOUNCEMNTS="announcements";
	
	private  ArrayList<Appointment> appointmentList = null;
	private  ArrayList<Baby> babyList = null;
	private  ArrayList<Clinics> clinicList = null;
	private  ArrayList<Pregnancies> pregnanciesList = null;
	private  ArrayList<ServiceOptions>  serviceOptionsList = null;
	private  ArrayList<ServiceProvider> serviceProviderList = null;
	private  ArrayList<ServiceUser> serviceUserList = null;
	private  ArrayList<VisitLogs> visitLogs = null;
	private  ArrayList<Links> linksDataList = null;//belongs to specific appointment (by Appointment ID)
	private  ArrayList<Announcements> announcementslist=null;
	
	//lets HashMap
	private HashMap<Integer,Appointment> appointmentMap= null;
	private HashMap<Integer,Baby> babyMap= null;
	private HashMap<Integer,Clinics> clinicMap= null;
	private HashMap<Integer,Pregnancies> pregnanciesMap= null;
	private HashMap<Integer,ServiceProvider> serviceProviderMap= null;
	private HashMap<Integer,ServiceUser> serviceUserMap= null;
	private HashMap<Integer,Announcements> announcementsMap= null;
	private HashMap<Integer,Links> linksMap= null;//key is userId...?
	
	
	/**
	 * load all URLs, when LogIn success, then run: new SmartDownloader();
	 * announcements URL for specific appointment ID: "server/ + ID/ + announcements" !
	 * @param context
	 */
	public SmartDownloader(Context context){
		this.context=context;
		this.token=SmartAuth.getToken();
		this.apiKey=SmartAuth.getApiKey();
		this.server = context.getString(R.string.auth_url_server);
		this.urlAppointment=server.concat(context.getString(R.string.auth_url_appointment));
		this.urlBabies = server.concat(context.getString(R.string.auth_url_babies));
		this.urlPregnancies = server.concat(context.getString(R.string.auth_url_pregnancies));
		this.urlServiceOptions = server.concat(context.getString(R.string.auth_url_service_options));
		this.urlServiceProviders = server.concat(context.getString(R.string.auth_url_service_providers));
		this.urlClinics = server.concat(context.getString(R.string.auth_url_clinics));
		this.urlServiceUser = server.concat(context.getString(R.string.auth_url_service_users));
		this.urlAnnouncments = context.getString(R.string.auth_url_announcements);
	
		//to get Links data need run special asyncTask AFTER Appointment asyncTask!
		
		//need run Links-AsyncTask when Appointments-AsyncTas finish!
		
		
		AsyncSmartDownloader serviceOptionsTask = new AsyncSmartDownloader(TAG_SERVICE_OPTIONS);
		AsyncSmartDownloader clinicTask = new AsyncSmartDownloader(TAG_CLINIC);
		AsyncSmartDownloader appointmentTask = new AsyncSmartDownloader(TAG_APPOINTMENTS);
		AsyncSmartDownloader serviceUserTask = new AsyncSmartDownloader(TAG_USER);
		AsyncSmartDownloader serviceProviderTask = new AsyncSmartDownloader(TAG_SERVICE_PROVIDER);
		AsyncSmartDownloader pregnanciesTask = new AsyncSmartDownloader(TAG_PREGNANCIES);
		AsyncSmartDownloader babiesTask = new AsyncSmartDownloader(TAG_BABY);
		
		AsyncSmartDownloader announcmentsTask = new AsyncSmartDownloader(TAG_ANNOUNCEMNTS);// later.......
		
		
		serviceOptionsTask.execute(urlServiceOptions,TAG_SERVICE_OPTIONS);
		clinicTask.execute(urlClinics,TAG_CLINIC);
		appointmentTask.execute(urlAppointment,TAG_APPOINTMENTS);
//		announcmentsTask.execute(urlAnnouncments,TAG_ANNOUNCEMNTS);//not sure this task is ready to run.. need be call in ClinicTask? Or just like a LinksTask?
		serviceUserTask.execute(urlServiceUser,TAG_USER);
		serviceProviderTask.execute(urlServiceProviders,TAG_SERVICE_PROVIDER);
		pregnanciesTask.execute(urlPregnancies,TAG_PREGNANCIES);
		babiesTask.execute(urlBabies,TAG_BABY);
	}
	



	private class AsyncSmartDownloader extends AsyncTask<String,Void,String>{
		String msg;
		private ProgressDialog dialog;
		AsyncSmartDownloader(String msg){
//			this.dialog=new ProgressDialog(context);
			this.msg=msg;
		}
		
		@Override
		protected String doInBackground(String... params) {
			String jsonString, url = params[0];
			String tag = params[1];
			SmartAuth smart = new SmartAuth(token, apiKey, url);
			jsonString = smart.accessTheDBTable(token);
			if (jsonString.isEmpty())return null;
			setJsonString(jsonString);
			
			switch(tag){
			
			case TAG_BABY:
				setJsonBabies(jsonString);
				break;
			case TAG_APPOINTMENTS:
				setJsonAppointments(jsonString);
				break;
			case TAG_CLINIC:
				setJsonClinics(jsonString);
				break;
			case TAG_PREGNANCIES:
				setJsonPregnancies(jsonString);
				break;
			case TAG_SERVICE_OPTIONS:
				setJsonServiceOptions(jsonString);
				break;
			case TAG_USER:
				setJsonServiceUser(jsonString);
				break;
			case TAG_SERVICE_PROVIDER:
				setJsonServiceProviders(jsonString);
				break;
			}
			return tag;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(context);
			dialog.setMessage(msg+" is running...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(String tag) {
			
			//if TAG, then get jsonString, parse it, and sent specific ArrayList to DataManager!
			super.onPostExecute(tag);
			if(dialog.isShowing()) dialog.dismiss();
			try{
			switch(tag){
			
				case TAG_BABY:
//					babyList= new ArrayList<Baby>();
					babyMap = new HashMap<Integer, Baby>();
					babyMap = BabiesParser.parseBabyMap(getJsonBabies());
					DataManager.setBabyMap(babyMap);
					break;
				case TAG_APPOINTMENTS:
//					appointmentList = new ArrayList<Appointment>();
					appointmentMap = new HashMap<Integer,Appointment>();
					appointmentMap = AppointmentParser.parseAppointment(getJsonAppointments());
					DataManager.setAppointmentFullMap(appointmentMap);
					break;
				case TAG_CLINIC:
					clinicList = new ArrayList<Clinics>();
					clinicList = ClinicsParser.parseClinics(getJsonClinics());
					DataManager.setClinicList(clinicList);
					break;
				case TAG_PREGNANCIES:
					pregnanciesList = new ArrayList<Pregnancies>();
					pregnanciesList = PregnanciesParser.parsePregnancies(getJsonPregnancies());
					DataManager.setPregnanciesList(pregnanciesList);
					break;
				case TAG_SERVICE_OPTIONS:
					serviceOptionsList = new ArrayList<ServiceOptions>();
					serviceOptionsList = ServiceOptionsParser.parseServiceOptions(getJsonServiceOptions());
					DataManager.setServiceOptionsList(serviceOptionsList);
					break;
				case TAG_USER:
					serviceUserList = new ArrayList<ServiceUser>();
					serviceUserList = ServiceUserParser.parseServiceUser(getJsonServiceUser());
					DataManager.setServiceUserList(serviceUserList);
					break;
				case TAG_SERVICE_PROVIDER:
					serviceProviderList = new ArrayList<ServiceProvider>();
					serviceProviderList = ServiceProviderParser.parseServiceProviders(getJsonServiceProviders());
					DataManager.setServiceProviderList(serviceProviderList);					
					break;
				
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
		
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getJsonServiceOptions() {
		return jsonServiceOptions;
	}

	public void setJsonServiceOptions(String jsonServiceOptions) {
		this.jsonServiceOptions = jsonServiceOptions;
	}

	public String getJsonClinics() {
		return jsonClinics;
	}

	public void setJsonClinics(String jsonClinics) {
		this.jsonClinics = jsonClinics;
	}

	public String getJsonServiceProviders() {
		return jsonServiceProviders;
	}

	public void setJsonServiceProviders(String jsonServiceProviders) {
		this.jsonServiceProviders = jsonServiceProviders;
	}

	public String getJsonServiceUser() {
		return jsonServiceUser;
	}

	public void setJsonServiceUser(String jsonServiceUser) {
		this.jsonServiceUser = jsonServiceUser;
	}

	public String getJsonAppointments() {
		return jsonAppointments;
	}

	public void setJsonAppointments(String jsonAppointments) {
		this.jsonAppointments = jsonAppointments;
	}

	public String getJsonBabies() {
		return jsonBabies;
	}

	public void setJsonBabies(String jsonBabies) {
		this.jsonBabies = jsonBabies;
	}

	public String getJsonPregnancies() {
		return jsonPregnancies;
	}

	public void setJsonPregnancies(String jsonPregnancies) {
		this.jsonPregnancies = jsonPregnancies;
	}

	public String getLinkServiceOptionsURL() {
		return linkServiceOptionsURL;
	}

	public void setLinkServiceOptionsURL(String linkServiceOptionsURL) {
		this.linkServiceOptionsURL = linkServiceOptionsURL;
	}

	public String getLinkServiceProvidersURL() {
		return linkServiceProvidersURL;
	}

	public void setLinkServiceProvidersURL(String linkServiceProvidersURL) {
		this.linkServiceProvidersURL = linkServiceProvidersURL;
	}

	public String getLinkServiceUsersURL() {
		return linkServiceUsersURL;
	}

	public void setLinkServiceUsersURL(String linkServiceUsersURL) {
		this.linkServiceUsersURL = linkServiceUsersURL;
	}

	public HashMap<Integer, Appointment> getAppointmentMap() {
		return appointmentMap;
	}

	public void setAppointmentMap(HashMap<Integer, Appointment> appointmentMap) {
		this.appointmentMap = appointmentMap;
	}

	public HashMap<Integer, Baby> getBabyMap() {
		return babyMap;
	}

	public void setBabyMap(HashMap<Integer, Baby> babyMap) {
		this.babyMap = babyMap;
	}

	public HashMap<Integer, Clinics> getClinicMap() {
		return clinicMap;
	}

	public void setClinicMap(HashMap<Integer, Clinics> clinicMap) {
		this.clinicMap = clinicMap;
	}

	public HashMap<Integer, Pregnancies> getPregnanciesMap() {
		return pregnanciesMap;
	}

	public void setPregnanciesMap(HashMap<Integer, Pregnancies> pregnanciesMap) {
		this.pregnanciesMap = pregnanciesMap;
	}

	public HashMap<Integer, ServiceProvider> getServiceProviderMap() {
		return serviceProviderMap;
	}

	public void setServiceProviderMap(
			HashMap<Integer, ServiceProvider> serviceProviderMap) {
		this.serviceProviderMap = serviceProviderMap;
	}

	public HashMap<Integer, ServiceUser> getServiceUserMap() {
		return serviceUserMap;
	}

	public void setServiceUserMap(HashMap<Integer, ServiceUser> serviceUserMap) {
		this.serviceUserMap = serviceUserMap;
	}

	public HashMap<Integer, Announcements> getAnnouncementsMap() {
		return announcementsMap;
	}

	public void setAnnouncementsMap(HashMap<Integer, Announcements> announcementsMap) {
		this.announcementsMap = announcementsMap;
	}

	public HashMap<Integer, Links> getLinksMap() {
		return linksMap;
	}

	public void setLinksMap(HashMap<Integer, Links> linksMap) {
		this.linksMap = linksMap;
	}
	

}

