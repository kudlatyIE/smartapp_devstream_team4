package com.midwives.classes;

//not used, will be deleted... I think.

public enum BloodType {
	
	A_POSITIVE ("A+"),
	A_NEGATIVE ("A-"),
	B_POSITIVE ("B+"),
	B_NEGATIVE ("B-"),
	AB_POSITIVE ("AB+"),
	AB_NEGATIVE ("AB-"),
	ZERO_POSITIVE ("0+"),
	ZERO_NEGATIVE ("0-");
	
	
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
