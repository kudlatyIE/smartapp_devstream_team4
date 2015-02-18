package com.midwives.classes;

public enum Priority {
	DROP_IN("drop_in"),
	SCHEDULED("scheduled");
	
	String priorityName;
	
	Priority(String name){
		this.priorityName=name;
	}

	public String getPriorityName() {
		return this.priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	
	

}
