package cbr.primer2;

import java.util.Arrays;
import java.util.Collection;

import connector.primer2.CsvConnector;
import model.primer2.TravelDescription;
import similarity.TableSimilarity;
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
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

public class CbrApplication implements StandardCBRApplication {
	
	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */

	NNConfig simConfig;  /** KNN configuration */
	
	public void configure() throws ExecutionException {
		_connector =  new CsvConnector();
		
		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
		
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		
		// simConfig.addMapping(new Attribute("id", TravelDescription.class), new Interval(1));
		simConfig.addMapping(new Attribute("type", TravelDescription.class), new Equal());
		// simConfig.addMapping(new Attribute("price", TravelDescription.class), new Interval(100));
		simConfig.addMapping(new Attribute("persons", TravelDescription.class), new Interval(2));
		simConfig.addMapping(new Attribute("region", TravelDescription.class), new Equal());
		
		TableSimilarity tableSim = new TableSimilarity((Arrays.asList(new String[] {"Plane","Car","Bus","Train"})));
		tableSim.setSimilarity("Plane", "Car", .5);
		tableSim.setSimilarity("Car", "Bus", .7);
		tableSim.setSimilarity("Bus", "Train", .9);
		simConfig.addMapping(new Attribute("transportation", TravelDescription.class), tableSim);
		
		simConfig.addMapping(new Attribute("duration", TravelDescription.class), new Interval(2));
		// simConfig.addMapping(new Attribute("season", TravelDescription.class), new Equal());
		// simConfig.addMapping(new Attribute("accommodation", TravelDescription.class), new Equal());
		// simConfig.addMapping(new Attribute("hotel", TravelDescription.class), new Equal());

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
		eval = SelectCases.selectTopKRR(eval, 5);
		System.out.println("Retrieved cases:");
		for (RetrievalResult nse : eval)
			System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
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

	public static void main(String[] args) {
		StandardCBRApplication recommender = new CbrApplication();
		try {
			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();
			
			TravelDescription travelDescription = new TravelDescription();
			travelDescription.setType("Skiing");
			travelDescription.setPersons(4);
			travelDescription.setRegion("France");
			travelDescription.setTransportation("Plane");
			travelDescription.setDuration(7);
			
			query.setDescription( travelDescription );
			recommender.cycle(query);

			recommender.postCycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}