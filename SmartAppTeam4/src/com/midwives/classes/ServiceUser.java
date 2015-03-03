package com.midwives.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5518374187111281600L;
	private ClinicalFields clinicalFields;
	private int id;
	private String hospitalNumber;
	
	private ArrayList<Baby> babies;
	private ArrayList<Pregnancies> pregnancies;
	
	private int [] pregnencyIds, babyIds;
	
	private Pregnancies pregnancy;
	private String gestation;
	private PersonalFields personalFields;
	private String name;
	
	private static ServiceUser serviceUser;
	private static Links links;
	
	//used by AppointmentParser class
	public ServiceUser(String gestation, int id, String name){
		this.gestation=gestation;
		this.id=id;
		this.name=name;
	}
//	public ServiceUser(Pregnancies gestation, int id, PersonalFields name){
//		this.pregnancy=gestation;
//		this.id=id;
//		this.personalFields=name;
//	}
	
	public ServiceUser(ArrayList<Baby> baby, ArrayList<Pregnancies> pregnancies,int[] babyIds, ClinicalFields clinicalFields,String hospitalNumber,
						int id, PersonalFields personalFields, int[] pregnanciesIds){
		this.babies=baby;
		this.pregnancies=pregnancies;
		this.babyIds=babyIds;
		this.clinicalFields=clinicalFields;
		this.hospitalNumber=hospitalNumber;
		this.id=id;
		this.personalFields=personalFields;
		this.pregnencyIds=pregnanciesIds;
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
	public ServiceUser(ClinicalFields clinicalFields, int id, String hospitalName, PersonalFields person, ArrayList<Baby> baby, ArrayList<Pregnancies> pregnancies,
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

	public ArrayList<Baby> getBabies() {
		return babies;
	}

	public void setBabies(ArrayList<Baby> babies) {
		this.babies = babies;
	}

	public ArrayList<Pregnancies> getPregnancies() {
		return pregnancies;
	}

	public void setPregnancies(ArrayList<Pregnancies> pregnancies) {
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

	public String getGestation() {
		return gestation;
	}

	public void setGestation(String gestation) {
		this.gestation = gestation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pregnancies getPregnancy() {
		return pregnancy;
	}


	public void setPregnancy(Pregnancies gestation) {
		this.pregnancy = gestation;
	}
	
	
}
