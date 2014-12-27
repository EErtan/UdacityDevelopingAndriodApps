package com.nullcognition.udacitydevelopingandriodapps;
/**
 * Created by ersin on 04/11/14 at 12:38 AM
 */
public class FullTestSuite {

   public static junit.framework.Test suite(){
	  return new android.test.suitebuilder.TestSuiteBuilder(FullTestSuite.class).includeAllPackagesUnderHere().build();
   }

   public FullTestSuite(){
	  super();
   }

}
