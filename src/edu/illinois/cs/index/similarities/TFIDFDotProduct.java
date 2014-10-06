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
    	//stats.getAvgFieldLength(): average document length
    	//stats.getNumberOfDocuments(): total number of documents in the index
    	//stats.getDocFreq(): the number of documents the current term appears in
    	float r = (float) ((termFreq)*(Math.log(stats.getNumberOfDocuments()+1)/stats.getDocFreq()));
        return r;
    }

    @Override
    public String toString() {
        return "TF-IDF Dot Product";
    }
}
