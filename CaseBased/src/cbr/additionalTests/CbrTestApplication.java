package cbr.additionalTests;

import java.util.ArrayList;
import java.util.Collection;

import connector.additionalTests.CsvTestConnector;
import model.additionalTests.AdditionalTestsDescription;
import similarity.ListSimilarity;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

public class CbrTestApplication implements StandardCBRApplication {

	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */

	NNConfig simConfig;  /** KNN configuration */
	
	private static ArrayList<String> results = new ArrayList<String>(); /** Result list*/

	public void configure() throws ExecutionException {
		_connector =  new CsvTestConnector();

		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization

		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average


		simConfig.addMapping(new Attribute("symptomsList", AdditionalTestsDescription.class), new ListSimilarity());
		simConfig.addMapping(new Attribute("geneticsList", AdditionalTestsDescription.class), new ListSimilarity());
		//simConfig.addMapping(new Attribute("additionalTestsList", AdditionalTestsDescription.class), new ListSimilarity());


		//simConfig.addMapping(new Attribute("treatment", TreatmentDescription.class), new ListTableSimilarity());

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
		
		results = new ArrayList<String>();
		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 3);
		//System.out.println("Retrieved cases:");
		for (RetrievalResult nse : eval)
			results.add(nse.get_case().getDescription() + " -> " + nse.getEval());

	}
	public void postCycle() throws ExecutionException {

	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		//for (CBRCase c: cases)
		//	System.out.println(c.getDescription());
		return _caseBase;
	}

	public static ArrayList<String> doCbrApplication(ArrayList<String> symptomsList, ArrayList<String> geneticsList) {
		StandardCBRApplication recommender = new CbrTestApplication();
		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();

			//ArrayList<String> symptomsList = new ArrayList<>();
			//symptomsList.add("fatigue");
			//symptomsList.add("weakness");

			//ArrayList<String> geneticsList = new ArrayList<>();
			//geneticsList.add("iron_deficiency_anemia");
			//geneticsList.add("diabetes_type_1");

			AdditionalTestsDescription additionalTestsDescription = new AdditionalTestsDescription();
			additionalTestsDescription.setSymptomsList(symptomsList);
			additionalTestsDescription.setGeneticsList(geneticsList);

			query.setDescription( additionalTestsDescription );

			recommender.cycle(query);

			recommender.postCycle();
			
			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
