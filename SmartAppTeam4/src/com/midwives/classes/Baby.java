package com.midwives.classes;


public class Baby {
	private String birthOutcome;
	private String screeningTest;
	private String name;
	private int id;
	private int pregnancyId;
	private String hospitalNumber;
	private String gender;
	private int weight;
	private String deliveryDateTime;
	private String vitaminK;
	private String hearingTest;
	private String newBornScreeningTest;
	
	public Baby(){}// used if no babies....
	
	
	public Baby(String birthOutcome, String deliveryDateTime, String gender, String hearing, String hospitalNumber,
				int id, String name, String screeaningTest, int pregnacyId, String vitaminK, int weight){
		this.birthOutcome=birthOutcome;
		this.deliveryDateTime=deliveryDateTime;
		this.gender=gender;
		this.hearingTest=hearing;
		this.hospitalNumber=hospitalNumber;
		this.id=id;
		this.name=name;
		this.screeningTest=screeaningTest;
		this.pregnancyId=pregnacyId;
		this.vitaminK=vitaminK;
		this.weight=weight;
		
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(String deliveryDateTime) {
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

	public String getBirthOutcome() {
		return birthOutcome;
	}

	public void setBirthOutcome(String birthOutcome) {
		this.birthOutcome = birthOutcome;
	}

	public String getScreeningTest() {
		return screeningTest;
	}

	public void setScreeningTest(String screeningTest) {
		this.screeningTest = screeningTest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

} //Close class  - test push3 working
// test comment

//ano ssdfjhsdjhdf	
