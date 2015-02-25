package com.midwives.parsers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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
	
	



public static ArrayList<Announcements> parseAnnouncements(String dataList) {
	ArrayList<Announcements> mylist = new ArrayList <Announcements>();
	try {
		json = new JSONObject(dataList);
		jArray = json.getJSONArray(TAG_ARRAY);
		json = jArray.getJSONObject(0);
		int id = json.getInt(TAG_ID);
		String note = json.getString(TAG_NOTE);
		String date = json.getString(TAG_DATE);
		boolean blocking = json.getBoolean(TAG_BLOCKING);

		mylist.add(new Announcements(id, note ,date, blocking));
		
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return mylist;
}
	


}//close class AnnouncementParser

