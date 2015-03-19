package com.midwives.parsers;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.ServiceOptions;

public class ServiceOptionsParser implements Serializable {

	private static final long serialVersionUID = 5692736555719611369L;
	
	private final static String TAG_OBJECT = "service_options";
	private final static String TAG_ARRAY = "service_options";// array title
	private final static String TAG_SERVICE_NAME = "name";
	private final static String TAG_SERVICE_ID = "id";
	private final static String TAG_CLINIC_IDS="clinic_ids";

	private static JSONObject json,jObject;
	private static JSONArray jArray;

	/**
	 * parse service_options
	 * @param data - json String
	 * @return ArrayList of all service_options 
	 */
	public static ArrayList<ServiceOptions> parseServiceOptions(String data) {

		ArrayList<ServiceOptions> myList = new ArrayList<ServiceOptions>();

		try {
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_ARRAY);
			for(int i=0;i<jArray.length();i++){
				json=jArray.getJSONObject(i);
				int serviceId = json.getInt(TAG_SERVICE_ID);
				String serviceName = json.getString(TAG_SERVICE_NAME);
				int[] clinicsIDs;
				try{
					JSONArray arr = json.getJSONArray(TAG_CLINIC_IDS);
					clinicsIDs = new int[arr.length()];
					for(int j=0;j<arr.length();j++){
						clinicsIDs[j]=arr.getInt(j);
					}
				}catch(Exception ex){
					clinicsIDs = new int[1];
					clinicsIDs[0]=0;
				}
				myList.add(new ServiceOptions(serviceId, serviceName,clinicsIDs));
			}
			
		}

		catch (JSONException e) {
			e.printStackTrace();
		}
		return myList;
	}
	//------------------------------------------------------------
	/**
	 * parse service_options_id
	 * @param data json String
	 * @return new ServiceOptions object
	 */
	public static ServiceOptions parseServiceOptionID(String data){
		
		String serviceName;
		int serviceId;
		ServiceOptions serviceOptionId = null;
		try {
			json = new JSONObject(data);

			jObject = json.getJSONObject(TAG_OBJECT);
			serviceId = jObject.getInt(TAG_SERVICE_ID);
			serviceName = jObject.getString(TAG_SERVICE_NAME);
			JSONArray arr = json.getJSONArray(TAG_CLINIC_IDS);
			int[] clinicsIDs;
			if(arr.length()>0){
				clinicsIDs = new int[arr.length()];
				for(int j=0;j<arr.length();j++){
					clinicsIDs[j]=arr.getInt(j);
				}
			}else {
				clinicsIDs = new int[1];
				clinicsIDs[0]=0;//if clinics list is empty return 0 - may be null better?
			}
			serviceOptionId = new ServiceOptions(serviceId, serviceName,clinicsIDs);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return serviceOptionId;
	}
}
//Nick
