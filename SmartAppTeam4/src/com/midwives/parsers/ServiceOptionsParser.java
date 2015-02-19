package com.midwives.parsers;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.ServiceOptions;

public class ServiceOptionsParser implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static String TAG_OBJECT = "service_options";
	private final static String TAG_ARRAY = "service_options";// array title
	private final static String TAG_SERVICE_NAME = "name";
	private final static String TAG_SERVICE_ID = "id";

	private static JSONObject json,jObject;
	private static JSONArray jArray;

	/**
	 * parse service_options
	 * @param data - json String
	 * @return ArrayList of service_options objects
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
				myList.add(new ServiceOptions(serviceId, serviceName));
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
			serviceOptionId = new ServiceOptions(serviceId, serviceName);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return serviceOptionId;
	}
}
//Nick
