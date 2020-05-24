package base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

	private static Set<String> symptoms = new HashSet<String>(Arrays.asList(
			"fatigue",
			"weakness",
			"pale_skin"
			));
	
	private static Set<String> genetics = new HashSet<String>(Arrays.asList(
			"iron_deficiency_anemia",
			"pernicious_anemia",
			"diabetes_type_1",
			"diabetes_type_2",
			"gestational_diabetes"
			));
	
	

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
	
	
}
