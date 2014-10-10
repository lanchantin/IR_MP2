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
    	double s = 0.1;
    	
    	float tf = termFreq; //document term frequency
    	long N = stats.getNumberOfDocuments(); //total number of docs
    	long df = stats.getDocFreq(); //number of docs this term occurs in
    	float n = docLength; //current doc length
    	float n_avg = stats.getAvgFieldLength(); //average doc length
    	
    	double normDocTf = (1+Math.log(1+Math.log(tf)))/(1-s+(s*n/n_avg));
    	float queryTf = 1; //query term frequency
    	double IDF = Math.log((N+1)/df);
    	
        return (float) (normDocTf*queryTf*IDF); //ranking function
    }

    @Override
    public String toString() {
        return "Pivoted Length Normalization";
    }

}
