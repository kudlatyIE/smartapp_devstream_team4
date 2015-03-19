package com.midwives.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.midwives.classes.Baby;

public class BabiesParser {

	private final static String TAG_ARRAY="babies";
	private final static String TAG_BIRTH_OUTCOME="birth_outcome";
	private final static String TAG_SCREENING_TEST="newborn_screening_test";
	private final static String TAG_HEARNG="hearing";
	private final static String TAG_VITAMIN_K="vitamin_k";
	private final static String TAG_DELIVERY_DATE_TIME="delivery_date_time";
	private final static String TAG_WEIGHT="weight";
	private final static String TAG_GENDER="gender";
	private final static String TAG_HOSPITAL_NO="hospital_number";
	private final static String TAG_PREGNANCY_ID="pregnancy_id";
	private final static String TAG_ID="id";
	private final static String TAG_NAME="name";
	
	private static String jsonString;
//	private static Appointment appData;
	
	private static JSONArray jArray;
	private static JSONObject json;
	
	
//	public static ArrayList<Baby> parseBabiesList(String data){
//		ArrayList<Baby> babyList = new ArrayList<Baby>();
		public static HashMap<Integer,Baby> parseBabyMap(String data){
			HashMap<Integer,Baby> babyMap = new HashMap<Integer,Baby>();
		try{
			json = new JSONObject(data);
			 
			jArray = json.getJSONArray(TAG_ARRAY);
			for(int i=0;i<jArray.length();i++){
				 json = jArray.getJSONObject(i);
				 String name = json.getString(TAG_NAME);
				 String birthOutcome = json.getString(TAG_BIRTH_OUTCOME);
				 String screeningTest = json.getString(TAG_SCREENING_TEST);
				 String hearing = json.getString(TAG_HEARNG);
				 String vitaminK = json.getString(TAG_VITAMIN_K);
				 String deliveryDate = json.getString(TAG_DELIVERY_DATE_TIME);
				 int weight;
				 try{
					 weight = json.getInt(TAG_WEIGHT);
				 }catch(Exception ex){
					 weight=0;
				 }
				 String gender = json.getString(TAG_GENDER);
				 String hospitalNo = json.getString(TAG_HOSPITAL_NO);
				 int pregnancyId = json.getInt(TAG_PREGNANCY_ID);
				 int id = json.getInt(TAG_ID);
				 
//				 babyList.add(new Baby(birthOutcome,deliveryDate,gender, hearing,hospitalNo,id,name,screeningTest,pregnancyId,vitaminK,weight));
				 babyMap.put(Integer.valueOf(id), new Baby(birthOutcome,deliveryDate,gender, hearing,hospitalNo,id,name,screeningTest,pregnancyId,vitaminK,weight));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return babyMap;
		
	}
}
