/**
 * class not used, functionality holds by method XFiles.getAllOpenDaysList()
 */

package com.midwives.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProcessClinicWeeklyOpenDates {
	//fields, arrays, constants 
	private Calendar calendar;
	private Date date;
	private int  year;
	private int  month;
	private int  monthDay;
	private int  weekDay;
	private int  totalMonthDays;
	private String[] gridArrayDays;
		
	//private constructor
	public ProcessClinicWeeklyOpenDates(Calendar cal) {
		setCalendar(cal);          //gives a calendar instance
		setDate();                 //date (default time zone) in calendar
		setYear();                 //year year in calendar
		setMonth();                //Month in calendar
		setMonthDay();             // day of month in calendar
		setWeekDay();              // day of week in calendar
		setTotalMonthDays();       // total days of month in calendar
	}
	
	public void setMonthDay(int day) {
		if(day < totalMonthDays)
		  monthDay = day;
	}

	//static getters-setters methods	
	public Calendar getCalendar() {
		return calendar;
	}
	
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Date getDate() {
		return date; 
	}

	public void setDate() {
		this.date = calendar.getTime();
	}

	public int getYear() {
		return year;
	}

	public void setYear() {
		this.year = calendar.get(Calendar.YEAR);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth() {
		this.month = calendar.get(Calendar.MONTH);
	}

	public int getMonthDay() {
		return monthDay;
	}

	public void setMonthDay() {
		this.monthDay = calendar.get(Calendar.DAY_OF_MONTH);
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay() {
		this.weekDay = calendar.get(Calendar.DAY_OF_WEEK);
	}

	public int getTotalMonthDays() {
		return totalMonthDays;
	}

	public void setTotalMonthDays() {
		this.totalMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public String get_Month_Year() {
		return getMonthName() + " " + String.valueOf(getYear());
		
	}
	
	public String[] getGridArrayDays() {
		int count = getMonthDay(); // set count to month day
		switch(getWeekDay()){
		case 1:
			int u = (getTotalMonthDays() - getMonthDay()) + 1;  //the 1 = case the u is array length
			gridArrayDays = new String[u];
			//no need to add empty strings for case 1 ie. day of week is sun maps to index 0
			for(int j = 0; j < u; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}				
			break;
		case 2:                                                  //day of week is mon maps to index 1
			int p = (getTotalMonthDays() - getMonthDay()) + 2;  //the 2 = case the n is array length
			gridArrayDays = new String[p];
			for(int i = 0; i < 1; i++){                             // the 1 is 2-1 ie. (case2 - 1)
				gridArrayDays[i] = "";
			}
			for(int j = 1; j < p; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}
			break;
		case 3:                                                  //day of week is tue maps to index 2
			int o = (getTotalMonthDays() - getMonthDay()) + 3;  //the 3 = case the n is array length
			gridArrayDays = new String[o];
			for(int i = 0; i < 2; i++){                             // the 2 is 3-1 ie. (case3 - 1)
				gridArrayDays[i] = "";
			}
			for(int j = 6; j < o; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}
			break;
		case 4:                                                 //day of week is wed maps to index 3
			int q = (getTotalMonthDays() - getMonthDay()) + 4;  //the 4 = case the n is array length
			gridArrayDays = new String[q];
			for(int i = 0; i < 3; i++){                             // the 3 is 4-1 ie. (case3 - 1)
				gridArrayDays[i] = "";
			}
			for(int j = 3; j < q; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}
			break;
		case 5:                                                 //day of week is thur maps to index 4
			int l = (getTotalMonthDays() - getMonthDay()) + 5;  //the 5 = case the l is array length
			gridArrayDays = new String[l];
			for(int i = 0; i < 4; i++){                             // the 4 is 5-1 ie. case5 - 1
				gridArrayDays[i] = "";
			}
			for(int j = 4; j < l; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}
			break;
		case 6:                                                 //day of week is fri maps to index 5
			int m = (getTotalMonthDays() - getMonthDay()) + 6;  //the 6 = case the m is array length
			gridArrayDays = new String[m];
			for(int i = 0; i < 5; i++){                             		  // the 5 is 6-1 ie. (case6 - 1)
				gridArrayDays[i] = "";
			}
			for(int j = 5; j < m; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}
			break;
		case 7:                                                 //day of week is sat maps to index 6
			int n = (getTotalMonthDays() - getMonthDay()) + 7;  //the 7 = case the n is array length
			gridArrayDays = new String[n];
			for(int i = 0; i < 6; i++){                             // the 6 is 7-1 ie. (case7 - 1)
				gridArrayDays[i] = "";
			}
			for(int j = 6; j < n; j++){
				gridArrayDays[j] = String.valueOf(count);
				count ++;
			}
			break;		
		}//close switch
		
		return gridArrayDays;
	}
	
	public String getMonthName(){                //matches month number (starts 0 in calendar class) to month name
	    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
	    return monthNames[getMonth()];
	}
	
	public String getWeekDayName(int dayNumber){             //match week name (starts at 1 in calendar class)
		String[] dayNames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
		return dayNames[dayNumber -1];      //note:- in calendar class week day starts at one not the array index start zero
	}
	
	public int[] getSelectDaysIntArray(String[] selectDaysStringArray){
		int[] dayNum = new int[selectDaysStringArray.length];
		String[] dayNames = new String[] {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
		for(int i = 0; i < selectDaysStringArray.length; i++){
			for(int j = 0; j < 7; j++){
				if(selectDaysStringArray[i].equalsIgnoreCase(dayNames[j])){
					dayNum[i] = j + 1;  //note:- the plus 1 to offset difference of zero array start and the calendar day start of 1
				}
			}
		}
		return dayNum;
	}
	
	public ArrayList<String> getClinicWeeklyOpenDatesArray(ProcessClinicWeeklyOpenDates p, String[] selectDaysStringArray) {	
		int[] selectDaysIntArray = p.getSelectDaysIntArray(selectDaysStringArray);
		int counter = p.getWeekDay();
		ArrayList<String> clinicWeeklyOpenDates = new ArrayList<String>();
		for(int i = p.getMonthDay(); i <= p.getTotalMonthDays(); i++){
			
			for(int j = 0; j < selectDaysIntArray.length; j++) {
				if(counter == selectDaysIntArray[j]){
					clinicWeeklyOpenDates.add( p.getWeekDayName(counter) + "  " + String.valueOf(i) + "  " + p.get_Month_Year());  
				}
			}
			
			//look after rotation of weekday numbers
			if(counter <7) {
				counter++;
			} else {
				counter = 1;
			}				
		}
		return clinicWeeklyOpenDates;
	}

}//close class ProcessclinicweeklyopenDates
