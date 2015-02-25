package com.midwives.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
    
    public static ArrayList<String> getDateList(String dayOfWeek){
    	String day = dayOfWeek.toUpperCase();
		ArrayList<String> dayList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("EEE dd/MM/yyyy");

		
		switch(day){
		case "MONDAY": cal.setTime(nextDayOfWeek(cal.MONDAY).getTime());
			break;
		case "TUESDAY": cal.setTime(nextDayOfWeek(cal.TUESDAY).getTime());
			break;
		case "WEDNESDAY": cal.setTime(nextDayOfWeek(cal.WEDNESDAY).getTime());
			break;
		case "THURSDAY": cal.setTime(nextDayOfWeek(cal.THURSDAY).getTime());
			break;
		case "FRIDAY": cal.setTime(nextDayOfWeek(cal.FRIDAY).getTime());
			break;
		case "SATURDAY": cal.setTime(nextDayOfWeek(cal.SATURDAY).getTime());
			break;
		case "SUNDAY": cal.setTime(nextDayOfWeek(cal.SUNDAY).getTime());
			break;
		default: break;
		}
		
		for(int i=0;i<7;i++){
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

}
//Nick
