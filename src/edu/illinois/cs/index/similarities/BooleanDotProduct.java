package edu.illinois.cs.index.similarities;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class BooleanDotProduct extends SimilarityBase {
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
    	//getAvgFieldLength(): average document length
    	//getNumberOfDocuments(): total number of documents in the index
    	//getDocFreq(): the number of documents the current term appears in
    	//System.out.println(termFreq);
        return termFreq;
    }

    @Override
    public String toString() {
        return "Boolean Dot Product";
    }
}
