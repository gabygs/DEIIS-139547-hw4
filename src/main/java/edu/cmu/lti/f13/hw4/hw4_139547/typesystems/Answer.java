

/* First created by JCasGen Fri Apr 25 00:08:28 CDT 2014 */
package edu.cmu.lti.f13.hw4.hw4_139547.typesystems;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Apr 25 00:08:31 CDT 2014
 * XML source: /Users/IBAGNOG/Documents/workspace/hw4-139547/src/main/resources/descriptors/typesystems/VectorSpaceTypes.xml
 * @generated */
public class Answer extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Answer.class);
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
  protected Answer() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Answer(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Answer(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Answer(JCas jcas, int begin, int end) {
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
  //* Feature: score

  /** getter for score - gets According to a certain similarity measure we obtain a score.
   * @generated
   * @return value of the feature 
   */
  public double getScore() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Answer");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Answer_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets According to a certain similarity measure we obtain a score. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(double v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Answer");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Answer_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: rank

  /** getter for rank - gets After obtaining a score all answers are ordered and their ranking is recorded in this manner.
   * @generated
   * @return value of the feature 
   */
  public int getRank() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Answer");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Answer_Type)jcasType).casFeatCode_rank);}
    
  /** setter for rank - sets After obtaining a score all answers are ordered and their ranking is recorded in this manner. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setRank(int v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.Answer");
    jcasType.ll_cas.ll_setIntValue(addr, ((Answer_Type)jcasType).casFeatCode_rank, v);}    
  }

    