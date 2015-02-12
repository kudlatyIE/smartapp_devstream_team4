package com.midwives.classes;

import java.util.Date;

public class PersonDetails {
	
	private int userId, hospitalId;
	private String homeAddress,homeType,homeCountry,homePostCode, directions;
	private String dob;// change to Date................................................
	private String email, phoneHome, phoneMobile,personName;
	private String nextOfKinPhone,nextOfKinName;
	
	

	public PersonDetails(){}
	
	public PersonDetails(int id,String name){ //constructor for test only....
		this.userId=id;
		this.personName=name;
	}
	
	public PersonDetails(int id, int hospitalId, String address, String homeType, String homeCountry, String homePost, String homeDirect,
						String dob, String email, String homePh, String mobilePh, String name, String nextKinPhon, String nextKinName){
		this.userId=id;
		this.hospitalId=hospitalId;
		this.homeAddress=address;
		this.homeType=homeType;
		this.homeCountry=homeCountry;
		this.homePostCode=homePost;
		this.directions=homeDirect;
		this.dob=dob;
		this.email=email;
		this.phoneHome=homePh;
		this.phoneMobile=mobilePh;
		this.personName=name;
		this.nextOfKinPhone=nextKinPhon;
		this.nextOfKinName=nextKinName;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
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

	public String getHomeCountry() {
		return homeCountry;
	}

	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
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

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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

