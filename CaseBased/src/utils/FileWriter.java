package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWriter {
	
	public final static String TEST_CSV = "";
	public final static String DIAGNOSIS_CSV = "";
	public final static String TREATMENT_CSV = "";
	
	private static String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(i -> escapeSpecialCharacters(i))
	      .collect(Collectors.joining(","));
	}
	
	private static String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}

	public static boolean writeToTests(List<String[]> dataLines) throws IOException {
	    File csvOutputFile = new File(TEST_CSV);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(i -> convertToCSV(i))
	          .forEach(pw::println);
	    }
	    return csvOutputFile.exists();
	}
	
	public static boolean writeToDiagnosis(List<String[]> dataLines) throws IOException {
	    File csvOutputFile = new File(DIAGNOSIS_CSV);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(i -> convertToCSV(i))
	          .forEach(pw::println);
	    }
	    return csvOutputFile.exists();
	}
	
	public static boolean writeToTreatments(List<String[]> dataLines) throws IOException {
	    File csvOutputFile = new File(TREATMENT_CSV);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(i -> convertToCSV(i))
	          .forEach(pw::println);
	    }
	    return csvOutputFile.exists();
	}

}
