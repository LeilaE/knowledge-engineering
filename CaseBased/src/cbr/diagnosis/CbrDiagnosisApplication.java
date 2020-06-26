package cbr.diagnosis;

import java.util.ArrayList;
import java.util.Collection;

import connector.diagnosis.CsvDiagnosisConnector;
import model.diagnosis.DiagnosisDescription;
import model.treatments.TreatmentsDescription;
import similarity.ListSimilarity;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

public class CbrDiagnosisApplication implements StandardCBRApplication {

	private static String initialDiagnosis;
	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */

	NNConfig simConfig;  /** KNN configuration */
	
	private static ArrayList<String> results = new ArrayList<String>(); 
	
	public void configure() throws ExecutionException {
		_connector =  new CsvDiagnosisConnector();
		
		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
		
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average


		//simConfig.addMapping(new Attribute("initaialDiagnosis", DiagnosisDescription.class), new Equal());
		simConfig.addMapping(new Attribute("gender", DiagnosisDescription.class), new Equal());
		simConfig.addMapping(new Attribute("age", DiagnosisDescription.class), new Interval(100));
		simConfig.addMapping(new Attribute("active", DiagnosisDescription.class), new Equal());
		simConfig.addMapping(new Attribute("smoker", DiagnosisDescription.class), new Equal());
		simConfig.addMapping(new Attribute("pregnant", DiagnosisDescription.class), new Equal());
		simConfig.addMapping(new Attribute("geneticsList", DiagnosisDescription.class), new ListSimilarity());
		simConfig.addMapping(new Attribute("testsList", DiagnosisDescription.class), new ListSimilarity());
		//simConfig.addMapping(new Attribute("confirmedDiagnosisList", DiagnosisDescription.class), new ListSimilarity());

		// simConfig.addMapping(new Attribute("attribute", CaseDescription.class), new Interval(5));
		// TODO

		// Equal - returns 1 if both individuals are equal, otherwise returns 0
		// Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
		// Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
		// EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
		// MaxString - returns a similarity value depending of the biggest substring that belong to both strings
		// EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
		// EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
		// Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
		// TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity) 
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		//eval = SelectCases.selectTopKRR(eval, 10);
		
		results = new ArrayList<String>();

		for(RetrievalResult rr: eval){
			String[] values = rr.get_case().getDescription().toString().split(",");
			String[] values0 = values[0].toString().split("=");
			String[] values1 = values0[1].toString().split("'");
			String diagnosisVal = values1[1];
			if(initialDiagnosis.equals(diagnosisVal)){
				results.add(rr.get_case().getDescription() + " -> " + rr.getEval());
			}
		}
		

	}

	public void postCycle() throws ExecutionException {
		
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for (CBRCase c: cases)
			System.out.println(c.getDescription());
		return _caseBase;
	}

	public static ArrayList<String> doCbrDiagnosisApplication(ArrayList<String> initialDiagnosisList, ArrayList<String> geneticsList, String smoker, String pregnant, String active, String gender, int age,  ArrayList<String> tests) {
		
		StandardCBRApplication recommender = new CbrDiagnosisApplication();
		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();


			DiagnosisDescription diagnosisDescription = new DiagnosisDescription();
			diagnosisDescription.setInitialDiagosis(initialDiagnosisList.get(0));
			diagnosisDescription.setGender(gender);
			diagnosisDescription.setAge(age);
			diagnosisDescription.setActive(active);
			diagnosisDescription.setSmoker(smoker);
			diagnosisDescription.setPregnant(pregnant);


			diagnosisDescription.setGeneticsList(geneticsList);
			diagnosisDescription.setTestsList(tests);

			initialDiagnosis = diagnosisDescription.getInitialDiagosis();

			query.setDescription(diagnosisDescription);

			recommender.cycle(query);

			recommender.postCycle();
			
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}