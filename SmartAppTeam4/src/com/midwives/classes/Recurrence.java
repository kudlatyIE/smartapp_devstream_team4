package com.midwives.classes;

//not used, will be deleted.....

public enum Recurrence {
	DAILY("daily", false),
	WEEKLY("weekly",false),
	MONTHLY("monthly",false);
	
	private String recc;
	private boolean status;
	
	Recurrence(String recurrence, boolean status){
		this.recc=recurrence;
		this.status=status;
	}

	public String getReccName() {
		return recc;
	}

	public void setReccName(String recc) {
		this.recc = recc;
	}

	public boolean getReccurenceStatus() {
		return status;
	}

	public void setReccurenceStatus(boolean status) {
		this.status = status;
	}
	

}
