package logic;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;
import com.ugos.jiprolog.engine.JIPTerm;

public class PrologLogic {
	
	private static PrologLogic instance = null;
	
	private JIPEngine engine = new JIPEngine();
	
	private PrologLogic() {
		engine.consultFile(FilesUtils.initProlog());
	}
	
	public static PrologLogic getInstance() { 
        if (instance == null) 
        	instance = new PrologLogic();
        return instance; 
    }

    public void reConsult() {
		instance.engine.consultFile(FilesUtils.openProlog());
	}
	
	/* Query example: "prethodnik(3,X)" */
	public JIPQuery doQuery(String queryString) {
		return engine.openSynchronousQuery(queryString);
	}
	
	/*Term example: "sledbenik(X,Y) :- X is Y+1."
	* Not working */
	public void assertTerm(String termString) {
		JIPTerm term = null;
		try {
			term = engine.getTermParser().parseTerm(termString);
		}
		catch(JIPSyntaxErrorException ex)
		{
			System.out.println("Syntax error in prolog term");
		}
		engine.assertz(term);
	}
}
