package winapp.cti.qa.analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	int counter = 0; //Current script attempt
	int retryLimit = 3; //Max amount of times a script will run, if it fails
	
	//If a script fails, this method retries that script, up to the 'retryLimit' number
	public boolean retry(ITestResult result) {
		
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		
		return false;
		
	}
	
}