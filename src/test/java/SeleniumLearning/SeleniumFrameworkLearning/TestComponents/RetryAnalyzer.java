package SeleniumLearning.SeleniumFrameworkLearning.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	int countTRy = 0;
	int maxTry = 2;

	@Override
	public boolean retry(ITestResult result) {
		
		boolean tryTest = false;
		
		if(countTRy<maxTry) {
			countTRy++;
			tryTest = true;
		}
		
		return tryTest;
	}

}
