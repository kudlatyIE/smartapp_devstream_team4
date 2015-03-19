//Essentials data will parse into hash map, not in array list as before......
package com.midwives.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.midwives.classes.ServiceProvider;

public class ServiceProviderParser {
	
	private final static String TAG_ARRAY = "service_providers";
	private final static String TAG_ACTIVE="active", TAG_ADMIN = "admin", TAG_EMAIL ="email";
	private final static String TAG_ID = "id", TAG_LEVEL = "job_level", TAG_OCCUPATION ="job_occupation";
	private final static String TAG_NAME = "name", TAG_PASSWORD = "password";
	private final static String TAG_PRIMARY_PHONE = "primary_phone";
	private final static String TAG_SECONDARY_PHONE = "secondary_phone";
	private final static String TAG_USERNAME = "username";
	private static ServiceProvider provider;
	private static ArrayList<ServiceProvider> myList;
	private static JSONObject json;
	private static JSONArray jArray;
	
	
//	public static ArrayList<ServiceProvider> parseServiceProviders(String data){
	public static HashMap<Integer,ServiceProvider> parseServiceProvider(String data){
//		myList= new ArrayList<ServiceProvider>();
		HashMap<Integer,ServiceProvider> myMap = new HashMap<Integer, ServiceProvider>();
		try{
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_ARRAY);
			for(int i=0;i<jArray.length();i++){
				json = jArray.getJSONObject(i);
				
				boolean active = json.getBoolean(TAG_ACTIVE);
				boolean admin = json.getBoolean(TAG_ADMIN);
				String email = json.getString(TAG_EMAIL);
				int id = json.getInt(TAG_ID);
				String level = json.getString(TAG_LEVEL);
				String occupation = json.getString(TAG_OCCUPATION);
				String name = json.getString(TAG_NAME);
				String pass = json.getString(TAG_PASSWORD);
				String primary = json.getString(TAG_PRIMARY_PHONE);
				String secondary = json.getString(TAG_SECONDARY_PHONE);
				String username = json.getString(TAG_USERNAME);
				
//				myList.add(new ServiceProvider(active,admin,email,id,level,occupation,name,
//									pass,primary, secondary,username));
				myMap.put(id, new ServiceProvider(active,admin,email,id,level,occupation,name,
									pass,primary, secondary,username));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return myMap;
	}
	
	public static ServiceProvider parseServiceProviderID(String data){
		try{
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_ARRAY);
			if(jArray.length()!=1){
				Log.e("ServiceProvider ParserID", "providers num: "+jArray.length()+"!!!! - retur only first");
			}
			json = jArray.getJSONObject(0);
			
			boolean active = json.getBoolean(TAG_ACTIVE);
			boolean admin = json.getBoolean(TAG_ADMIN);
			String email = json.getString(TAG_EMAIL);
			int id = json.getInt(TAG_ID);
			String level = json.getString(TAG_LEVEL);
			String occupation = json.getString(TAG_OCCUPATION);
			String name = json.getString(TAG_NAME);
			String pass = json.getString(TAG_PASSWORD);
			String primary = json.getString(TAG_PRIMARY_PHONE);
			String secondary = json.getString(TAG_SECONDARY_PHONE);
			String username = json.getString(TAG_USERNAME);
			
			provider =  new ServiceProvider(active,admin,email,id,level,occupation,name,
								pass,primary, secondary,username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return provider;
	}

}
