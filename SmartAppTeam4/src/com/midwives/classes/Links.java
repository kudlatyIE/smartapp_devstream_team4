package com.midwives.classes;

public class Links {
	
	private String serviceOptions, serviceProviders,serviceUsers;
	
	private int id;//the same as relevant appointment ID!
	private ServiceOptions serviceOptionData;
	private ServiceProvider serviceProviderData;
	private ServiceUser serviceUserData;
	Links(){}
	/**
	 * storage URLs for
	 * @param options - ServiceOptions class
	 * @param providers - ServiceProvider class
	 * @param users - ServiceUser class
	 */
	public Links(String options, String providers, String users){
		this.serviceOptions=options;
		this.serviceProviders=providers;
		this.serviceUsers=users;
	}
	/**
	 * Link object hold downloaded data for specific Appointment
	 * @param id - appointment ID
	 * @param options - ServiceOptions class
	 * @param provider - ServiceProvider class
	 * @param user - ServiceUser class
	 */
	public Links(int id, ServiceOptions options, ServiceProvider provider, ServiceUser user){
		this.id=id;
		this.serviceOptionData=options;
		this.serviceProviderData=provider;
		this.serviceUserData=user;
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ServiceOptions getServiceOptionData() {
		return serviceOptionData;
	}

	public void setServiceOptionData(ServiceOptions serviceOptionData) {
		this.serviceOptionData = serviceOptionData;
	}

	public ServiceProvider getServiceProviderData() {
		return serviceProviderData;
	}

	public void setServiceProviderData(ServiceProvider serviceProviderData) {
		this.serviceProviderData = serviceProviderData;
	}

	public ServiceUser getServiceUserData() {
		return serviceUserData;
	}

	public void setServiceUserData(ServiceUser serviceUserData) {
		this.serviceUserData = serviceUserData;
	}
	

}
