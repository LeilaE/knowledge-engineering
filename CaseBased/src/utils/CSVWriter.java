package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CSVWriter {
	
	public final static String TEST_CSV = "data/additionalTests.csv";
	public final static String DIAGNOSIS_CSV = "data/diagnosis.csv";
	public final static String TREATMENT_CSV = "data/treatments.csv";
	

	public static void writeToTests(List<String> dataLines) throws IOException {
	    FileWriter csvOutputFile = new FileWriter(TEST_CSV, true);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	    	pw.println("\n");
	        dataLines.stream().map(i -> i+";").forEach(pw::print);
	    }
	    csvOutputFile.close();
	}
	
	public static void writeToDiagnosis(List<String> dataLines) throws IOException {
		FileWriter csvOutputFile = new FileWriter(TEST_CSV, true);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	    	pw.println("\n");
	        dataLines.stream().map(i -> i+";").forEach(pw::print);
	    }
	    csvOutputFile.close();
	}
	
	public static void writeToTreatments(List<String> dataLines) throws IOException {
		FileWriter csvOutputFile = new FileWriter(TEST_CSV, true);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	    	pw.println("\n");
	        dataLines.stream().map(i -> i+";").forEach(pw::print);
	    }
	    csvOutputFile.close();
	}

}
