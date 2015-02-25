package com.midwives.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.midwives.classes.Baby;
import com.midwives.classes.ClinicalFields;
import com.midwives.classes.PersonalFields;
import com.midwives.classes.Pregnancies;
import com.midwives.classes.ServiceUser;

public class ServiceUserParser {
	
	
	private static JSONObject json;
	private static JSONObject j;
	private static JSONArray jArray;
	
	//key for Babies
	private final static String TAG_BABIES_ARRAY = "babies";//------JSONArray
	private final static String TAG_BIRTH_OUTCOME="birth_outcome";
	private final static String TAG_DELIVERY_DATE_TIME="delivery_date_time";
	private final static String TAG_GENDER="gender";
	private final static String TAG_HEARING="hearing";
	private final static String TAG_HOSPITAL_NO="hospital_number";
	private final static String TAG_ID="id";
	private final static String TAG_NAME="name";
	private final static String TAG_SCREENING_TEST="newborn_screening_test";
	private final static String TAG_PREGNANCY_ID="pregnancy_id";
	private final static String TAG_VITAMIN_K="vitamin_k";
	private final static String TAG_WEIGHT="weight";
	//keys for Pregnancies
	private final static String TAG_PREGANCIES_ARRAY="pregnancies";//------JSONArray
	private final static String TAG_INFO="additional_info";
	private final static String TAG_ANTI_D="anti_d";
	private final static String TAG_BABY_IDS="baby_ids"; //----------- int []
	private final static String TAG_BIRTH_MODE="birth_mode";//---------String []
	private final static String TAG_CREATED_AT="created_at";
	private final static String TAG_ESTIMATED_DELIVERY_DATE="estimated_delivery_date";
	private final static String TAG_FEEDING="feeding";//------------- can be NULL
	private final static String TAG_GESTATION="gestation";//--------- can be NULL
	private final static String TAG_LAST_PERIOD="last_menstrual_period";
	private final static String TAG_PERINEUM="perineum";
	private final static String TAG_SERVICE_USER_ID="service_user_id";
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
	
	private static ArrayList<Baby>baby;
	private static ArrayList<Pregnancies> pregnancies;
	private static ClinicalFields clinical;
	private static ServiceUser serviceUser;
	private static PersonalFields personalFields;
	private static  ArrayList<ServiceUser> myList;
	
