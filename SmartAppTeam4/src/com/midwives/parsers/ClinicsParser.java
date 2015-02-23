package com.midwives.parsers;

import java.io.Serializable;   //Chris 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.Clinics;
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
	private static int[] service_option_ids;
	private static JSONObject json;
	private static JSONArray  jArray;
	


	public static ArrayList<Clinics> parseClinics(String jsonString) {
		ArrayList<Clinics> listOfClinics = new ArrayList<Clinics>();
		int[] service_option_ids = null;
		
		
		try{
			json = new JSONObject(jsonString);
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
					
					String[] dayNames = new String[7];
					
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_SUNDAY)){
						dayNames[0] = Days.SUNDAY.getDayName();
					}
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_MONDAY)){
						dayNames[1] = Days.MONDAY.getDayName();
					}
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_TUESDAY)){
						dayNames[2] = Days.TUESDAY.getDayName();
					} 
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_WEDNESDAY)){
						dayNames[3] = Days.WEDNESDAY.getDayName();
					}
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_THURSDAY)){
						dayNames[4] = Days.THURSDAY.getDayName();
					} 
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_FRIDAY)){
						dayNames[5] = Days.FRIDAY.getDayName();
					} 
					if(json.getJSONObject(TAG_DAYS).getBoolean(TAG_SATURDAY)){
						dayNames[6] = Days.SATURDAY.getDayName();
					}
										
					JSONArray temp = json.getJSONArray(TAG_SERVICE_OPTION_IDS);
					int length = temp.length();
					if(length > 0){
						service_option_ids = new int[length];
						for(int k = 0; k < length; k++) {
							service_option_ids[k] = temp.getInt(k);
						}
					}
					
					listOfClinics.add(new Clinics(clinicId, clinicName, clinicAddress, openingTime, closingTime, recurrence, type, appointmentInterval, dayNames, service_option_ids ));
				} // close for iteration loop
							
		} catch (JSONException e) {
			e.printStackTrace();
		}
				
		return listOfClinics;    //returns an array list of clinics
	}
	
}//close class Clinicsparser
