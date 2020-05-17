package connector.primer2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import model.primer2.TravelDescription;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvConnector implements Connector {
	
	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/primer2.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();

				TravelDescription travelDescription = new TravelDescription();
				travelDescription.setId(values[0]);
				travelDescription.setType(values[1]);
				travelDescription.setPrice(Float.parseFloat(values[2]));
				travelDescription.setPersons(Integer.parseInt(values[3]));
				travelDescription.setRegion(values[4]);
				travelDescription.setTransportation(values[5]);
				travelDescription.setDuration(Integer.parseInt(values[6]));
				travelDescription.setSeason(values[7]);
				travelDescription.setAccommodation(values[8]);
				travelDescription.setHotel(values[9]);
				
				cbrCase.setDescription(travelDescription);
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