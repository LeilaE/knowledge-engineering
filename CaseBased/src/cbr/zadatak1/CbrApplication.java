package cbr.zadatak1;

import java.util.Arrays;
import java.util.Collection;

import connector.zadatak1.CsvConnector;
import model.zadatak1.MovieDescription;
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
		
		setSimilarityConfigration1();
	}
	
	public void setSimilarityConfigration1() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("age", MovieDescription.class), new Interval(5));
		simConfig.addMapping(new Attribute("score", MovieDescription.class), new Interval(1));
		simConfig.addMapping(new Attribute("year", MovieDescription.class), new Interval(10));
		TableSimilarity genreSimilarity = new TableSimilarity((Arrays.asList(new String[] {"Crime","Action","Thriller"})));
		genreSimilarity.setSimilarity("Crime", "Action", .7);
		genreSimilarity.setSimilarity("Crime", "Thriller", .7);
		genreSimilarity.setSimilarity("Action", "Thriller", .9);
		simConfig.addMapping(new Attribute("genre", MovieDescription.class), genreSimilarity);
	}
	public void setSimilarityConfigration2() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("gender", MovieDescription.class), new Equal());
		simConfig.addMapping(new Attribute("score", MovieDescription.class), new Interval(1));
		TableSimilarity genreSimilarity = new TableSimilarity((Arrays.asList(new String[] {"Crime","Action","Thriller"})));
		genreSimilarity.setSimilarity("Crime", "Action", .7);
		genreSimilarity.setSimilarity("Crime", "Thriller", .7);
		genreSimilarity.setSimilarity("Action", "Thriller", .9);
		simConfig.addMapping(new Attribute("genre", MovieDescription.class), genreSimilarity);
	}
	public void setSimilarityConfigration3() {
		setSimilarityConfigration2();
	}
	public void setSimilarityConfigration4() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("gender", MovieDescription.class), new Equal());
		simConfig.addMapping(new Attribute("title", MovieDescription.class), new Equal());
	}
	public void setSimilarityConfigration5() {
		setSimilarityConfigration4();
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
//		for (CBRCase c: cases)
//			System.out.println(c.getDescription());
		return _caseBase;
	}

	public static void main(String[] args) {
		CbrApplication recommender = new CbrApplication();
		try {
			System.out.println("-----");
			recommender.configure();
			recommender.preCycle();
			CBRQuery query = new CBRQuery();
			MovieDescription movieDescription = new MovieDescription();
			movieDescription.setGenre("Comedy");
			movieDescription.setYear(1980);
			movieDescription.setAge(24);
			movieDescription.setScore(5);
			query.setDescription( movieDescription );
			recommender.cycle(query);
			recommender.postCycle();
			
			System.out.println("-----");
			recommender.setSimilarityConfigration2();
			recommender.preCycle();
			movieDescription = new MovieDescription();
			movieDescription.setGenre("Action");
			movieDescription.setGender("F");
			movieDescription.setScore(1);
			query.setDescription( movieDescription );
			recommender.cycle(query);
			recommender.postCycle();
			
			System.out.println("-----");
			recommender.setSimilarityConfigration3();
			recommender.preCycle();
			movieDescription = new MovieDescription();
			movieDescription.setGenre("Drama");
			movieDescription.setGender("M");
			movieDescription.setScore(1);
			query.setDescription( movieDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration4();
			recommender.preCycle();
			movieDescription = new MovieDescription();
			movieDescription.setTitle("Gladiator");
			movieDescription.setGender("M");
			movieDescription.setScore(1);
			query.setDescription( movieDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration5();
			recommender.preCycle();
			movieDescription = new MovieDescription();
			movieDescription.setTitle("Titanic");
			movieDescription.setGender("F");
			query.setDescription( movieDescription );
			recommender.cycle(query);
			recommender.postCycle();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}