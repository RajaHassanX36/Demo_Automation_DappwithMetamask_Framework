package com.dappwithmm.qa.Analyzer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/*This class is for retry test cases which are failed due to any reason at least 3 time 
then declare as a failed 
this will implemented on run time no need to declare at test level
*/
public class MyTransformer implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)

	{

		annotation.setRetryAnalyzer(RetryAnalyzer.class);

	}

}

