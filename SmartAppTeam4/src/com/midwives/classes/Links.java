package com.midwives.classes;

public class Links {
	
	private String serviceOptions, serviceProviders,serviceUsers;
	
	Links(){}
	
	public Links(String options, String providers, String users){
		this.serviceOptions=options;
		this.serviceProviders=providers;
		this.serviceUsers=users;
	}

	public String getServiceOptions() {
		return serviceOptions;
	}

	public void setServiceOptions(String serviceOptions) {
		this.serviceOptions = serviceOptions;
	}

	public String getServiceProviders() {
		return serviceProviders;
	}

	public void setServiceProviders(String serviceProviders) {
		this.serviceProviders = serviceProviders;
	}

	public String getServiceUsers() {
		return serviceUsers;
	}

	public void setServiceUsers(String serviceUsers) {
		this.serviceUsers = serviceUsers;
	}
	

}
