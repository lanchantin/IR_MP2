package edu.illinois.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class TFIDFDotProduct extends SimilarityBase {
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
    	float tf = termFreq; //document term frequency
    	long N = stats.getNumberOfDocuments(); //total number of docs
    	long df = stats.getDocFreq(); //number of docs this term occurs in
    	
    	return (float) (tf*(Math.log((N+1)/df))); //ranking function
    }

    @Override
    public String toString() {
        return "TF-IDF Dot Product";
    }
}
