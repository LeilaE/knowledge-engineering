package similarity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class ListSimilarity implements LocalSimilarityFunction {
	
	@Override
	public double compute(Object value1, Object value2) throws NoApplicableSimilarityFunctionException {
		int startSize = ((List) value1).size();
		((List) value1).retainAll( (List) value2 );
		return ((List) value1).size() / startSize;
	}

	@Override
	public boolean isApplicable(Object value1, Object value2) {
		if (value1 instanceof List && value2 instanceof List)
			return true;
		return false;
	}

	


	
	
}
