package com.midwives.classes;

import java.io.Serializable;

public class ServiceProvider implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4964762144329275003L;
	private int providerId;
	private String providerName, providerUserName, prividerEmail,jobOcupation,jobLevel, providerPrimaryPhone, providerSecondatPhone;
	private String providerPassword; // will chars array... or something
	private boolean activeStatus, adminRight;
	
	public ServiceProvider(boolean active,boolean admin, String email, int id, String jobLewel,
			String occupation, String name, String password, String primaryPhone,  String secondaryPhone,String userName){
		
		this.providerId=id;
		this.providerName=name;
		this.providerUserName=userName;
		this.prividerEmail=email;
		this.providerPassword=password;
		this.activeStatus=active;
		this.adminRight=admin;
		this.jobOcupation=occupation;
		this.jobLevel=jobLewel;
		this.providerPrimaryPhone=primaryPhone;
		this.providerSecondatPhone=secondaryPhone;
		
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderUserName() {
		return providerUserName;
	}

	public void setProviderUserName(String providerUserName) {
		this.providerUserName = providerUserName;
	}

	public String getPrividerEmail() {
		return prividerEmail;
	}

	public void setPrividerEmail(String prividerEmail) {
		this.prividerEmail = prividerEmail;
	}

	public String getJobOcupation() {
		return jobOcupation;
	}

	public void setJobOcupation(String jobOcupation) {
		this.jobOcupation = jobOcupation;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getProviderPrimaryPhone() {
		return providerPrimaryPhone;
	}

	public void setProviderPrimaryPhone(String providerPrimaryPhone) {
		this.providerPrimaryPhone = providerPrimaryPhone;
	}

	public String getProviderSecondatPhone() {
		return providerSecondatPhone;
	}

	public void setProviderSecondatPhone(String providerSecondatPhone) {
		this.providerSecondatPhone = providerSecondatPhone;
	}

	public String getProviderPassword() {
		return providerPassword;
	}

	public void setProviderPassword(String providerPassword) {
		this.providerPassword = providerPassword;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public boolean isAdminRight() {
		return adminRight;
	}

	public void setAdminRight(boolean adminRight) {
		this.adminRight = adminRight;
	}

}
