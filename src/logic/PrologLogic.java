package logic;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;

public class PrologLogic {
	
	private static PrologLogic instance = null;
	
	private static JIPEngine engine = new JIPEngine();
	
	private PrologLogic() {
		engine.consultFile("ruleBased/prolog.pl");
	}
	
	public static PrologLogic getInstance() { 
        if (instance == null) 
        	instance = new PrologLogic(); 
  
        return instance; 
    }
	
	/* Query example: "prethodnik(3,X)" */
	public JIPQuery doQuery(String queryString) {
		return engine.openSynchronousQuery(queryString);
	}
	
	/*Term example: "sledbenik(X,Y) :- X is Y+1." */
	public boolean assertTerm(String termString) {
		engine.assertz(engine.getTermParser().parseTerm(termString));
		return true;
	}
}
