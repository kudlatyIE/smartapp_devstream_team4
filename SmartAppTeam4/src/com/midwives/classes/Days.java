package com.midwives.classes;

public enum Days {
	MONDAY("mondat",false),
	TUESDAY("tuesday", false),
	WEDNESDAY("wednesday", false),
	THURSDAY("thursday",false),
	FRIDAY("friday",false),
	SATURDAY("saturday",false),
	SUNDAY("sunday",false);
    
    private String dayName;
	private boolean openStatus;
    
    Days(String name, boolean open){
    	this.dayName=name;
    	this.openStatus=open;
	}
    
    public String getDayName(){
    	return this.dayName;
    }
    
    public boolean getOpenStatus(){
    	return this.openStatus;
    }
    public void setDayName(String dayName){
    	this.dayName=dayName;
    }
    public void setOpenStatus(boolean status){
    	this.openStatus=status;
    }
}
