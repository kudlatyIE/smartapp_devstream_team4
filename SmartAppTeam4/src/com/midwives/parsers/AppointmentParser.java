/**
 * temporary class to handle fake json stuff - test only!
 */
package com.midwives.parsers;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.Appointment;
import com.midwives.classes.AppointmentJson;
import com.midwives.classes.Links;
import com.midwives.classes.ServiceUser;


public class AppointmentParser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String TAG_ARRAY="appointments";
	private final static String TAG_CLINIC_ID="clinic_id", TAG_DATE="date", TAG_ID="id";
	private final static String TAG_PRIORITY="priority",TAG_SERVICE_OPTION_IDS="service_option_ids",TAG_SERVICE_PROVIDER_ID="service_provider_id";
	private final static String TAG_SERVCE_USER_ID="service_user_id",TAG_TIME="time", TAG_VISIT_LOGS="visit_logs", TAG_VISIT_TYPE="visit_type";
	private final static String TAG_LINKS="links",TAG_SERVICE_USER_ID="service_user_id";
	
	private final static String TAG_USER = "service_user";
	private final static String TAG_USER_GESTATION="gestation", TAG_USER_ID="id", TAG_USER_NAME="name";
	private final static String TAG_SERVICE_OPTIONS="service_options",TAG_SERVICE_PROVIDER="service_provider",TAG_SERVICE_USER="service_user";
	private static String jsonString;
	private static Appointment appData;
	
	private static JSONArray jArray;
	private static JSONObject json;
	
	public static ArrayList<AppointmentJson> parseAppointment(String data){
		
		 ArrayList<AppointmentJson> myList = new ArrayList<AppointmentJson>();
 
		 
		 try {
			 json = new JSONObject(data);
			 
			jArray = json.getJSONArray(TAG_ARRAY);
			for(int i=0;i<jArray.length();i++){
				 json = jArray.getJSONObject(i);
				 
				 String clinicId = String.valueOf(json.getInt(TAG_CLINIC_ID));
				 String date = json.getString(TAG_DATE);
				 String id = String.valueOf(json.getInt(TAG_ID));
				 String serviceOptions = json.getJSONObject(TAG_LINKS).getString(TAG_SERVICE_OPTIONS);
				 String serviceProvider = json.getJSONObject(TAG_LINKS).getString(TAG_SERVICE_PROVIDER);
				 String serviceUser = json.getJSONObject(TAG_LINKS).getString(TAG_SERVICE_USER);
				 String priority = json.getString(TAG_PRIORITY);
				 
				 //how to parse nested String array from jsonArray???
//				 String[] serviceOptionIDs = json.getNames(getJSONObject(TAG_SERVICE_OPTION_IDS));
				 
				 String serviceProviderID = String.valueOf(json.getInt(TAG_SERVICE_PROVIDER_ID));
				 String serviceUserID = String.valueOf(json.getInt(TAG_SERVCE_USER_ID));
				 
				 String userGestation;  
				 try{
					 if(json.getJSONObject(TAG_USER).getString(TAG_USER_GESTATION).equals(null)) userGestation =  " ---- ";
					 else userGestation =  json.getJSONObject(TAG_USER).getString(TAG_USER_GESTATION);
					  
				 }catch(Exception e){
					 userGestation =  " ---- "; // return when gestation is NULL
				 }

				 
				 String userName = json.getJSONObject(TAG_USER).getString(TAG_USER_NAME);
				 String userId= String.valueOf(json.getJSONObject(TAG_USER).getInt(TAG_USER_ID));
				 String time = json.getString(TAG_TIME);
				 //to do: parse visit_logs Array.....
//				 String[] visitLogs;
				 String visitType = json.getString(TAG_VISIT_TYPE);
				 
				 //to fix.....
				 myList.add(new AppointmentJson(clinicId,date,id,new Links(serviceOptions,serviceProvider,serviceUser),
						 					priority,serviceProviderID,serviceUserID,new ServiceUser(userGestation,userId, userName),time,visitType));
				 
				 
			 }
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 
		 
		 return myList;
	}

}

