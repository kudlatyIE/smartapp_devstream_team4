package com.midwives.classes;

//not used, will be deleted..........

public enum VisitType {
	ANTE_NATAL("ante natal"),
	PRE_NATAL("pre natal");
	
	private String visitTypeName;
	private int visitTypeId; 
	
	VisitType(String name){
		this.visitTypeName=name;
	}

	public String getVisitTypeName() {
		return this.visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	
	

}
 