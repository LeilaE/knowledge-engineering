package connector.diagnosis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import model.additionalTests.AdditionalTestsDescription;
import model.diagnosis.DiagnosisDescription;
import similarity.ListSimilarity;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvDiagnosisConnector implements Connector {



	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/diagnosis.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0) || line.startsWith(";"))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();

				DiagnosisDescription diagnosisDescription = new DiagnosisDescription();
				diagnosisDescription.setInitialDiagosis(values[0]);
				diagnosisDescription.setGender(values[1]);
				diagnosisDescription.setAge(Integer.parseInt(values[2]));
				diagnosisDescription.setActive(values[3]);
				diagnosisDescription.setSmoker(values[4]);
				diagnosisDescription.setPregnant(values[5]);
				diagnosisDescription.setGeneticsList(Arrays.asList(values[6].split(",")));
				diagnosisDescription.setTestsList(Arrays.asList(values[7].split(",")));
				diagnosisDescription.setConfirmedDiagnosisList(Arrays.asList(values[8].split(",")));

				cbrCase.setDescription(diagnosisDescription);
				//CaseDescription caseDescription = new CaseDescription();
				
				// TODO
				
				//cbrCase.setDescription(caseDescription);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
	}
	
	@Override
	public void close() {
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {
	}

}