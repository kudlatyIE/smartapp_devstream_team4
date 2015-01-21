package com.midwives.classes;

public enum Days {
	MONDAY(1,"monday",false),
	TUESDAY(2,"tuesday", false),
	WEDNESDAY(3,"wednesday", false),
	THURSDAY(4,"thursday",false),
	FRIDAY(5,"friday",false),
	SATURDAY(6,"saturday",false),
	SUNDAY(0,"sunday",false),
	MISCELLANEOUS(10,"miscellaneous",false);
    
	private int dayId;
    private String dayName;
	private boolean openStatus;
    
    Days(int dayId,String name, boolean open){
    	this.dayId=dayId;
    	this.dayName=name;
    	this.openStatus=open;
	}
    
    public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
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
