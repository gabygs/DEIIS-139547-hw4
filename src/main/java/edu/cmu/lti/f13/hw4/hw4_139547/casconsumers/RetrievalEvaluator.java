package edu.cmu.lti.f13.hw4.hw4_139547.casconsumers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Document;
import edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Token;
import edu.cmu.lti.f13.hw4.hw4_139547.utils.Utils;


public class RetrievalEvaluator extends CasConsumer_ImplBase {

	/** query id number **/
	public ArrayList<Integer> qIdList;

	/** query and text relevant values **/
	public ArrayList<Integer> relList;

	/** global words**/
	public ArrayList<String> globalDictionary;
	public int numQuerries;
	
	/** relevant ranks, use them for MRR **/
	public ArrayList<Double> ranks;



	public void initialize() throws ResourceInitializationException {

		qIdList = new ArrayList<Integer>();
		relList = new ArrayList<Integer>();
		globalDictionary = new ArrayList<String>();
		ranks = new ArrayList<Double>();
		numQuerries=0;

	}

	/**
	 * TODO :: 1. construct the global word dictionary 2. keep the word
	 * frequency for each sentence
	 */
	@Override
	public void processCas(CAS aCas) throws ResourceProcessException {
		ArrayList<Integer> dummyIds = new ArrayList<Integer>();
		
		
		JCas jcas;
		try {
			jcas =aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		FSIterator it = jcas.getAnnotationIndex(Document.type).iterator();

		if (it.hasNext()) {
			Document doc = (Document) it.next();

			//Make sure that your previous annotators have populated this in CAS
			FSList fsTokenList = doc.getTokenList();

			System.out.println("Doc: " + doc.getText()); 
			for (Token t :Utils.fromFSListToCollection(fsTokenList, Token.class))
				globalDictionary.add(t.getText());
			//System.out.println("TokenList contains: " + t.getText() + " -- Frequency: " + t.getFrequency());


			ArrayList<Token>tokenList=Utils.fromFSListToCollection(fsTokenList, Token.class);

			//System.out.println("getQueryID(): "+doc.getQueryID() + " -- getText: " + doc.getText() + " -- getRelevanceValue: " + doc.getRelevanceValue());

			qIdList.add(doc.getQueryID());
			relList.add(doc.getRelevanceValue());
			if(!dummyIds.contains(doc.getQueryID())){
				dummyIds.add(doc.getQueryID());
				System.out.println("dummyID: "+doc.getQueryID());
				numQuerries=numQuerries+1;
			}
			//Do something useful here
		}//if

		System.out.println("numQuerries: "+numQuerries);
		
	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	@Override
	public void collectionProcessComplete(ProcessTrace arg0)
			throws ResourceProcessException, IOException {

		//System.out.println("\n------------------------------------------------------------------------\n");
		System.out.println("\n------------------Started RetrievalEvaluator ------------------\n");

		super.collectionProcessComplete(arg0);

		// TODO :: compute the cosine similarity measure



		// TODO :: compute the rank of retrieved sentences



		// TODO :: compute the metric:: mean reciprocal rank
		//double metric_mrr = compute_mrr();
		//System.out.println(" (MRR) Mean Reciprocal Rank ::" + metric_mrr);
		System.out.println("\n------------------------------------------------------------------------\n");

	}

	/**
	 * 
	 * @return cosine_similarity
	 */

	@SuppressWarnings("rawtypes")
	// TODO :: compute cosine similarity between two sentences
	private double computeCosineSimilarity(Map<String, Integer> queryVector, Map<String, Integer> docVector) {
		double cosine_similarity=0.0, queryMagn=0.0, docMagn=0.0, dotProduct=0.0;
		Iterator queryIt = queryVector.entrySet().iterator();

		//calculate dotProduct
		while(queryIt.hasNext()){
			Map.Entry querrySet = (Map.Entry)queryIt.next();
			String qKey = (String)querrySet.getKey();
			Integer qVal = (Integer)querrySet.getValue();

			if(globalDictionary.contains(qKey) && docVector.containsKey(qKey)){
				dotProduct = dotProduct + (qVal.doubleValue() * docVector.get(qKey).doubleValue());
			}
		}
		//calculate magnitude
		queryMagn=vectorMagnitude(queryVector);
		docMagn=vectorMagnitude(docVector);

		//calculate cosine similarity
		cosine_similarity = dotProduct / (queryMagn * docMagn);

		return cosine_similarity;
	}


	@SuppressWarnings("rawtypes")
	private double vectorMagnitude(Map<String, Integer> vector){
		double vectMagnitude=0.0;
		Iterator vectorIter = vector.entrySet().iterator();
		
		while(vectorIter.hasNext()) {
			Map.Entry vectorSet = (Map.Entry)vectorIter.next();
			double freq = ((Integer)vectorSet.getValue()).doubleValue();
			
			vectMagnitude = vectMagnitude +(freq * freq);
		}
		vectMagnitude = Math.sqrt(vectMagnitude);

		return vectMagnitude;
	}

	/**
	 * 
	 * @return mrr
	 */
	// TODO :: compute Mean Reciprocal Rank (MRR) of the text collection
	private double compute_mrr() {
		double metric_mrr=0.0, sum_ranks=0.0;
		
		for(int i=0;i<numQuerries;i++)
			sum_ranks = sum_ranks + (1.0/ranks.get(i));
		
		metric_mrr=(1.0/numQuerries)* sum_ranks;
			
		return metric_mrr;
	}

}
