package com.midwives.classes;

public class ClinicalFields{
//	private BloodType bloodType;
	private String bloodType;
	private String bmi;
	private String parity;
	private String obsteticHistory;
	private boolean rhesus;
//	private String estimatedDeliveryDate;
	
	public ClinicalFields(String bloodType, String bmi, String parity,
			String obsteticHistory, boolean rhesus) {
		this.bloodType = bloodType;
		this.bmi = bmi;
		this.parity = parity;
		this.obsteticHistory = obsteticHistory;
		this.rhesus = rhesus;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getParity() {
		return parity;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	public String getObsteticHistory() {
		return obsteticHistory;
	}

	public void setObstetricHistory(String obsteticHistory) {
		this.obsteticHistory = obsteticHistory;
	}

	public boolean getRhesus() {
		return rhesus;
	}

	public void setRhesus(boolean rhesus) {
		this.rhesus = rhesus;
	}
	
}
//Nick
