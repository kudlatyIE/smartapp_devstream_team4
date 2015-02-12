package com.midwives.classes;

public class ServiceUser {
	private ClinicalFields clinicalFields;
	private PersonDetails personDetails;
	private Baby babies;
	private Pregnancies pregnancies;
	
	//for object from appointments
	private String gestation, userId, userName;
	//user ID is a key
	public ServiceUser(String gestation, String id, String name){
		this.gestation=gestation;
		this.userId=id;
		this.userName=name;
	}
	
	public String getGestation() {
		return gestation;
	}

	public void setGestation(String gestation) {
		this.gestation = gestation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// full constructor
	public ServiceUser(ClinicalFields clinicalFields, PersonDetails person, Baby baby, Pregnancies pregnancies){
		this.clinicalFields=clinicalFields;
		this.personDetails=person;
		this.babies=baby;
		this.pregnancies=pregnancies;
	}

	public Pregnancies getPregnancies() {
		return pregnancies;
	}

	public void setPregnancies(Pregnancies pregnancies) {
		this.pregnancies = pregnancies;
	}

	public ClinicalFields getClinicalFields() {
		return clinicalFields;
	}

	public void setClinicalFields(ClinicalFields clinicalFields) {
		this.clinicalFields = clinicalFields;
	}

	public PersonDetails getPersonDetails() {
		return personDetails;
	}

	public void setPersonDetails(PersonDetails personDetails) {
		this.personDetails = personDetails;
	}

	public Baby getBabies() {
		return babies;
	}

	public void setBabies(Baby babies) {
		this.babies = babies;
	}
}
