package com.midwives.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


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
