package com.midwives.classes;

public enum Priority {
	URGENT("urgent",0),
	ANNOING("annoying",1),
	CAN_WAIT("can wait",2),
	SHE_IS_FINE("she is fine",3),
	FALSE_ALARM("false alarm",4),
	OTHER("other",5);
	
	String priorityName;
	int priorityLevel;
	
	Priority(String name, int level){
		this.priorityName=name;
		this.priorityLevel=level;
	}

	public String getPriorityName() {
		return this.priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public int getPriorityLevel() {
		return this.priorityLevel;
	}

	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	
	

}
