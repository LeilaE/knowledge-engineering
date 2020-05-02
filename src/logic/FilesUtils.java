package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesUtils {
	
	private static String[] files = {"ruleBased/people.pl", "ruleBased/symptoms.pl", "ruleBased/additional_examination.pl", "ruleBased/treatments.pl"};
	private static String prologOutput = "ruleBased/prolog.pl";

	public static String openProlog() {
		
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
				while ( (b = in.read(buf)) >= 0)
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
}
