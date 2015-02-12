package com.midwives.classes;

public class ClinicalFields{
	BloodType bloodType;
	String estimatedDeliveryDate;
	
	public ClinicalFields(BloodType blood, String estimatedDelivery){
		this.bloodType=blood;
		this.estimatedDeliveryDate=estimatedDelivery;
	}

	public String getBloodType() {
		return bloodType.getBloodType();
	}

	public void setBloodType(BloodType blood) {
		this.bloodType = blood;
	}

	public String getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}
	
}