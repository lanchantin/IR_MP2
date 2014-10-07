package edu.illinois.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class PivotedLength extends SimilarityBase {
    /**
     * Returns a score for a single term in the document.
     *
     * @param stats
     *            Provides access to corpus-level statistics
     * @param termFreq
     * @param docLength
     */
    @Override
    protected float score(BasicStats stats, float termFreq, float docLength) {
    	double s = 0.5;
    	
    	long N = stats.getNumberOfDocuments();
    	long df = stats.getDocFreq();
    	float c = termFreq;
    	float n = docLength;
    	float n_avg = stats.getAvgFieldLength();
    	
    	double t1 = (1+Math.log(1+Math.log(c)))/(1-s+(s*n/n_avg));
    	double t3 = Math.log((N+1)/df);
    	
    	double r = t1*c*t3;
    	
    	
        return (float) r;
    }

    @Override
    public String toString() {
        return "Pivoted Length Normalization";
    }

}
