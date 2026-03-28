package SeleniumLearning.SeleniumFrameworkLearning;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CartDashboardPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CheckoutPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ConfirmationPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ProductsCataloguePage;
import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.BaseTest;
import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.RetryAnalyzer;

public class LandingTest extends BaseTest{

	@Test(groups={"ErrorHandling"}, retryAnalyzer = RetryAnalyzer.class)
	public void loginErrorValidation() throws IOException {

		String errorMessageExpected = "Incorrect email or password."; // add "," to fail
		
		landingPage.login("abelandaman@gmail.com", "Mba@h2026"); // move to Catalogue page
		AssertJUnit.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase(errorMessageExpected));
		//must 
	}

}