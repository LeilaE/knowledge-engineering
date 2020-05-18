package similarity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class ListSimilarity implements LocalSimilarityFunction {

	@Override
	public double compute(Object list1, Object list2) throws NoApplicableSimilarityFunctionException {
		if (list1 == null || list2 == null)
			return 0;
		int startSize1 = ((List) list1).size();
		int startSize2 = ((List) list2).size();
		double counter = 0;

		for (String element1 : (List<String>) list1) {
			for (String element2 : (List<String>) list2) {
				if (element1.equals(element2)) {
					counter = counter + 1;
					break;
				}
			}
		}

		if (startSize1 > startSize2) {
			return counter / startSize1;
		}else{
			return counter / startSize2;
		}

	}

	@Override
	public boolean isApplicable(Object value1, Object value2) {
		if (value1 instanceof List && value2 instanceof List)
			return true;
		return false;
	}






}