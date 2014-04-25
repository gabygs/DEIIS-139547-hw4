package edu.cmu.lti.f13.hw4.hw4_139547.casconsumers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	public Map<String, Integer> globalDictionary;
	public ArrayList<String> docText;
	public ArrayList<String> corText;
	public ArrayList<String> querries;
	
	public int numQuerries;

	/** relevant ranks, use them for MRR **/
	public ArrayList<Integer> ranks;
	
	/**Documents**/
	public ArrayList<Map<String,Integer>> tdfVectors;



	public void initialize() throws ResourceInitializationException {
		qIdList = new ArrayList<Integer>();
		relList = new ArrayList<Integer>();
		docText = new ArrayList<String>();
		corText = new ArrayList<String>();
		querries = new ArrayList<String>();
		ranks = new ArrayList<Integer>();
		globalDictionary = new HashMap<String,Integer>();
		numQuerries=3;
		tdfVectors = new ArrayList<Map<String,Integer>>();
	}

	/**
	 * TODO :: 1. construct the global word dictionary 2. keep the word
	 * frequency for each sentence
	 */
	@Override
	public void processCas(CAS aCas) throws ResourceProcessException {
		//ArrayList<Integer> dummyIds = new ArrayList<Integer>();

		Map<String,Integer> vector =new HashMap<String,Integer>();
		JCas jcas;
		try {
			jcas =aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		FSIterator it = jcas.getAnnotationIndex(Document.type).iterator();
		//int i=0;
		if (it.hasNext()) {
			Document dummy = (Document) it.next();
			//i++;

			FSList fsTokenList = dummy.getTokenList();

			for (Token t :Utils.fromFSListToCollection(fsTokenList, Token.class)){
				globalDictionary.put(t.getText(), t.getFrequency());
				vector.put(t.getText(), t.getFrequency());
				//i++;
			}

			tdfVectors.add(vector);
			//numToks.add(i);
			//i=0;
			docText.add(dummy.getText());

			//ArrayList<Token>tokenList=Utils.fromFSListToCollection(fsTokenList, Token.class);
			//System.out.println("getQueryID(): "+doc.getQueryID() + " -- getText: " + doc.getText() + " -- getRelevanceValue: " + doc.getRelevanceValue());

			qIdList.add(dummy.getQueryID());
			relList.add(dummy.getRelevanceValue());		
			if(dummy.getRelevanceValue()==1){
				corText.add(dummy.getText());
			}
			if(dummy.getRelevanceValue()==99){
				querries.add(dummy.getText());
			}
		}//if
	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	// TODO :: compute the cosine similarity measure
	// TODO :: compute the rank of retrieved sentences
	// TODO :: compute the metric:: mean reciprocal rank
	@Override
	public void collectionProcessComplete(ProcessTrace arg0)
			throws ResourceProcessException, IOException {
		
		Map<String, Integer> queryVector = new HashMap<String, Integer>();
		Map<String, Integer> docVector= new HashMap<String, Integer>();
		ArrayList<Double> localScores = new ArrayList<Double>();
		ArrayList<Double> dScores = new ArrayList<Double>();
		ArrayList<Integer> localRanks = new ArrayList<Integer>();
		ArrayList<Integer> indexes =new ArrayList<Integer>();
		ArrayList<Double> correctScores =new ArrayList<Double>();
		int rel=0;
		int countAnws=0,countQs=1, qNum=0, aNum=0;

		super.collectionProcessComplete(arg0);

		relList.add(99);
		for(int docsIt=0;docsIt<(qIdList.size()+1);docsIt++){
			
			//check first for queries
			if(relList.get(rel)==99){
				if(docsIt<qIdList.size())
					queryVector=tdfVectors.get(rel);				
				
				if(aNum > 0){
					Collections.sort(localScores);
					Collections.reverse(localScores);
	
					//sort acording to Scores
					for(double d : dScores)
						localRanks.add(localScores.indexOf(d));
					//find correct answers
					int countArr=0, indCorAnw=0;
					for(int rels=(rel-aNum);rels<(rel+1);rels++){
						countArr++;
						if(relList.get(rels)==1){
							indCorAnw=countArr-1;
							indexes.add(rels);
						}
					}
					//correct rank!!
					int inx = localRanks.indexOf(indCorAnw);
					if(inx==-1){
						ranks.add(1);
						correctScores.add(localScores.get(0));
					}else{
						ranks.add(localRanks.get(inx)+1);
						correctScores.add(localScores.get(inx));
					}
					dScores.clear();
					localScores.clear();
					localRanks.clear();
					qNum++;
					
				}//if aNum
				aNum=0;
				countQs=1;
			}else{
				//check for answers corresponding to query
				docVector=tdfVectors.get(rel);
				countAnws=1;
			}//else

			if(countAnws==1 && countAnws==1){
				localScores.add(computeCosineSimilarity(queryVector, docVector));
				dScores.add(computeCosineSimilarity(queryVector, docVector));
				countAnws=0;
				aNum++;
			}//IF
			rel++;
		}//FOR
		

		double metric_mrr = compute_mrr();
		
		printAnswers(correctScores);
		System.out.println("(MRR) Mean Reciprocal Rank ::" + metric_mrr+"\n\n");
		
	}

	
	private void printAnswers(ArrayList<Double> correctScores){
		int i=0;
		for(double s: correctScores){
			System.out.println("Query: "+querries.get(i));
			System.out.println("Correct Answer: "+corText.get(i));
			System.out.println("Score: "+ s + "\t rank ="+ranks.get(i)+"\t quid="+(i+1)+"\n");
			i++;
		}
		
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

			if(globalDictionary.containsKey(qKey) && docVector.containsKey(qKey)){
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
