package com.midwives.classes;

public enum VisitType {
	ANTE_NATAL("ante natal",0),
	PRE_NATAL("pre natal",1);
	
	private String visitTypeName;
	private int visitTypeId; 
	
	VisitType(String name, int id){
		this.visitTypeName=name;
		this.visitTypeId=id;
	}

	public String getVisitTypeName() {
		return this.visitTypeName;
	}

	public void setVisitTypeName(String visitTypeName) {
		this.visitTypeName = visitTypeName;
	}

	public int getVisitTypeId() {
		return this.visitTypeId;
	}

	public void setVisitTypeId(int visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	
	

}
 