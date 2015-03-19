package com.midwives.parsers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.midwives.classes.Announcements;

public class AnnouncementsParser implements Serializable{
	private static final long serialVersionUID = 1L;
	private final static String TAG_ARRAY = "announcements";
	private final static String TAG_ID = "id";
	private final static String TAG_NOTE = "note";
	private final static String TAG_DATE = "date";
	private final static String TAG_BLOCKING = "blocking";
	private static JSONObject json;
	private static JSONArray jArray;
	

//	public static ArrayList<Announcements> parseAnnouncements(String dataList) {
	public static HashMap<Integer, Announcements> parseAnnouncements(String data){
//		ArrayList<Announcements> mylist = new ArrayList <Announcements>();
		HashMap<Integer,Announcements> myMap = new HashMap<Integer, Announcements>();
		try {
			json = new JSONObject(data);
			jArray = json.getJSONArray(TAG_ARRAY);
			json = jArray.getJSONObject(0);
			int id = json.getInt(TAG_ID);
			String note = json.getString(TAG_NOTE);
			String date = json.getString(TAG_DATE);
			boolean blocking = json.getBoolean(TAG_BLOCKING);

//			mylist.add(new Announcements(id, note ,date, blocking));
			myMap.put(id, new Announcements(id, note ,date, blocking));
		
		} catch (JSONException e) {
			myMap.put(0, new Announcements(0, "N/A" ,"", false));
			e.printStackTrace();
		}
		return myMap;
	}

}

