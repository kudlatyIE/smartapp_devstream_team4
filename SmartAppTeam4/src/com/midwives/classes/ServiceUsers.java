package com.midwives.classes;

public class ServiceUsers {
	private int serviceUserId;
    private int hospitalNumber;  
    private String homeAddress; 
    private String homeType;
    private String homeCounty;
    private String homePostcode;
    private String directions;
    private String dob;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String name;
    private String nextOfKinPhone;
    private String nextOfKinName;
    
  public ServiceUsers (int ServiceUserId, int hospitalNumber, String homeAddress, String HomeType, String homeCounty, String homePostcode, String directions, String dob, String email, String homePhone, String mobilrPhone, String name, String nextOfKinPhone, String nextOfKinName) {
	  this.serviceUserId = serviceUserId;
	  this.hospitalNumber = hospitalNumber;
	  this.homeAddress = homeAddress;
	  this.homeType = homeType;
	  this.homeCounty = homeCounty;
	  this.homePostcode = homePostcode;
	  this.directions = directions;
	  this.dob = dob; 
	  this.email = email;
	  this.homePhone = homePhone;
	  this.mobilePhone = mobilePhone;
	  this.name = name;
	  this.nextOfKinPhone = nextOfKinPhone;
	  this .nextOfKinName = nextOfKinName;
	   
	  
  }
		  
public int getServiceUserId() {
	return serviceUserId;
}

public void setServiceUserId(int serviceUserId) {
	this.serviceUserId = serviceUserId;
}

public int getHospitalNumber() {
	return hospitalNumber;
}

public void setHospitalNumber(int hospitalNumber) {
	this.hospitalNumber = hospitalNumber;
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

public String getHomePostcode() {
	return homePostcode;
}

public void setHomePostcode(String homePostcode) {
	this.homePostcode = homePostcode;
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

} //close serviceUsers 

	
	
