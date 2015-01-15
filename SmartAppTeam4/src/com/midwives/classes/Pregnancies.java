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
	
public Pregnancies(int idNum, int serviceUserId, String estDeliveryDate, String additonalInfo, String birthMode, String perineum, String antiD, String feeding, String lastMenstrualPeriod, String gestation) {
	
	this.idNum = idNum;
	this.serviceUserId = serviceUserId;
	this.estDeliveryDate = estDeliveryDate;
	this.additionalInfo = additionalInfo;
	this.birthMode = birthMode;
	this.perineum = perineum;
	this.antiD = antiD;
	this.feeding = feeding;
	this.lastMenstrualPeriod = lastMenstrualPeriod;
	this.gestation = gestation;
	

}

public void setidNum(int newId)
{
	idNum = newId;
}

public void setServiceUserId(int newServiceUserId)
{
	serviceUserId = newServiceUserId;
	
}

public void setestDeliveryDate(String newEstDeliveryDate)
{
	estDeliveryDate = newEstDeliveryDate;
}

public void setAdditionalInfo(String newAdditionalInfo)
{
	additionalInfo = newAdditionalInfo;
}
public void setBirthMode(String newBirthMode)
{
	birthMode = newBirthMode;
}

public void perineum(String newPerineum)
{
	perineum = newPerineum;
}

public void antiD(String newAntiD)
{
	antiD = newAntiD;
}
public void feeding(String newFeeding)
{
	feeding = newFeeding;
}

public void lastMenstrualPeriod(String newLastMenustrualPeriod)
{
	lastMenstrualPeriod = newLastMenustrualPeriod;
}

public void gestation(String newGestation)
{
	gestation = newGestation;
}

public int getidNum()
{
	return idNum;
	
}

public int getServiceUserId()
{
	return serviceUserId;
}
public String getEstDeliveryDate()
{
	return estDeliveryDate;
}

public String getAdditionalInfo()
{
	return additionalInfo;
	
}

public String getbirthMode()
{
	return birthMode;
}

public String getPerineum()
{
	return perineum;
}

public String getantiD()
{
	return antiD;
}
public String feeding()
{
	return feeding;
}

public String getLastMenstrualPeriod()
{
	return lastMenstrualPeriod;
}

public String getGestation()
{
	return gestation;
}

}




