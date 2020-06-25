package logic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesUtils {

	private static String[] files = { "ruleBased/people.pl", "ruleBased/symptoms.pl",
			"ruleBased/additional_examination.pl", "ruleBased/treatments.pl" };
	private static String prologOutput = "ruleBased/prolog.pl";
	private static String lastFile = "ruleBased/treatments.pl";

	public static String openProlog() {
		return prologOutput;
	}

	public static String initProlog() {

		Path fileToDeletePath = Paths.get(prologOutput);
		try {
			if (!Files.deleteIfExists(fileToDeletePath)) {
				File myObj = new File(prologOutput);
			    myObj.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OutputStream out = null;
		try {
			out = new FileOutputStream(prologOutput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buf = new byte[8];
		for (String file : files) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int b = 0;
			try {
				while ((b = in.read(buf)) >= 0)
					out.write(buf, 0, b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prologOutput;
	}

	public static void writeSingleProlog(String term) {
		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter(prologOutput, true);
			pw = new PrintWriter(file);

			pw.println(term);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != file)
					file.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		try {
			file = new FileWriter(lastFile, true);
			pw = new PrintWriter(file);

			pw.println(term);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != file)
					file.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// Extracted from "CITAMO SVE IZ FAJLA I AKO NADJEMO NA TEST KOJI HOCEMO DA
	// UPISEMO PRESKOCIMO GA, AKO NE NADJEMO VRACAMO FALSE"
	public static boolean replaceInFile(String termReplace, String termNew) {
		List<String> fileContent = null;
		boolean flag = false;
		
		try {
			fileContent = new ArrayList<>(Files.readAllLines(Paths.get(prologOutput), StandardCharsets.UTF_8));
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		for (int i = 0; i < fileContent.size(); i++) {
		    if (fileContent.get(i).contains(termReplace)) {
		        fileContent.set(i, termNew);
		        flag = true;
		        break;
		    }
		}

		try {
			Files.write(Paths.get(prologOutput), fileContent, StandardCharsets.UTF_8);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<String> fileContentNew = null;
		
		try {
			fileContentNew = new ArrayList<>(Files.readAllLines(Paths.get(lastFile), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < fileContentNew.size(); i++) {
		    if (fileContentNew.get(i).contains(termReplace)) {
		    	fileContentNew.set(i, termNew);
		    	flag = true;
		    	break;
		    }
		}

		try {
			Files.write(Paths.get(lastFile), fileContentNew, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
