package com.midwives.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Collator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class XFiles {
	
    public static Calendar nextDayOfWeek(int dow) {
        Calendar date = Calendar.getInstance();
        int diff = dow - date.get(Calendar.DAY_OF_WEEK);
        if (!(diff > 0)) {
            diff += 7;
        }
        date.add(Calendar.DAY_OF_MONTH, diff);
        return date;
    }
//-----------------------------------------------------------------
    
    /**
     * create calendar for number of weeks, for specific weekdays
     * @param daysOfWeek when clinic is open, number of weeks to display
     * @return list(Calendar,String)
     */
    public static ArrayList<ClinicCalendar> getAllOpenDaysList(String []daysOfWeek, int numberOfWeeks){
    	ArrayList<ClinicCalendar> daysList = new ArrayList<ClinicCalendar>();
    	List<Date> sortedList= new ArrayList<Date>();
    	SimpleDateFormat formatter = new SimpleDateFormat("EEE dd/MM/yyyy");
    	String day;
    	Calendar cal;
    		
    		for(int i=0;i<daysOfWeek.length;i++){//repeat for next weekday
    			cal= Calendar.getInstance();
    			day= daysOfWeek[i].toUpperCase();
	    		cal = getDataOfWeekDay(cal,day);
    			for(int j=0;j<numberOfWeeks;j++){//get date of weekday for next 10 weeks
    	    		sortedList.add(cal.getTime());
    	    		
    	    		cal.add(Calendar.DAY_OF_YEAR, 7);
//        			System.out.println("cal.add.week: "+formatter.format(cal.getTime()));
    			}
    		}
    		Comparator<Date> comp = new CompareIt();
    		Collections.sort( sortedList,comp);
    		Calendar c = Calendar.getInstance();
    		for(Date d: sortedList){
    			c.setTime(d);
    			daysList.add(new ClinicCalendar(d,formatter.format(c.getTime())));
    		}
    	
		return daysList;
	}
    //-----------------------------------------------------------------------
    private static Calendar getDataOfWeekDay(Calendar cal, String day){
    	switch(day){
		case "MONDAY": cal.setTime(nextDayOfWeek(cal.MONDAY).getTime()); return cal;
		case "TUESDAY": cal.setTime(nextDayOfWeek(cal.TUESDAY).getTime()); return cal;
		case "WEDNESDAY": cal.setTime(nextDayOfWeek(cal.WEDNESDAY).getTime());; return cal;
		case "THURSDAY": cal.setTime(nextDayOfWeek(cal.THURSDAY).getTime());; return cal;
		case "FRIDAY": cal.setTime(nextDayOfWeek(cal.FRIDAY).getTime());; return cal;
		case "SATURDAY": cal.setTime(nextDayOfWeek(cal.SATURDAY).getTime());; return cal;
		case "SUNDAY": cal.setTime(nextDayOfWeek(cal.SUNDAY).getTime());; return cal;
		}
		return cal;
    }
    //----------------------------------------------------------------
    public static ArrayList<String> getDateList(String dayOfWeek){//for single weekday
    	String day = dayOfWeek.toUpperCase();
		ArrayList<String> dayList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("EEE dd/MM/yyyy");

//		
//		switch(day){
//		case "MONDAY": cal.setTime(nextDayOfWeek(cal.MONDAY).getTime());
//			break;
//		case "TUESDAY": cal.setTime(nextDayOfWeek(cal.TUESDAY).getTime());
//			break;
//		case "WEDNESDAY": cal.setTime(nextDayOfWeek(cal.WEDNESDAY).getTime());
//			break;
//		case "THURSDAY": cal.setTime(nextDayOfWeek(cal.THURSDAY).getTime());
//			break;
//		case "FRIDAY": cal.setTime(nextDayOfWeek(cal.FRIDAY).getTime());
//			break;
//		case "SATURDAY": cal.setTime(nextDayOfWeek(cal.SATURDAY).getTime());
//			break;
//		case "SUNDAY": cal.setTime(nextDayOfWeek(cal.SUNDAY).getTime());
//			break;
//		default: break;
//		}
		
		for(int i=0;i<7;i++){
			cal = getDataOfWeekDay(cal,day);
			dayList.add(formatter.format(cal.getTime()));
			cal.add(Calendar.DATE, 7);
		}
		return dayList;
	}
    
    /**
     * return age in years, for dob (PersonalFields class)
     * @param stringDate (DOB) yyyy-MM-dd 
     * @return int age years
     */
    public static int getAge(String stringDate){
		int age=0;
		Calendar cal = Calendar.getInstance();
		age = cal.get(Calendar.YEAR);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(stringDate);
			cal.setTime(date);
			age=age-cal.get(Calendar.YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}
    
    public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
    
    public static String getShortDate(String date){
    	Date d = new Date();
    	SimpleDateFormat shortDate = new SimpleDateFormat("dd MMM yyyy");
    	
    	return shortDate.format(d);
    }
    
    /**
     * method used to sort appointment map by appointment time....
     * @param map HashMap(K,V)
     * @return HashMap - sorted by key(string)
     */
    public static <K extends Comparable, V extends Comparable> HashMap<K,V> sortHashMapByKey(HashMap<K,V> map){
    	List<K> keys = new LinkedList<K>(map.keySet());
    	Collections.sort(keys, (Comparator<? super K>) new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				Collator collator = Collator.getInstance(Locale.getDefault());
				return collator.compare(o1, o2);
			}
    	});
    	HashMap<K,V> sortedMap = new HashMap<K,V>();
    	for(K key: keys){
    		sortedMap.put(key, map.get(key));
    	}
    	return sortedMap;
    }

    
    /**
     * create Time Array from openTime until CloseTime with set interval in minutes
     * @param open String (HH:mm:ss)
     * @param close String (HH:mm:ss)
     * @param interval int (time in minutes)
     * @return String[]
     */
    public static String[] getTimeList(String open, String close, int interval){
//    	HashMap<String,String> myMap = new HashMap<String, String>();
    	String[] myList;
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    	long difference;
    	try {
			Date startTime = df.parse(open);
			Date closeTime = df.parse(close);
			difference = closeTime.getTime()-startTime.getTime();
			double converter = 60*1000*interval;
			int result = (int) (difference/converter); //get quarters
//			System.out.println("difference: "+result);
			myList = new String[result];
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			for(int i=0;i<result;i++){
				myList[i] = open;
				cal.add(Calendar.MINUTE, interval);
				open = df.format(cal.getTime());
			}
			
			return myList;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
	 * use to parse selected date into string and compare with appointmentDates value (string date is a key in appointment HashMap) 
	 * @param cal
	 * @return
	 */
	public static String getStringFromCalendar(Calendar cal){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(cal.getTime());
	}
	
	
	//filter appointments matched for specific day - return Map with Time as a KEY------------------------------------------
//	private ArrayList<Appointment> getAppointmentsForDay(Date day, HashMap<Integer,Appointment> fullMap){
	public static HashMap<String,Appointment> getAppointmentsForDay(Date day, HashMap<Integer,Appointment> fullMap){
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		HashMap<String,Appointment> resultList = new HashMap<String,Appointment>();
		String date = getStringFromCalendar(cal);
		
		for(Appointment app:fullMap.values()){
//			if(date.equals(app.getAppDate())) resultList.add(app);
			if(date.equals(app.getAppDate())) resultList.put(app.getAppTime(),app);
		}
		
		return resultList;
	}
	/**
	 * create full time list with all appointments and free slots
	 * @param timeList String[] HH:m:ss
	 * @param fullMap all Appointments HashMap<String, Appointment>
	 * @return full ArrayList<Appointment>
	 */
	public static ArrayList<Appointment> getAppointmentsTimeList(String[] timeList, HashMap<String,Appointment> fullMap, String appointmentDay, Clinics clinic){
		ArrayList<Appointment> resultList = new ArrayList<Appointment>();
		
		for(String s:timeList){
			if(fullMap.containsKey(s)) {
				resultList.add(fullMap.get(s));
				System.out.println("method test: "+fullMap.get(s).getServiceUser().getName());
			}
			else resultList.add(new Appointment(clinic.getClinicId(),appointmentDay,s, false));
		}
		
		return resultList;
	}

}   

class CompareIt implements Comparator<Date>{

	@Override
	public int compare(Date arg0, Date arg1) {
		Date x = (Date) arg0;
		Date y = (Date) arg1;
		if(x.compareTo(y)<0) return -1;
		if(x.compareTo(y)>0) return 1;
		return 0;
	}
	
}

//Nick
