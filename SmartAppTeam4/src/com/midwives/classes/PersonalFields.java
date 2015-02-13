package com.midwives.classes;


public class PersonalFields {
	
	private String homeAddress,homeType,homeCounty,homePostCode, directions;
	private String dob;
	private String email, homePhone, mobilePhone,name;
	private String nextOfKinPhone,nextOfKinName;
	
	
	/**
	 * for test only
	 */
	public PersonalFields(String name){
		this.name=name;
	}
	
	/**
	 * thats constructor for use!
	 * @param directions
	 * @param dob
	 * @param email
	 * @param homeAddress
	 * @param homeCounty
	 * @param homePhone
	 * @param homePostCode
	 * @param homeType
	 * @param mobilePhone
	 * @param name
	 * @param nextOfKinName
	 * @param nextOfKinPhone
	 */
	public PersonalFields(String directions, String dob, String email, String homeAddress, String homeCounty, String homePhone,
						String homePostCode,String homeType, String mobilePhone, String name, String nextOfKinName, String nextOfKinPhone){
		this.directions=directions;
		this.dob=dob;
		this.email=email;
		this.homeAddress=homeAddress;
		this.homeCounty=homeCounty;
		this.homePhone=homePhone;
		this.homePostCode=homePostCode;
		this.homeType=homeType;
		this.mobilePhone=mobilePhone;
		this.name=name;
		this.nextOfKinName=nextOfKinName;
		this.nextOfKinPhone=nextOfKinPhone;
		
	}


	
	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getHomeCounty() {
		return homeCounty;
	}

	public void setHomeCounty(String homeCounty) {
		this.homeCounty = homeCounty;
	}

	public String getHomePostCode() {
		return homePostCode;
	}

	public void setHomePostCode(String homePostCode) {
		this.homePostCode = homePostCode;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNextOfKinPhone() {
		return nextOfKinPhone;
	}

	public void setNextOfKinPhone(String nextOfKinPhone) {
		this.nextOfKinPhone = nextOfKinPhone;
	}

	public String getNextOfKinName() {
		return nextOfKinName;
	}

	public void setNextOfKinName(String nextOfKinName) {
		this.nextOfKinName = nextOfKinName;
	}
	




	//to be continue...
	
	
}

