package model.additionalTests;

import java.util.ArrayList;
import java.util.List;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class AdditionalTestsDescription implements CaseComponent {

	private List<String> symptomsList;
	private List<String> initialDiagnosisList;
	private List<String> additionalTestsList;

	@Override
	public String toString() {
		return "AdditionalTestsDescription{" +
				"symptomsList=" + symptomsList +
				", initialDiagnosisList=" + initialDiagnosisList +
				", additionalTestsList=" + additionalTestsList +
				'}';
	}

	public List<String> getSymptomsList() {
		return symptomsList;
	}

	public void setSymptomsList(List<String> symptomsList) {
		this.symptomsList = symptomsList;
	}

	public List<String> getInitialDiagnosisList() {
		return initialDiagnosisList;
	}

	public void setInitialDiagnosisList(List<String> initialDiagnosis) {
		this.initialDiagnosisList = initialDiagnosis;
	}

	public List<String> getAdditionalTestsList() {
		return additionalTestsList;
	}

	public void setAdditionalTestsList(List<String> additionalTests) {
		this.additionalTestsList = additionalTests;
	}

	@Override
	public Attribute getIdAttribute() {
		return null;
	}

}
