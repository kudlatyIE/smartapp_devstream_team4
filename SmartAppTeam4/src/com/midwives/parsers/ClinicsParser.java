package com.midwives.parsers;

import java.io.Serializable;   //Chris 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.Clinics;
import com.midwives.classes.DataManager;
import com.midwives.classes.Days;
import com.midwives.classes.Recurrence;

public class ClinicsParser implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static String TAG_ARRAY="clinics";
	private final static String TAG_CLINIC_ID = "id", TAG_APPOINTMENT_INTERVAL = "appointment_interval";  //int fields
	private final static String TAG_CLINIC_NAME = "name", TAG_CLINIC_ADDRESS = "address", TAG_TYPE = "type"; // string fields
	private final static String TAG_SERVICE_OPTION_IDS = "service_option_ids", TAG_ANNOUNCEMENT_IDS = "announcement_ids"; //int arrays
	private final static String TAG_OPENING_TIME = "opening_time", TAG_CLOSING_TIME = "closing_time"; // datetime  fields
	private final static String TAG_ANNOUNCEMENTS = "announcements", TAG_LINKS = "links"; 
	private final static String TAG_DAYS = "days", TAG_RECURRENCE = "recurrence"; // enums 
	private final static String TAG_MONDAY = "monday", TAG_TUESDAY = "tuesday", TAG_WEDNESDAY = "wednesday", TAG_THURSDAY = "thursday", TAG_FRIDAY = "friday", TAG_SATURDAY = "saturday", TAG_SUNDAY = "sunday"; 
	
	private static JSONObject json;
	private static JSONArray  jArray;


//	public static ArrayList<Clinics> parseClinics(String jsonString) {
	public static HashMap<Integer, Clinics> parseClinic(String data){
//		ArrayList<Clinics> listOfClinics = new ArrayList<Clinics>();
		HashMap<Integer,Clinics> myMap = new HashMap<Integer,Clinics>();
		
		try{
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_ARRAY);
				for(int i = 0; i < jArray.length(); i++){
					json = jArray.getJSONObject(i);
					
					int clinicId = json.getInt(TAG_CLINIC_ID);
					String clinicName = json.getString(TAG_CLINIC_NAME);
					String clinicAddress = json.getString(TAG_CLINIC_ADDRESS);
					
					String openingTime  = json.getString(TAG_OPENING_TIME);
					
					String closingTime = json.getString(TAG_CLOSING_TIME);
										
					Recurrence recurrence = Recurrence.valueOf(json.getString(TAG_RECURRENCE).toUpperCase());
					recurrence.setReccurenceStatus(true);
					
					String type = json.getString(TAG_TYPE);
					int appointmentInterval = json.getInt(TAG_APPOINTMENT_INTERVAL);
					
					String[] dayNames;
					// will change to arrayList.................................
					ArrayList<String>days = new ArrayList<String>();
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_SUNDAY)){
						days.add(Days.SUNDAY.getDayName());
					}
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_MONDAY)){
						days.add(Days.MONDAY.getDayName());
					}
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_TUESDAY)){
						days.add(Days.TUESDAY.getDayName());
					} 
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_WEDNESDAY)){
						days.add(Days.WEDNESDAY.getDayName());
					}
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_THURSDAY)){
						days.add(Days.THURSDAY.getDayName());
					} 
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_FRIDAY)){
						days.add(Days.FRIDAY.getDayName());
					} 
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_SATURDAY)){
						days.add(Days.SATURDAY.getDayName());
					}
					dayNames = days.toArray(new String[days.size()]);
					
					int[] service_option_ids;
					try{
						JSONArray temp = json.getJSONArray(TAG_SERVICE_OPTION_IDS);
						service_option_ids = new int[temp.length()];
						for(int k = 0; k < temp.length(); k++) {
							service_option_ids[k] = temp.getInt(k);
							}
					}catch(Exception ex){
						service_option_ids = new int[1];
						service_option_ids[0]=0;
					}
					
					myMap.put(clinicId, new Clinics(clinicId, clinicName, clinicAddress, openingTime, closingTime, recurrence, 
												type, appointmentInterval, dayNames, service_option_ids));
					
				} // close for iteration loop
							
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return myMap;    //returns an array list of clinics
	}
	
}//close class Clinicsparser
