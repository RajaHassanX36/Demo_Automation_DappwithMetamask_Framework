package com.dappwithmm.qa.Analyzer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/*This clas is for retry test cases which are failed due to any reason at least 3 time 
then declare as a failed 
this will implemented on test level
*/
public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 0;

	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}

