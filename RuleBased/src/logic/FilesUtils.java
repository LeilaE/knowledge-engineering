package logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
			Files.deleteIfExists(fileToDeletePath);
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

	// Extracted from "ISPISIVANJE NOVIH TESTOVA"
	public static void writeProlog(ArrayList<String> terms) {
		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter(prologOutput, true);
			pw = new PrintWriter(file);

			for (String t : terms) {
				pw.println(t);
			}
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

			for (String t : terms) {
				pw.println(t);
			}
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
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;

		boolean retVal = false;

		try {
			fr = new FileReader(prologOutput);
			br = new BufferedReader(fr);

			ArrayList<String> lines = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (!line.contains(termReplace)) {
					lines.add(line);
				} else {
					retVal = true;
				}
			}

			// pisemo ceo fajl ponovo, , ako test nije postojao napisacemo ga gore, ako je
			// postojao nismo ga dodali u lines nego cemo napisati novu vrednost
			writeAllValuesWithReplaced(lines, termNew, retVal);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return retVal;
	}

	public static void writeAllValuesWithReplaced(ArrayList<String> lines, String termNew, boolean retVal) {
		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter(prologOutput);
			pw = new PrintWriter(file);

			for (String l : lines) {
				pw.write(l + "\n");
			}

			// nova vrednost za postojeci test
			if (retVal) {
				pw.write(termNew);
			}

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
			file = new FileWriter(lastFile);
			pw = new PrintWriter(file);

			for (String l : lines) {
				pw.write(l + "\n");
			}

			// nova vrednost za postojeci test
			if (retVal) {
				pw.write(termNew);
			}

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
}
