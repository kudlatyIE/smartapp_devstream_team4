package com.midwives.classes;

import java.util.ArrayList;

/**
 * Service provider as "Domino Dublin", "Domino Wicklow", "ETH Dublin", "Satelite"
 * @author kudlaty
 *
 */
public class ServiceOptions {
	
	private String serviceName;
	private int serviceId;
	private int[] clinicsIDs;
	
	public ServiceOptions(int id, String name, int [] clinicsIDs){
		this.serviceName=name;
		this.serviceId=id;
		this.clinicsIDs=clinicsIDs;
		
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
	
	public int[] getClinicsIDs() {
		return clinicsIDs;
	}
	public void setClinicsIDs(int[] clinicsIDs) {
		this.clinicsIDs = clinicsIDs;
	}
	
}