	/**
	 * 
	 * @param data json String
	 * @return single object of ServiceUser
	 */
	public static ServiceUser parseServiceUserId(String data){
		
		try {
			baby = new ArrayList<Baby>();
			pregnancies = new ArrayList<Pregnancies>();
//			myList = new ArrayList<ServiceUser>();
			
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_BABIES_ARRAY);
			//get Babies data
			for(int i =0;i<jArray.length();i++){
				j=jArray.getJSONObject(i);
				String birthOutcome = j.getString(TAG_BIRTH_OUTCOME);
				String deliveryDate = j.getString(TAG_DELIVERY_DATE_TIME);
				String gender = j.getString(TAG_GENDER);
				String hearing = j.getString(TAG_HEARING);
				String hospitalNo = j.getString(TAG_HOSPITAL_NO);
				int babyId = j.getInt(TAG_ID);
				String babyName = j.getString(TAG_NAME);
				String screening = j.getString(TAG_SCREENING_TEST);
				int pregnancyId = j.getInt(TAG_PREGNANCY_ID);
				String vitaminK = j.getString(TAG_VITAMIN_K);
				int weight = j.getInt(TAG_WEIGHT);
				
				//bugs hunting....
				baby.add(new Baby(birthOutcome,deliveryDate,gender,hearing,hospitalNo,babyId,babyName,
									screening,pregnancyId,vitaminK,weight));
//				baby.add(new Baby(babyId, babyName, hearing));
			}// get Pregnancy data ---------------------------------------------
			jArray = json.getJSONArray(TAG_PREGANCIES_ARRAY);
			for(int i=0;i<jArray.length();i++){
				j=jArray.getJSONObject(i);
				String info = j.getString(TAG_INFO);
				String antiD=j.getString(TAG_ANTI_D);
				
				int[] babyIds;
				JSONArray arr = j.getJSONArray(TAG_BABY_IDS);
				if(arr.length()>0){
					babyIds = new int[arr.length()];
					for(int ii=0;ii<arr.length();ii++){
						babyIds[ii]= arr.getInt(ii);
//						Log.e("babies IDs: ",String.valueOf( babyIds[ii]));
					}
				}else {// if baby_ids == NULL, return one id=0
					babyIds = new int[1];
					babyIds[0]=0;
				}
				String [] birthMode;
				JSONArray arr2 = j.getJSONArray(TAG_BIRTH_MODE);
				if(arr2.length()>0){
					birthMode = new String[arr2.length()];
					for(int ii=0;ii<arr2.length();ii++){
						birthMode[ii]=arr2.getString(ii);
//						Log.e("birth_mode", birthMode[ii]);
					}
				}else{//if birth_mode[] empty return [0]=""
					birthMode = new String[1];
					birthMode[0]="";
				}
				String createdAt = j.getString(TAG_CREATED_AT);
				String estimatedDeliveryDate = j.getString(TAG_ESTIMATED_DELIVERY_DATE);
				//need to handle when value is null------------------------------------------------------
				String feeding;
				try{
					feeding = j.getString(TAG_FEEDING);
				}catch(JSONException e){
					feeding = "n/a";
				}

				String gestation = j.getString(TAG_GESTATION);
				int pregnanciesId = j.getInt(TAG_ID);
				String lastPeriod = j.getString(TAG_LAST_PERIOD);
				String perineum = j.getString(TAG_PERINEUM);
				int serviceUserId = j.getInt(TAG_SERVICE_USER_ID);
				
				pregnancies.add(new Pregnancies(info, antiD,babyIds,birthMode,createdAt,estimatedDeliveryDate,feeding,gestation,
												pregnanciesId,lastPeriod,perineum,serviceUserId));
			}//get service_user data ------------------------------------------------------
			jArray = json.getJSONArray(TAG_SERVICE_USER_ARRAY);
			if(jArray.length()!=1){
				Log.e("ServiceUser ParserID", "users num: "+jArray.length()+"!!!! - retur only first");
			}
				j=jArray.getJSONObject(0);
				int[] babyIds;
				JSONArray arr = j.getJSONArray(TAG_BABY_IDS);
				if(arr.length()>0){
					babyIds = new int[arr.length()];
					for(int ii=0;ii<arr.length();ii++){
						babyIds[ii]= arr.getInt(ii);
//						Log.e("babies IDs: ",String.valueOf( babyIds[ii]));
					}
				}else {// if baby_ids == NULL, return one id=0
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
				JSONArray arr3 = j.getJSONArray(TAG_PREGNANCY_IDS);
				if(arr.length()>0){
					pregnanciesIds = new int[arr3.length()];
					for(int ii=0;ii<arr3.length();ii++){
						pregnanciesIds[ii]= arr3.getInt(ii);
//						Log.e("babies IDs: ",String.valueOf( pregnanciesIds[ii]));
					}
				}else {// if baby_ids == NULL, return one id=0
					pregnanciesIds = new int[1];
					pregnanciesIds[0]=0;
				}
				//uff.. that's last object, so I can return an object or array of objects...
				serviceUser = new ServiceUser(baby,pregnancies,babyIds,clinical,hospitalNumber,id,personalFields,pregnanciesIds);
//				myList.add(serviceUser);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return serviceUser;
	}
	
	
	//return list of all objects
	/**
	 * 
	 * @param data json String
	 * @return ArrayList of all ServiceUsers...
	 */
	public static ArrayList<ServiceUser> parseServiceUser(String data){
		
		try {
			baby = new ArrayList<Baby>();
			pregnancies = new ArrayList<Pregnancies>();
			myList = new ArrayList<ServiceUser>();
			
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_BABIES_ARRAY);
			//get Babies data
			for(int i =0;i<jArray.length();i++){
				j=jArray.getJSONObject(i);
				String birthOutcome = j.getString(TAG_BIRTH_OUTCOME);
				String deliveryDate = j.getString(TAG_DELIVERY_DATE_TIME);
				String gender = j.getString(TAG_GENDER);
				String hearing = j.getString(TAG_HEARING);
				String hospitalNo = j.getString(TAG_HOSPITAL_NO);
				int babyId = j.getInt(TAG_ID);
				String babyName = j.getString(TAG_NAME);
				String screening = j.getString(TAG_SCREENING_TEST);
				int pregnancyId = j.getInt(TAG_PREGNANCY_ID);
				String vitaminK = j.getString(TAG_VITAMIN_K);
				int weight = j.getInt(TAG_WEIGHT);
				
				//bugs hunting....
				baby.add(new Baby(birthOutcome,deliveryDate,gender,hearing,hospitalNo,babyId,babyName,
									screening,pregnancyId,vitaminK,weight));
//				baby.add(new Baby(babyId, babyName, hearing));
			}// get Pregnancy data ---------------------------------------------
			jArray = json.getJSONArray(TAG_PREGANCIES_ARRAY);
			for(int i=0;i<jArray.length();i++){
				j=jArray.getJSONObject(i);
				String info = j.getString(TAG_INFO);
				String antiD=j.getString(TAG_ANTI_D);
				
				int[] babyIds;
				JSONArray arr = j.getJSONArray(TAG_BABY_IDS);
				if(arr.length()>0){
					babyIds = new int[arr.length()];
					for(int ii=0;ii<arr.length();ii++){
						babyIds[ii]= arr.getInt(ii);
//						Log.e("babies IDs: ",String.valueOf( babyIds[ii]));
					}
				}else {// if baby_ids == NULL, return one id=0
					babyIds = new int[1];
					babyIds[0]=0;
				}
				String [] birthMode;
				JSONArray arr2 = j.getJSONArray(TAG_BIRTH_MODE);
				if(arr2.length()>0){
					birthMode = new String[arr2.length()];
					for(int ii=0;ii<arr2.length();ii++){
						birthMode[ii]=arr2.getString(ii);
//						Log.e("birth_mode", birthMode[ii]);
					}
				}else{//if birth_mode[] empty return [0]=""
					birthMode = new String[1];
					birthMode[0]="";
				}
				String createdAt = j.getString(TAG_CREATED_AT);
				String estimatedDeliveryDate = j.getString(TAG_ESTIMATED_DELIVERY_DATE);
				//need to handle when value is null------------------------------------------------------
				String feeding;
				try{
					feeding = j.getString(TAG_FEEDING);
				}catch(JSONException e){
					feeding = "n/a";
				}

				String gestation = j.getString(TAG_GESTATION);
				int pregnanciesId = j.getInt(TAG_ID);
				String lastPeriod = j.getString(TAG_LAST_PERIOD);
				String perineum = j.getString(TAG_PERINEUM);
				int serviceUserId = j.getInt(TAG_SERVICE_USER_ID);
				
				pregnancies.add(new Pregnancies(info, antiD,babyIds,birthMode,createdAt,estimatedDeliveryDate,feeding,gestation,
												pregnanciesId,lastPeriod,perineum,serviceUserId));
			}//get service_user data ------------------------------------------------------
			jArray = json.getJSONArray(TAG_SERVICE_USER_ARRAY);
			for(int i=0;i<jArray.length();i++){
				j=jArray.getJSONObject(i);
				int[] babyIds;
				JSONArray arr = j.getJSONArray(TAG_BABY_IDS);
				if(arr.length()>0){
					babyIds = new int[arr.length()];
					for(int ii=0;ii<arr.length();ii++){
						babyIds[ii]= arr.getInt(ii);
//						Log.e("babies IDs: ",String.valueOf( babyIds[ii]));
					}
				}else {// if baby_ids == NULL, return one id=0
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
				JSONArray arr3 = j.getJSONArray(TAG_PREGNANCY_IDS);
				if(arr.length()>0){
					pregnanciesIds = new int[arr3.length()];
					for(int ii=0;ii<arr3.length();ii++){
						pregnanciesIds[ii]= arr3.getInt(ii);
//						Log.e("babies IDs: ",String.valueOf( pregnanciesIds[ii]));
					}
				}else {// if baby_ids == NULL, return one id=0
					pregnanciesIds = new int[1];
					pregnanciesIds[0]=0;
				}
				//uff.. that's last object, so I can return an object or array of objects...
				serviceUser = new ServiceUser(baby,pregnancies,babyIds,clinical,hospitalNumber,id,personalFields,pregnanciesIds);
				myList.add(serviceUser);
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return myList;
	}

}
//Nick