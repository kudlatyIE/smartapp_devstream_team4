package com.midwives.classes;

public class ServiceUser {
	private ClinicalFields clinicalFields;
	private int id;
	private String hospitalNumber;
	private PersonalFields personalFields;
	private Baby []babies;
	private Pregnancies [] pregnancies;
	
	private int [] pregnencyIds, babyIds;
	
	private Pregnancies pregnancy;
	
	//test - used with Appointemt class
	public ServiceUser(Pregnancies gestation, int id, PersonalFields name){
		this.pregnancy=gestation;
		this.id=id;
		this.personalFields=name;
	}
	
	/**
	 * constructor to use...  or not to use
	 * @param clinicalFields - ClinicalFields class object
	 * @param id
	 * @param hospitalName
	 * @param person - PersonalFields class object
	 * @param baby - array of Baby class objects
	 * @param pregnancies - array of Pregnancies class object
	 */
	public ServiceUser(ClinicalFields clinicalFields, int id, String hospitalName, PersonalFields person, Baby [] baby, Pregnancies [] pregnancies){
		this.clinicalFields=clinicalFields;
		this.personalFields=person;
		this.babies=baby;
		this.pregnancies=pregnancies;
	}
	/**
	 * constructor to use - I hope.. this one is better
	 * @param clinicalFields - ClinicalFields class object
	 * @param id
	 * @param hospitalName
	 * @param person - PersonalFields class object
	 * @param baby - array of Baby class objects
	 * @param pregnancies - array of Pregnancies class object
	 * @param pregancyIds - int array
	 * @param babyIds - int array
	 */
	public ServiceUser(ClinicalFields clinicalFields, int id, String hospitalName, PersonalFields person, Baby [] baby, Pregnancies [] pregnancies,
						int[] pregancyIds, int[] babyIds){
		this.clinicalFields=clinicalFields;
		this.personalFields=person;
		this.babies=baby;
		this.pregnancies=pregnancies;
		this.pregnencyIds=pregancyIds;
		this.babyIds=babyIds;
	}




	public ClinicalFields getClinicalFields() {
		return clinicalFields;
	}

	public void setClinicalFields(ClinicalFields clinicalFields) {
		this.clinicalFields = clinicalFields;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	public PersonalFields getPersonalFields() {
		return personalFields;
	}

	public void setPersonalFields(PersonalFields personalFields) {
		this.personalFields = personalFields;
	}

	public Baby[] getBabies() {
		return babies;
	}

	public void setBabies(Baby[] babies) {
		this.babies = babies;
	}

	public Pregnancies[] getPregnancies() {
		return pregnancies;
	}

	public void setPregnancies(Pregnancies[] pregnancies) {
		this.pregnancies = pregnancies;
	}

	public int[] getPregnencyIds() {
		return pregnencyIds;
	}

	public void setPregnencyIds(int[] pregnencyIds) {
		this.pregnencyIds = pregnencyIds;
	}

	public int[] getBabyIds() {
		return babyIds;
	}

	public void setBabyIds(int[] babyIds) {
		this.babyIds = babyIds;
	}

	public Pregnancies getPregnancy() {
		return pregnancy;
	}


	public void setPregnancy(Pregnancies gestation) {
		this.pregnancy = gestation;
	}
	
	
}
