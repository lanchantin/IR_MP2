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
    	double k1 = 1.2;
    	double k2 = 0.1;
    	double b = 0.75;
    	
    	long N = stats.getNumberOfDocuments(); //total number of docs
    	long df = stats.getDocFreq(); //total docs this term occurs in
    	float tf = termFreq; //document term frequency
    	float qtf = 1; //query term frequency
    	float n = docLength; //current doc length
    	float n_avg = stats.getAvgFieldLength(); //average doc length
    	double K = k1*(1-b+(b*(n/n_avg))); 
    	
    	double IDF = Math.log((N - df + 0.5)/(df + 0.5));
    	double docTF = ((k1+1)*(tf))/(K+tf);
    	double queryTF = ((k2+1)*(qtf))/(k2+qtf);

        return (float) (IDF*docTF*queryTF);
    }

    @Override
    public String toString() {
        return "Okapi BM25";
    }

}
