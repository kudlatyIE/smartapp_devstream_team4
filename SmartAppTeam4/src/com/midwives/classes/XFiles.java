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

import org.apache.commons.lang3.StringUtils;


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
    public static String getAge(String stringDate){
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
			return "N/A";
			
		}
		return String.valueOf(age);
	}
    
    public static boolean isDeliveryComing(String edd){
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar calNow = Calendar.getInstance();
    	try {
    		return (df.parse(edd)).after(calNow.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
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
	 * For AppointmentCalendar activity
	 * create full time list with all appointments and free slots
	 * FreeSlots holds clinicId, date, and time - all to create a new appointment
	 * @param appointmentDay selected day - String 
	 * @param clinic current Clinic
	 * @param timeList String[] HH:m:ss
	 * @param fullMap HashMap: key: time(String), value: Appointment - fullMap all Appointments 
	 * @return ArrayList<Appointment> - all Appointments for date
	 */
	public static ArrayList<Appointment> getAppointmentsTimeList(String[] timeList, HashMap<String,Appointment> fullMap, String appointmentDay, Clinics clinic){
		ArrayList<Appointment> resultList = new ArrayList<Appointment>();
		
		for(String s:timeList){
			if(fullMap.containsKey(s)) {
				resultList.add(fullMap.get(s));
				System.out.println("method test: "+fullMap.get(s).getServiceUser().getName());
			}
			//create new empty appointment - used only in AppointmentCalendar Activity, to pass some basic data used on create NEW Appointment (FreeSlot)
			else resultList.add(new Appointment(clinic.getClinicId(),appointmentDay,s, false));
		}
		
		return resultList;
	}
	/**
	 * try extract firstName and LastName from fullName_string
	 * split two strings by " " and compare parts
	 * return int: 0 - perfect match(both strings are equals), higher value means: strings are more different (max value = string length)   
	 * @param str1 - first fullName string
	 * @param str2 - second fullName string
	 * @return Levenshtein Distance int 
	 */
	public static int compareNames(String str1, String str2){//like "FirstName LastName"
		int p1m1,p1m2,p2m1,p2m2;
		int bestMatch,sum1, sum2;
		int penalty; //if we try match single string to two strings.....
		String [] partsPattern,partsMatch;
		String pattern1,pattern2,match1, match2;
		String pattern=str1;
		String match=str2;
		//split name into firstName and lastName - as same with quote
		partsPattern = pattern.split(" ",2);
		partsMatch = match.split(" ", 2);
		
		if(partsPattern.length==1 && partsMatch.length==1){
			pattern1=partsPattern[0];
			match1=partsMatch[0];
			return StringUtils.getLevenshteinDistance(pattern1, match1);
		}
		
		if(partsPattern.length==1 && partsMatch.length==0){
			return partsPattern[0].length()/2;
		}
		
		if(partsPattern.length==1 && partsMatch.length>1){
			pattern1=partsPattern[0];
			match1=partsMatch[0];
			match2=partsMatch[1];
//			penalty=(int)0.5*pattern1.length();
			p1m1=StringUtils.getLevenshteinDistance(pattern1, match1);
			p1m2=StringUtils.getLevenshteinDistance(pattern1, match2);
			bestMatch=Math.min(p1m1, p1m2)+pattern1.length()/2;
			return bestMatch;
			
		}
		if(partsPattern.length>1 && partsMatch.length==1){
			pattern1=partsPattern[0]; 
			pattern2=partsPattern[1];
			match1=partsMatch[0];
//			penalty=(int)0.5*match1.length();
			p1m1=StringUtils.getLevenshteinDistance(pattern1, match1);
			p2m1=StringUtils.getLevenshteinDistance(pattern2, match1);
			bestMatch=Math.min(p1m1, p2m1)+match1.length()/2;
			return bestMatch;
			
		}else{
			pattern1=partsPattern[0]; 
			pattern2=partsPattern[1];
			match1=partsMatch[0];
			match2=partsMatch[1];
			p1m1=StringUtils.getLevenshteinDistance(pattern1, match1);
			p1m2=StringUtils.getLevenshteinDistance(pattern1, match2);
			p2m1=StringUtils.getLevenshteinDistance(pattern2, match1);
			p2m2=StringUtils.getLevenshteinDistance(pattern2, match2);
			sum1=p1m1+p2m2;
			sum2=p1m2+p2m1;
			bestMatch=Math.min(sum1, sum2);
			
			return bestMatch;
		}
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
