package edu.illinois.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.LMSimilarity;

public class DirichletPrior extends LMSimilarity {

    private LMSimilarity.DefaultCollectionModel model; // this would be your reference model
    private float queryLength = 0; // will be set at query time automatically

    public DirichletPrior() {
        model = new LMSimilarity.DefaultCollectionModel();
    }
    
    @Override
    protected float score(BasicStats stats, float termFreq, float docLength) {    	
    	double u = 2000;
    	double alpha = u/(u+docLength);
    	double pwc = model.computeProbability(stats);
    	double pwd = (termFreq+ u*pwc)/(docLength+u);
    	
    	double r = Math.log(pwd/(alpha*pwc)) + Math.log(alpha);
        return (float) r;
    }

    @Override
    public String getName() {
        return "Dirichlet Prior";
    }

    @Override
    public String toString() {
        return getName();
    }

    public void setQueryLength(float length) {
        queryLength = length;
    }
}
