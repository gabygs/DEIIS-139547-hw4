package edu.cmu.lti.f13.hw4.hw4_139547.annotators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Document;
import edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Token;
import edu.cmu.lti.f13.hw4.hw4_139547.utils.Utils;

public class DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

	//stop words
	public ArrayList<String> stopWords;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		System.out.println("\n------------------Started DocumentVectorAnnotator ------------------\n");

		BufferedReader br = null;
		String stopWsFile="src/main/resources/stopwords.txt";
		stopWords = new ArrayList<String>();

		try{
			br = new BufferedReader(new FileReader(stopWsFile));
			String line;
			int countLine=0;
			while ((line = br.readLine()) != null) {
				if(countLine>2)
					stopWords.add(line);
				countLine++;
			}
		} catch (Exception e) {
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}// end catch IOException
			}//end if
		}// end finally


		FSIterator<Annotation> iter = jcas.getAnnotationIndex().iterator();
		if (iter.isValid()) {
			iter.moveToNext();
			Document doc = (Document) iter.get();
			createTermFreqVector(jcas, doc);
		}
		System.out.println("\n------------------------------------------------------------------------\n");

	}
	/**
	 * 
	 * @param jcas
	 * @param doc
	 */

	private void createTermFreqVector(JCas jcas, Document doc) {

		String docText = doc.getText();

		//TO DO: construct a vector of tokens and update the tokenList in CAS
		//Vector of tokens for tokenList
		ArrayList<Token> tokList = new ArrayList<Token>();

		//parsing with regex
		Pattern patternToks = Pattern.compile("[A-Za-z0-9']+");
		int position = 0;
		Matcher matchToks = patternToks.matcher(docText);

		while(matchToks.find(position)){
			String wordFound;
			wordFound=docText.substring(matchToks.start(), matchToks.end()).toLowerCase();

			if(stopWords.contains(wordFound)){
				//ignore stopWords
				position=position+wordFound.length()+1;
				//System.out.println("Stop word: "+wordFound+" size: "+wordFound.length() );
			}else{
				//ADD TOKEN
				Token token = new Token(jcas);
				token.setBegin(matchToks.start());
				token.setEnd(matchToks.end());
				token.setText(wordFound);
				token.setFrequency(1);
				token.addToIndexes();

				position=matchToks.end();

				int inTokList = 0;

				//frequency count
				for (Token tok : tokList) {
					if (token.getText().equals(tok.getText())) {
						tok.setFrequency(tok.getFrequency()+1);
						inTokList = 1;
					}//if
				}//for

				//if token was not already in tokenList then add it!!
				if (inTokList==0)
					tokList.add(token);
			}//else

		}//while
		doc.setTokenList(Utils.fromCollectionToFSList(jcas, tokList));
		/*
		System.out.println("Doc: " + doc.getText()); 
		for (Token t :Utils.fromFSListToCollection(doc.getTokenList(), Token.class))
			System.out.println("TokenList contains: " + t.getText() + "," + t.getFrequency());
		 */

	}//createTernVectorFreq

}//class
