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
    	
    	long N = stats.getNumberOfDocuments();
    	long df = stats.getDocFreq();
    	float cwd = termFreq;
    	float cwq = 1;
    	float n = docLength;
    	float n_avg = stats.getAvgFieldLength();
    	
    	double t1 = Math.log((N - df + 0.5)/(df + 0.5));
    	double t2 = ((k1+1)*(cwd))/((k1*(1-b+(b*(n/n_avg))))+cwd);
    	double t3 = ((k2+1)*(cwq))/(k2+cwq);

        return (float) (t1*t2*t3);
    }

    @Override
    public String toString() {
        return "Okapi BM25";
    }

}
