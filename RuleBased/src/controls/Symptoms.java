package controls;

import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import logic.PrologLogic;
import models.Patient;

import java.util.ArrayList;

public class Symptoms {

    public static ArrayList<String> getSymptoms() {
        JIPQuery query = PrologLogic.getInstance().doQuery("symptom(X)");
        JIPTerm solution;

        ArrayList<String> symptoms = new ArrayList<String>();

        while ( (solution = query.nextSolution()) != null  ) {
            for (JIPVariable var: solution.getVariables()) {
                symptoms.add(var.getValue().toString());
            }
        }

        return symptoms;
    }
}
