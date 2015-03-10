package com.midwives.classes;

public enum Rhesus {
	
	POSITIVE("Positive"),
	NEGATIVE("Negative");
	
	String rhesusValue;
	
	Rhesus(String rhesus){
		this.rhesusValue=rhesus;
	}


	public String getRhesusValue() {
		return rhesusValue;
	}

	public void setRhesusValue(String rhesusValue) {
		this.rhesusValue = rhesusValue;
	}
	

}
