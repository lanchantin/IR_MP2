package edu.illinois.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class OkapiBM25 extends SimilarityBase {
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
    	//stats.getAvgFieldLength(): average document length
    	//stats.getNumberOfDocuments(): total number of documents in the index
    	//stats.getDocFreq(): the number of documents the current term appears in
    	double k1 = 1.2;
    	double k2 = 500;
    	double b = 1.0;
    	
    	long N = stats.getNumberOfDocuments();
    	long df = stats.getDocFreq();
    	float c = termFreq;
    	float n = docLength;
    	float n_avg = stats.getAvgFieldLength();
    	
    	double t1 = Math.log( (N - df + 0.5)/(df + 0.5) );
    	double t2 = ( (k1 + 1)*(c) ) / ( ( k1*( 1-b+(b*(n/n_avg)) ) )+c );
    	double t3 = ((k2+1)*(c))/(k2+c);
    	
    	double r = t1*t2*t3;

        return (float) r;
    }

    @Override
    public String toString() {
        return "Okapi BM25";
    }

}
