
/* First created by JCasGen Fri Apr 25 00:08:28 CDT 2014 */
package edu.cmu.lti.f13.hw4.hw4_139547.typesystems;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Apr 25 00:08:31 CDT 2014
 * @generated */
public class VSretrieval_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (VSretrieval_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = VSretrieval_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new VSretrieval(addr, VSretrieval_Type.this);
  			   VSretrieval_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new VSretrieval(addr, VSretrieval_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = VSretrieval.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
 
  /** @generated */
  final Feature casFeat_mrr;
  /** @generated */
  final int     casFeatCode_mrr;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getMrr(int addr) {
        if (featOkTst && casFeat_mrr == null)
      jcas.throwFeatMissing("mrr", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_mrr);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMrr(int addr, double v) {
        if (featOkTst && casFeat_mrr == null)
      jcas.throwFeatMissing("mrr", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_mrr, v);}
    
  
 
  /** @generated */
  final Feature casFeat_documentList;
  /** @generated */
  final int     casFeatCode_documentList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDocumentList(int addr) {
        if (featOkTst && casFeat_documentList == null)
      jcas.throwFeatMissing("documentList", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    return ll_cas.ll_getRefValue(addr, casFeatCode_documentList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocumentList(int addr, int v) {
        if (featOkTst && casFeat_documentList == null)
      jcas.throwFeatMissing("documentList", "edu.cmu.lti.f13.hw4.hw4_139547.typesystems.VSretrieval");
    ll_cas.ll_setRefValue(addr, casFeatCode_documentList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public VSretrieval_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_mrr = jcas.getRequiredFeatureDE(casType, "mrr", "uima.cas.Double", featOkTst);
    casFeatCode_mrr  = (null == casFeat_mrr) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mrr).getCode();

 
    casFeat_documentList = jcas.getRequiredFeatureDE(casType, "documentList", "uima.cas.FSList", featOkTst);
    casFeatCode_documentList  = (null == casFeat_documentList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentList).getCode();

  }
}



    