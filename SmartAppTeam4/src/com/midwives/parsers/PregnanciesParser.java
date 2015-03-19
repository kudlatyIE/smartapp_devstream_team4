package com.midwives.parsers;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.midwives.classes.Pregnancies;

public class PregnanciesParser {
	
	private final static String TAG_ARRAY="pregnancies";
	private final static String TAG_ID="id";
	private final static String TAG_SERVIDE_USER_ID="service_user_id";
	private final static String TAG_EDD="estimated_delivery_date";
	private final static String TAG_INFO="additional_info";
	private final static String TAG_BIRTH_MODE="birth_mode";//String[]
	private final static String TAG_PERINEUM="perineum";
	private final static String TAG_ANTI_D="anti_d";
	private final static String TAG_FEEDING="feeding";
	private final static String TAG_LAST_PERIOD="last_menstrual_period";
	private final static String TAG_GESTSTION="gestation";
	private final static String TAG_BABY_IDS="baby_ids"; //int []
	
	private static JSONArray jArray;
	private static JSONObject json;
	
	
//	public static ArrayList<Pregnancies> parsePregnancies(String data){
	public static HashMap<Integer, Pregnancies> parsePregnancies(String data){
		HashMap<Integer, Pregnancies> myMap = new HashMap<Integer, Pregnancies>();
//		ArrayList<Pregnancies> myList = new ArrayList<Pregnancies>();
		
		try{
			json = new JSONObject(data);
			 
			jArray = json.getJSONArray(TAG_ARRAY);
			for(int i=0;i<jArray.length();i++){
				 json = jArray.getJSONObject(i);
				 int id = json.getInt(TAG_ID);
				 int userId = json.getInt(TAG_SERVIDE_USER_ID);
				 String edd = json.getString(TAG_EDD);
				 String info = json.getString(TAG_INFO);
				 
				 String[] birthMode;
				 try{
					 JSONArray arrstr = json.getJSONArray(TAG_BIRTH_MODE);
					 birthMode = new String[arrstr.length()];
					 for(int j=0;j<arrstr.length();j++){
						 birthMode[j]= arrstr.getString(j);
						 Log.e("birthMode[+"+j+"]= ", birthMode[j]);
					 }
				 }catch(Exception ex){
					 birthMode= new String[1];
					 birthMode[0]="N/A";
				 }
				 
				 String perineum = json.getString(TAG_PERINEUM);
				 String antiD = json.getString(TAG_ANTI_D);
				 String feeding = json.getString(TAG_FEEDING);
				 String period = json.getString(TAG_LAST_PERIOD);
				 String gestation = json.getString(TAG_GESTSTION);
				 int [] babyIds;
				 try{
					 JSONArray arrInt = json.getJSONArray(TAG_BABY_IDS);
					 babyIds = new int[arrInt.length()];
					 for(int j=0;j<arrInt.length();j++){
						 babyIds[j] = arrInt.getInt(j);
						 Log.e("Baby_IDs[+"+j+"]: ", String.valueOf(babyIds[j]));
					 }
				 }catch(Exception ex){
					 babyIds = new int[1];
					 babyIds[0]=0; 
				 }
				 
//				 myList.add(new Pregnancies(id,userId,edd,info,birthMode,perineum,antiD,feeding,period,babyIds,gestation));
				 myMap.put(id, new Pregnancies(id,userId,edd,info,birthMode,perineum,antiD,feeding,period,babyIds,gestation));
				 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return myMap;
	}
	

}
