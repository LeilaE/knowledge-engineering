package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

public class Test {

	public static void main(String[] args) {
		JIPEngine engine = new JIPEngine();
		engine.consultFile("ruleBased/prolog.pl");
		
		String queryString = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter query: \n");
		
		while(queryString != null) {
			
			try {
				queryString = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JIPQuery query = engine.openSynchronousQuery(queryString);
			
			JIPTerm solution;
			while ( (solution = query.nextSolution()) != null) {
				//System.out.println("solution: " + solution);
				for (JIPVariable var: solution.getVariables()) {
					System.out.println(var.getName() + "=" + var.getValue());
				}
			}
			
		}

	}

}
