package connector.additionalTests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import model.additionalTests.AdditionalTestsDescription;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;
import model.additionalTests.AdditionalTestsDescription;

public class CsvTestConnector implements Connector {

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/additionalTests.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();

				AdditionalTestsDescription additionalTestsDescription = new AdditionalTestsDescription();
				additionalTestsDescription.setSymptomsList(Arrays.asList(values[0].split(",")));
				additionalTestsDescription.setGeneticsList(Arrays.asList(values[1].split(",")));
				additionalTestsDescription.setInitialDiagnosisList(Arrays.asList(values[2].split(",")));
				additionalTestsDescription.setAdditionalTestsList(Arrays.asList(values[3].split(",")));

				cbrCase.setDescription(additionalTestsDescription);
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