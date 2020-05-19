package model.diagnosis;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import java.util.List;

public class DiagnosisDescription implements CaseComponent {

	private String initialDiagosis;
	private String gender;
	private int age;
	private String active;
	private String smoker;
	private String pregnant;
	private List<String> geneticsList;
	private List<String> testsList;
	private List<String> confirmedDiagnosisList;


	@Override
	public String toString() {
		return "DiagnosisDescription{" +
				"initialDiagosis='" + initialDiagosis + '\'' +
				", gender='" + gender + '\'' +
				", age=" + age +
				", active='" + active + '\'' +
				", smoker='" + smoker + '\'' +
				", pregnant='" + pregnant + '\'' +
				", geneticsList=" + geneticsList +
				", testsList=" + testsList +
				", confirmedDiagnosisList=" + confirmedDiagnosisList +
				'}';
	}

	public String getInitialDiagosis() {
		return initialDiagosis;
	}

	public void setInitialDiagosis(String initialDiagosis) {
		this.initialDiagosis = initialDiagosis;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public String getPregnant() {
		return pregnant;
	}

	public void setPregnant(String pregnant) {
		this.pregnant = pregnant;
	}

	public List<String> getGeneticsList() {
		return geneticsList;
	}

	public void setGeneticsList(List<String> geneticsList) {
		this.geneticsList = geneticsList;
	}

	public List<String> getTestsList() {
		return testsList;
	}

	public void setTestsList(List<String> testsList) {
		this.testsList = testsList;
	}

	public List<String> getConfirmedDiagnosisList() {
		return confirmedDiagnosisList;
	}

	public void setConfirmedDiagnosisList(List<String> confirmedDiagnosisList) {
		this.confirmedDiagnosisList = confirmedDiagnosisList;
	}

	@Override
	public Attribute getIdAttribute() {
		return null;
	}
}
