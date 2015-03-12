/**
 * temporary class to handle fake json stuff - test only!
 */
package com.midwives.parsers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.midwives.classes.Appointment;
import com.midwives.classes.DataManager;
import com.midwives.classes.Links;
import com.midwives.classes.PersonalFields;
import com.midwives.classes.Pregnancies;
import com.midwives.classes.ServiceUser;


public class AppointmentParser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String TAG_ARRAY="appointments";
	private final static String TAG_CLINIC_ID="clinic_id", TAG_DATE="date", TAG_ID="id";
	private final static String TAG_PRIORITY="priority",TAG_SERVICE_OPTION_IDS="service_option_ids",
								TAG_SERVICE_PROVIDER_ID="service_provider_id";
	private final static String TAG_SERVCE_USER_ID="service_user_id",TAG_TIME="time", 
								TAG_VISIT_LOGS="visit_logs", TAG_VISIT_TYPE="visit_type";
	private final static String TAG_LINKS="links";
	
	private final static String TAG_USER = "service_user";
	private final static String TAG_USER_GESTATION="gestation", TAG_USER_ID="id", TAG_USER_NAME="name";
	private final static String TAG_SERVICE_OPTIONS="service_options",TAG_SERVICE_PROVIDER="service_provider",TAG_SERVICE_USER="service_user";
	private static String jsonString;
//	private static Appointment appData;
	
	private static JSONArray jArray;
	private static JSONObject json;
	
//	public static ArrayList<Appointment> parseAppointment(String data){
	public static HashMap<Integer,Appointment> parseAppointment(String data){
		
		 ArrayList<Appointment> myList = new ArrayList<Appointment>();
		 HashMap<Integer,Appointment>appointmentsMap = new HashMap<Integer,Appointment>();
 
		 
		 try {
			 json = new JSONObject(data);
			 
			jArray = json.getJSONArray(TAG_ARRAY);
			for(int i=0;i<jArray.length();i++){
				 json = jArray.getJSONObject(i);
				 
				 int clinicID = json.getInt(TAG_CLINIC_ID);
				 String date = json.getString(TAG_DATE);
				 int id = json.getInt(TAG_ID);//appointment ID
				 //Links
				 String serviceOptionsLink = json.getJSONObject(TAG_LINKS).getString(TAG_SERVICE_OPTIONS);
				 String serviceProviderLink = json.getJSONObject(TAG_LINKS).getString(TAG_SERVICE_PROVIDER);
				 String serviceUserLink = json.getJSONObject(TAG_LINKS).getString(TAG_SERVICE_USER);
				 //
				 String priority = json.getString(TAG_PRIORITY);
				 //---------------------------------------------------------
				 
				 String[] visitLogs;
				 JSONArray arrstr = json.getJSONArray(TAG_VISIT_LOGS);
				 if (arrstr.length()!=0){
					 visitLogs = new String[arrstr.length()];
					 for(int j=0;i<arrstr.length();j++){
						 visitLogs[j]= arrstr.getString(j);
						 Log.e("String[j]= ", visitLogs[j]);
					 }
				 }else {
					 visitLogs= new String[1];
					 visitLogs[0]="no logs";
				 }
				 //---------------------------------
				 int[]serviceOptionIDs;
				 JSONArray arrint = json.getJSONArray(TAG_SERVICE_OPTION_IDS);
				 if(arrint.length()!=0){
					 serviceOptionIDs = new int[arrint.length()];
					 for(int j=0;i<arrint.length();j++){
						 serviceOptionIDs[j]=arrint.getInt(j);
						 Log.e("int[j]= ", String.valueOf(serviceOptionIDs[j]));
					 }
				 }else {
					 serviceOptionIDs = new int[1];
					 serviceOptionIDs[0]=99999;
				 }
				 //-------------------------------------------------
				 
				 int serviceProviderID = json.getInt(TAG_SERVICE_PROVIDER_ID);
				 int serviceUserID = json.getInt(TAG_SERVCE_USER_ID);

				 String gestation;  
				 try{
					 if(json.getJSONObject(TAG_USER).getString(TAG_USER_GESTATION).equals(null)) gestation =  " ---- ";//delete this line.....
					 else gestation =  json.getJSONObject(TAG_USER).getString(TAG_USER_GESTATION);  
				 }catch(Exception e){
					 gestation =  " ---- "; // return when gestation is NULL
				 }
				 
				 String name = json.getJSONObject(TAG_USER).getString(TAG_USER_NAME);
				 int userId= json.getJSONObject(TAG_USER).getInt(TAG_USER_ID);
				 String time = json.getString(TAG_TIME);

				 String visitType = json.getString(TAG_VISIT_TYPE);
				  
				 //
				 //lets HashMap!!!
				 appointmentsMap.put(id, new Appointment(clinicID,date,id,new Links(serviceOptionsLink,serviceProviderLink,serviceUserLink),priority,
		 					serviceOptionIDs,serviceProviderID,new ServiceUser(gestation,userId,name),serviceUserID,time,visitLogs,visitType));
				 
				 myList.add(new Appointment(clinicID,date,id,new Links(serviceOptionsLink,serviceProviderLink,serviceUserLink),priority,
		 					serviceOptionIDs,serviceProviderID,new ServiceUser(gestation,userId,name),serviceUserID,time,visitLogs,visitType));	 
			 }
//			DataManager.setAppointmentFullMap(appointmentsMap);//will be handle by SmartDownloader class...
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 
		 
		 return appointmentsMap;
	}

}

