package com.midwives.classes;
/**
 * Service provider as "Domino Dublin", "Domino Wicklow", "ETH Dublin", "Satelite"
 * @author kudlaty
 *
 */
public class ServiceOptions {
	
	private String serviceName;
	private int serviceId;
	
	public ServiceOptions(int id, String name){
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
