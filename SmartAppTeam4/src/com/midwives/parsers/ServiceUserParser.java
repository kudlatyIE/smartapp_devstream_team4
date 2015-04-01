package com.midwives.parsers;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.ClinicalFields;
import com.midwives.classes.PersonalFields;
import com.midwives.classes.ServiceUser;

public class ServiceUserParser {
	
	
	private static JSONObject json;
	private static JSONObject j;
	private static JSONArray jArray;
	

	private final static String TAG_HOSPITAL_NO="hospital_number";
	private final static String TAG_ID="id";
	private final static String TAG_NAME="name";
	private final static String TAG_BABY_IDS="baby_ids"; //----------- int []

	//keys for ServiceUser
	private final static String TAG_SERVICE_USER_ARRAY="service_users";//------JSONArray
	private final static String TAG_CLINICAL="clinical_fields";
	private final static String TAG_BLOOD="blood_group";
	private final static String TAG_BMI="bmi";
	private final static String TAG_PARITY="parity";
	private final static String TAG_OBSTERIC="previous_obstetric_history";
	private final static String TAG_RHESUS="rhesus";//----------------- boolean
	private final static String TAG_PERSONAL_JSON_OBJECT="personal_fields";//---------JSON Object
	private final static String TAG_DIRECTIONS="directions";
	private final static String TAG_DOB="dob";
	private final static String TAG_EMAIL="email";
	private final static String TAG_HOME_ADDRESS="home_address";
	private final static String TAG_HOME_COUNTY="home_county";
	private final static String TAG_HOME_PHONE="home_phone";
	private final static String TAG_HOME_POST_CODE="home_post_code";
	private final static String TAG_HOME_TYPE="home_type";
	private final static String TAG_MOBILE="mobile_phone";
	private final static String TAG_NEXT_OF_KIN_NAME="next_of_kin_name";
	private final static String TAG_NEXT_OF_KIN_PHONE="next_of_kin_phone";
	// last, separate array, not belongs to PersonalFields
	private final static String TAG_PREGNANCY_IDS="pregnancy_ids";//----------- int []
	
	
	private static ClinicalFields clinical;
	private static ServiceUser serviceUser;
	private static PersonalFields personalFields;
	
	
	//return list of all objects
	/**
	 * 
	 * @param data json String
	 * @return HashMap of all ServiceUsers,  User ID as a KEY...
	 */
	public static HashMap<Integer, ServiceUser> parseServiceUser(String data){
		HashMap<Integer, ServiceUser> myMap = new HashMap<Integer,ServiceUser>();
		
		try {
			json = new JSONObject(data);

		//get service_user data ------------------------------------------------------
			jArray = json.getJSONArray(TAG_SERVICE_USER_ARRAY);
			for(int i=0;i<jArray.length();i++){
				j=jArray.getJSONObject(i);
				int[] babyIds;
				try{
					JSONArray arr = j.getJSONArray(TAG_BABY_IDS);
					babyIds = new int[arr.length()];
					for(int ii=0;ii<arr.length();ii++){
						babyIds[ii]= arr.getInt(ii);
					}
				}catch(Exception ex){
					babyIds = new int[1];
					babyIds[0]=0;
				}
				
				JSONObject clinic = j.getJSONObject(TAG_CLINICAL);
					String blood = clinic.getString(TAG_BLOOD);
					String bmi = clinic.getString(TAG_BMI);
					String partity = clinic.getString(TAG_PARITY);
					String obstetricHistory = clinic.getString(TAG_OBSTERIC);
					boolean rhesus = clinic.getBoolean(TAG_RHESUS);
				clinical = new ClinicalFields(blood,bmi,partity, obstetricHistory,rhesus);
				
				String hospitalNumber = j.getString(TAG_HOSPITAL_NO);
				int id = j.getInt(TAG_ID);
				JSONObject personal = j.getJSONObject(TAG_PERSONAL_JSON_OBJECT);
					String directions = personal.getString(TAG_DIRECTIONS);
					String dob = personal.getString(TAG_DOB);
					String email = personal.getString(TAG_EMAIL);
					String homeAddress = personal.getString(TAG_HOME_ADDRESS);
					String homeCounty = personal.getString(TAG_HOME_COUNTY);
					String homePhone = personal.getString(TAG_HOME_PHONE);
					String homePostCode = personal.getString(TAG_HOME_POST_CODE);
					String homeType = personal.getString(TAG_HOME_TYPE);
					String mobilePhone = personal.getString(TAG_MOBILE);
					String name = personal.getString(TAG_NAME);
					String nextOfKinName = personal.getString(TAG_NEXT_OF_KIN_NAME);
					String nextOfKinPhone = personal.getString(TAG_NEXT_OF_KIN_PHONE);
				personalFields = new PersonalFields(directions,dob,email,homeAddress,homeCounty,homePhone,homePostCode,homeType,
													mobilePhone,name,nextOfKinName,nextOfKinPhone);
				
				
				int[] pregnanciesIds;
				try{
					JSONArray arr3 = j.getJSONArray(TAG_PREGNANCY_IDS);
					pregnanciesIds = new int[arr3.length()];
					for(int ii=0;ii<arr3.length();ii++){
						pregnanciesIds[ii]= arr3.getInt(ii);
					}
				}catch(Exception ex){
					pregnanciesIds = new int[1];
					pregnanciesIds[0]=0;
				}
				serviceUser = new ServiceUser(clinical,id,hospitalNumber,personalFields,pregnanciesIds,babyIds);
				myMap.put(id, serviceUser);
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return myMap;
	}

}
//Nick