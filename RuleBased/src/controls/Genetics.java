package controls;

import java.util.ArrayList;

import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

import logic.PrologLogic;

public class Genetics {
	
	public static ArrayList<String> getGenetics() {
        JIPQuery query = PrologLogic.getInstance().doQuery("genetic(X)");
        JIPTerm solution;

        ArrayList<String> genetics = new ArrayList<String>();

        while ( (solution = query.nextSolution()) != null  ) {
            for (JIPVariable var: solution.getVariables()) {
                genetics.add(var.getValue().toString());
            }
        }

        return genetics;
    }
	
	public static ArrayList<String> getPatientsGenetics(String name) {
        JIPQuery query = PrologLogic.getInstance().doQuery("genetics(" + name + ",X)");
        JIPTerm solution;
        
        ArrayList<String> genetics = new ArrayList<String>();
		
		while ( (solution = query.nextSolution()) != null  ) {
			for (JIPVariable var: solution.getVariables()) {
				genetics.add(var.getValue().toString());
			}
		}
		
        return genetics;
    }
}
