

/* First created by JCasGen Fri Apr 25 00:08:28 CDT 2014 */
package edu.cmu.lti.f13.hw4.hw4_139547.typesystems;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Apr 25 00:08:31 CDT 2014
 * XML source: /Users/IBAGNOG/Documents/workspace/hw4-139547/src/main/resources/descriptors/typesystems/VectorSpaceTypes.xml
 * @generated */
public class VSretrieval extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(VSretrieval.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected VSretrieval() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public VSretrieval(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public VSretrieval(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public VSretrieval(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: mrr

  /** getter for mrr - gets Measure obtains from all documents and their raning.
   * @generated
   * @return value of the feature 
   */
  public double getMrr() {
    if (VSretrieval_Type.featOkTst && ((VSretrieval_Type)jcasType).casFeat_mrr == null)
      jcasType.jcas.throwFeatMissing("mrr", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((VSretrieval_Type)jcasType).casFeatCode_mrr);}
    
  /** setter for mrr - sets Measure obtains from all documents and their raning. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMrr(double v) {
    if (VSretrieval_Type.featOkTst && ((VSretrieval_Type)jcasType).casFeat_mrr == null)
      jcasType.jcas.throwFeatMissing("mrr", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((VSretrieval_Type)jcasType).casFeatCode_mrr, v);}    
   
    
  //*--------------*
  //* Feature: documentList

  /** getter for documentList - gets 
   * @generated
   * @return value of the feature 
   */
  public FSList getDocumentList() {
    if (VSretrieval_Type.featOkTst && ((VSretrieval_Type)jcasType).casFeat_documentList == null)
      jcasType.jcas.throwFeatMissing("documentList", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((VSretrieval_Type)jcasType).casFeatCode_documentList)));}
    
  /** setter for documentList - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentList(FSList v) {
    if (VSretrieval_Type.featOkTst && ((VSretrieval_Type)jcasType).casFeat_documentList == null)
      jcasType.jcas.throwFeatMissing("documentList", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    jcasType.ll_cas.ll_setRefValue(addr, ((VSretrieval_Type)jcasType).casFeatCode_documentList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    