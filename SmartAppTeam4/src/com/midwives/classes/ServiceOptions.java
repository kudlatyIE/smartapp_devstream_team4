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
	
	public static ArrayList<ServiceOptions> createServiceList(){
		
		ArrayList<ServiceOptions> serviceList = new ArrayList<ServiceOptions>();
		
		serviceList.add(new ServiceOptions(0,"Domino (Dublin)"));
		serviceList.add(new ServiceOptions(1,"Domino (Wicklow)"));
		serviceList.add(new ServiceOptions(2,"ETH (Dublin)"));
		serviceList.add(new ServiceOptions(3,"ETH (Wicklow)"));
		serviceList.add(new ServiceOptions(4,"Satelite"));
		return serviceList;
	}
	

}
