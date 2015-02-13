package com.midwives.classes;



public class Pregnancies {
	
	private int idNum = 0; //patient 
	private int serviceUserId; //user/doctor/nurse
	private String estDeliveryDate;
	private String additionalInfo;
	private String birthMode; // not sure about what to declare this as, "Forceps" or "Svd"
	private String perineum;
	private String antiD;
	private String feeding;
	private String lastMenstrualPeriod;
	private String gestation;
	
	public Pregnancies(){}
	
	public Pregnancies(String gestation){// used in Appointment
		this.gestation=gestation;
	}
	
	public Pregnancies(int idNum, int serviceUserId, String estDeliveryDate, String additonalInfo, String birthMode, 
					String perineum, String antiD, String feeding, String lastMenstrualPeriod, String gestation) {
	
		this.idNum = idNum;
		this.serviceUserId = serviceUserId;
		this.estDeliveryDate = estDeliveryDate;
		this.additionalInfo = additonalInfo;
		this.birthMode = birthMode;
		this.perineum = perineum;
		this.antiD = antiD;
		this.feeding = feeding;
		this.lastMenstrualPeriod = lastMenstrualPeriod;
		this.gestation = gestation;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public int getServiceUserId() {
		return serviceUserId;
	}

	public void setServiceUserId(int serviceUserId) {
		this.serviceUserId = serviceUserId;
	}

	public String getEstDeliveryDate() {
		return estDeliveryDate;
	}

	public void setEstDeliveryDate(String estDeliveryDate) {
		this.estDeliveryDate = estDeliveryDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getBirthMode() {
		return birthMode;
	}

	public void setBirthMode(String birthMode) {
		this.birthMode = birthMode;
	}

	public String getPerineum() {
		return perineum;
	}

	public void setPerineum(String perineum) {
		this.perineum = perineum;
	}

	public String getAntiD() {
		return antiD;
	}

	public void setAntiD(String antiD) {
		this.antiD = antiD;
	}

	public String getFeeding() {
		return feeding;
	}

	public void setFeeding(String feeding) {
		this.feeding = feeding;
	}

	public String getLastMenstrualPeriod() {
		return lastMenstrualPeriod;
	}

	public void setLastMenstrualPeriod(String lastMenstrualPeriod) {
		this.lastMenstrualPeriod = lastMenstrualPeriod;
	}

	public String getGestation() {
		return gestation;
	}

	public void setGestation(String gestation) {
		this.gestation = gestation;
	}


}




