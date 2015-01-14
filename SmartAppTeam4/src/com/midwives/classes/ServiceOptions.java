package com.midwives.classes;

public class ServiceOptions {
	
	private String serviceName;
	private int serviceId;
	
	ServiceOptions(String name, int id){
		this.serviceName=name;
		this.serviceId=id;
		
	}
	public String getServiceName() {
		return this.serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServiceId() {
		return this.serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	

}
