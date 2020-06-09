package base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

	private static Set<String> symptoms = new HashSet<>(Arrays.asList(
			"fatigue",
			"weakness",
			"pale_skin"
	));
	
	private static Set<String> genetics = new HashSet<>(Arrays.asList(
			"iron_deficiency_anemia",
			"pernicious_anemia",
			"diabetes_type_1",
			"diabetes_type_2",
			"gestational_diabetes"
	));
	
	private static Set<String> diagnosis = new HashSet<>(Arrays.asList(
			"iron_deficiency_anemia",
			"pernicious_anemia",
			"diabetes_type_1",
			"diabetes_type_2",
			"gestational_diabetes"
	));
	
	private static Set<String> initialDiagnosis = new HashSet<String>(Arrays.asList(
			"diabetes",
			"anemia"
			));
	
	private static Set<String> tests = new HashSet<String>(Arrays.asList(
			"hemoglobin_check",
			"b12_check",
			"iron_check",
			"folic_acid_check",
			"blood_sugar_level",
			"glycated_hemoglobin",
			"glucose_tolerance"
			));

	public static Set<String> getDiagnosis() {
		return diagnosis;
	}

	public static void setDiagnosis(Set<String> diagnosis) {
		Dictionary.diagnosis = diagnosis;
	}

	public static void addSymptom(String symptom) {
		Dictionary.symptoms.add(symptom);
	}

	public static void addGenetics(String genetic) {
		Dictionary.genetics.add(genetic);
	}

	
	public static Set<String> getSymptoms() {
		return symptoms;
	}

	public static void setSymptoms(Set<String> symptoms) {
		Dictionary.symptoms = symptoms;
	}

	public static Set<String> getGenetics() {
		return genetics;
	}

	public static void setGenetics(Set<String> genetics) {
		Dictionary.genetics = genetics;
	}

	public static Set<String> getInitialDiagnosis() {
		return initialDiagnosis;
	}

	public static void setInitialDiagnosis(Set<String> initialDiagnosis) {
		Dictionary.initialDiagnosis = initialDiagnosis;
	}

	public static Set<String> getTests() {
		return tests;
	}

	public static void setTests(Set<String> tests) {
		Dictionary.tests = tests;
	}
	
	
	
}
