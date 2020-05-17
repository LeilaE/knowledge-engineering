package model;

import java.util.List;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class TreatmentDescription implements CaseComponent {
	private String diagnosis;
	private String gender;
	private int age;
	private List<String> treatment;

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "TreatmentDescription [diagnosis=" + diagnosis + ", gender=" + gender + ", age=" + age + ", treatment="
				+ treatment + "]";
	}

	public List<String> getTreatment() {
		return treatment;
	}

	public void setTreatment(List<String> treatment) {
		this.treatment = treatment;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}



	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id",this.getClass());
	}
}
