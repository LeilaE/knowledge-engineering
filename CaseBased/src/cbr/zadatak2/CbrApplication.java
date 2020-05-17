package cbr.zadatak2;

import java.util.Arrays;
import java.util.Collection;

import connector.zadatak2.CsvConnector;
import model.zadatak2.CreditDescription;
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
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
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
		simConfig.addMapping(new Attribute("age", CreditDescription.class), new Interval(5));
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("car", "vacation/others", .9);
		purposeSimilarity.setSimilarity("education", "business", .7);
		purposeSimilarity.setSimilarity("furniture/equipment", "domestic appliances", .7);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
	}
	public void setSimilarityConfigration2() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("housing", CreditDescription.class), new Equal());
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("car", "vacation/others", .9);
		purposeSimilarity.setSimilarity("education", "business", .7);
		purposeSimilarity.setSimilarity("furniture/equipment", "domestic appliances", .7);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
	}
	public void setSimilarityConfigration3() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("duration", CreditDescription.class), new Interval(3));
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("education", "business", 1);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
	}
	public void setSimilarityConfigration4() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("gender", CreditDescription.class), new Equal());
		simConfig.addMapping(new Attribute("duration", CreditDescription.class), new Interval(3));
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("car", "vacation/others", .9);
		purposeSimilarity.setSimilarity("education", "business", .7);
		purposeSimilarity.setSimilarity("furniture/equipment", "domestic appliances", .7);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
	}
	public void setSimilarityConfigration5() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("gender", CreditDescription.class), new Equal());
		simConfig.addMapping(new Attribute("ammount", CreditDescription.class), new Threshold(100));
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("car", "vacation/others", .9);
		purposeSimilarity.setSimilarity("education", "business", .7);
		purposeSimilarity.setSimilarity("furniture/equipment", "domestic appliances", .7);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
	}
	public void setSimilarityConfigration6() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		simConfig.addMapping(new Attribute("housing", CreditDescription.class), new Equal());
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("car", "vacation/others", .9);
		purposeSimilarity.setSimilarity("education", "business", .7);
		purposeSimilarity.setSimilarity("furniture/equipment", "domestic appliances", .7);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
	}
	public void setSimilarityConfigration7() {
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		TableSimilarity purposeSimilarity = new TableSimilarity((Arrays.asList(new String[] {"car","vacation/others","education","business","furniture/equipment","domestic appliances"})));
		purposeSimilarity.setSimilarity("car", "vacation/others", .9);
		purposeSimilarity.setSimilarity("education", "business", .7);
		purposeSimilarity.setSimilarity("furniture/equipment", "domestic appliances", .7);
		simConfig.addMapping(new Attribute("purpose", CreditDescription.class), purposeSimilarity);
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
			CreditDescription creditDescription = new CreditDescription();
			creditDescription.setAge(30);
			creditDescription.setPurpose("education");
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();
			
			System.out.println("-----");
			recommender.setSimilarityConfigration2();
			recommender.preCycle();
			creditDescription = new CreditDescription();
			creditDescription.setHousing("own");
			creditDescription.setPurpose("car");
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration3();
			recommender.preCycle();
			creditDescription = new CreditDescription();
			creditDescription.setPurpose("business");
			creditDescription.setDuration(36);
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration4();
			recommender.preCycle();
			creditDescription = new CreditDescription();
			creditDescription.setGender("male");
			creditDescription.setPurpose("radio/TV");
			creditDescription.setDuration(12);
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration5();
			recommender.preCycle();
			creditDescription = new CreditDescription();
			creditDescription.setGender("female");
			creditDescription.setPurpose("domestic appliances");
			creditDescription.setAmmount(1200);
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration6();
			recommender.preCycle();
			creditDescription = new CreditDescription();
			creditDescription.setHousing("rent");
			creditDescription.setPurpose("vacation/others");
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();

			System.out.println("-----");
			recommender.setSimilarityConfigration7();
			recommender.preCycle();
			creditDescription = new CreditDescription();
			creditDescription.setPurpose("furniture/equipment");
			query.setDescription( creditDescription );
			recommender.cycle(query);
			recommender.postCycle();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}