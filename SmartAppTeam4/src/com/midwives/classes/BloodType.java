package com.midwives.classes;

//used with spinner list: AnteNatalEdit

public enum BloodType {
	
	EMPTY("N/A"),
	A ("A"),
	B ("B"),
	AB ("AB"),
	ZERO ("O");
	
	//to be continue....................
	
	String bloodType;
	
	BloodType(String bloodType){
		this.bloodType=bloodType;
	}
	public String getBloodType(){
		return this.bloodType;
	}
	public void setBloodType(String blood){
		this.bloodType=blood.toUpperCase();
	}

}
