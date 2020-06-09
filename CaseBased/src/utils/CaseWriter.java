package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaseWriter {

	public static boolean testsToCsv(List<String> symptomsList, List<String> geneticsList,
			List<String> initialDiagnosisList, List<String> additionalTestsList) {

		String symptoms = String.join(",", symptomsList);

		String genetics = String.join(",", geneticsList);

		String initialDiagnosis = String.join(",", initialDiagnosisList);

		String additionalTests = String.join(",", additionalTestsList);

		ArrayList<String> data = new ArrayList<>(Arrays.asList(symptoms, genetics, initialDiagnosis, additionalTests));

		try {
			CSVWriter.writeToTests(data);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean diagnosisToCsv(ArrayList<String> initialDiagnosisList,  ArrayList<String> geneticsList, String smoker, String pregnant, String active, String gender, int age,  
			 ArrayList<String> testsList, ArrayList<String> confirmedDiagnosisList) {

		String strAge = Integer.toString(age);
		
		String initialDiagnosis = String.join(",", initialDiagnosisList);

		String genetics = String.join(",", geneticsList);

		String tests = String.join(",", testsList);

		String confirmedDiagnosis = String.join(",", confirmedDiagnosisList);

		ArrayList<String> data = new ArrayList<>(Arrays.asList(initialDiagnosis, gender, strAge, active, smoker,
				pregnant, genetics, tests, confirmedDiagnosis));

		try {
			CSVWriter.writeToDiagnosis(data);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean treatmentsToCsv(String diagnosis, String gender, int age, List<String> treatmentsList) {

		String strAge = Integer.toString(age);

		String treatments = String.join(",", treatmentsList);

		ArrayList<String> data = new ArrayList<>(Arrays.asList(diagnosis, gender, strAge, treatments));

		try {
			CSVWriter.writeToTreatments(data);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
