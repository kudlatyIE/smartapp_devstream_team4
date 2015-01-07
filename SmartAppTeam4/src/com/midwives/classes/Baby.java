package com.midwives.classes;

import java.util.Date;

public class Baby {
	private int babyId;
	private int pregnancyId;
	private String hospitalNumber;
	private String gender;
	private double weight;
	private Date deliveryDateTime;
	private String vitaminK;
	private String hearingTest;
	private String newBornScreeningTest;
	
	public Baby(int babyId, int pregnancyId, String hospitalNumber, String gender, double weight, Date deliveryDateTime, String vitaminK, String hearingTest, String newBornScreeningTest ) {
		this.babyId = babyId;
		this.pregnancyId = pregnancyId;
		this.hospitalNumber = hospitalNumber;
		this.gender = gender;
		this.weight = weight;
		this.deliveryDateTime = deliveryDateTime;
		this.vitaminK = vitaminK;
		this.hearingTest = hearingTest;
		this.newBornScreeningTest = newBornScreeningTest;		
	}

	public int getBabyId() {
		return babyId;
	}

	public void setBabyId(int babyId) {
		this.babyId = babyId;
	}

	public int getPregnancyId() {
		return pregnancyId;
	}

	public void setPregnancyId(int pregnancyId) {
		this.pregnancyId = pregnancyId;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Date getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Date deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public String getVitaminK() {
		return vitaminK;
	}

	public void setVitaminK(String vitaminK) {
		this.vitaminK = vitaminK;
	}

	public String getHearingTest() {
		return hearingTest;
	}

	public void setHearingTest(String hearingTest) {
		this.hearingTest = hearingTest;
	}

	public String getNewBornScreeningTest() {
		return newBornScreeningTest;
	}

	public void setNewBornScreeningTest(String newBornScreeningTest) {
		this.newBornScreeningTest = newBornScreeningTest;
	}

} //Close class  - test push3 working
// test comment
//ano ssdfjhsdjhdf


