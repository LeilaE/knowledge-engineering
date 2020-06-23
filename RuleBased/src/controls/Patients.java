package controls;

import java.util.ArrayList;
import java.util.List;

import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import logic.FilesUtils;
import logic.PrologLogic;
import models.Patient;

public class Patients {

	public static ArrayList<Patient> getPatients() {
		JIPQuery query = PrologLogic.getInstance().doQuery("person(X)");
		JIPTerm solution;
		
		ArrayList<Patient> patients = new ArrayList<Patient>();
		
		  while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				String name = var.getValue().toString();
				patients.add(getPatientData(name));
			}
		}
		  
		return patients;
	}
	
	public static void addNewPatient(String name, String age, String activity, boolean smoker, boolean pregnant, List<String> genetics) {
		String strSmoker = smoker ? "yes" : "no";
		String strPregnant = pregnant ? "yes" : "no";
		FilesUtils.writeSingleProlog("person("+name+").");
		FilesUtils.writeSingleProlog("age("+name+","+age+").");
		FilesUtils.writeSingleProlog("activity("+name+","+activity+").");
		FilesUtils.writeSingleProlog("smoker("+name+","+strSmoker+").");
		FilesUtils.writeSingleProlog("pregnant("+name+","+strPregnant+").");
		
		for (String genetic : genetics) {
			FilesUtils.writeSingleProlog("genetics("+name+","+genetic+").");
		}
		
		PrologLogic.getInstance().reConsult();
	}
	
	public static Patient getPatientData(String name) {
		String age = null;
		String activity = null;
		boolean smoker = false;
		boolean pregnant = false;
		ArrayList<String> genetics = new ArrayList<String>();
		
		JIPQuery query = PrologLogic.getInstance().doQuery("age(" + name + ",X)");
		JIPTerm solution;
		
		while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				age = var.getValue().toString();
			}
		}
		
		query = PrologLogic.getInstance().doQuery("activity(" + name + ",X)");
		
		while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				activity = var.getValue().toString();
			}
		}
		
		query = PrologLogic.getInstance().doQuery("smoker(" + name + ",X)");
		
		while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				smoker = (var.getValue().toString().equalsIgnoreCase("yes")) ? true : false;
				break;
			}
			break;
		}
		
		query = PrologLogic.getInstance().doQuery("pregnant(" + name + ",X)");
		
		while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				pregnant = (var.getValue().toString().equalsIgnoreCase("yes")) ? true : false;
				break;
			}
			break;
		}
		
		query = PrologLogic.getInstance().doQuery("genetics(" + name + ",X)");
		
		while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				genetics.add(var.getValue().toString());
			}
		}
		
		return new Patient(name, age, activity, smoker, pregnant, genetics);
	}
}
