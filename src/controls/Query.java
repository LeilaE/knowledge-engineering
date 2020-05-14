package controls;

import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import logic.PrologLogic;

import java.util.ArrayList;

public class Query {

    public static ArrayList<String> genericArrayQuery(String queryString) {
        JIPQuery query = PrologLogic.getInstance().doQuery(queryString);
        JIPTerm solution;
        ArrayList<String> solutions= new ArrayList<String>();

        while ( (solution = query.nextSolution()) != null  ) {
            for (JIPVariable var: solution.getVariables()) {
                solutions.add(var.getValue().toString());
            }
        }

        return solutions;
    }
}
