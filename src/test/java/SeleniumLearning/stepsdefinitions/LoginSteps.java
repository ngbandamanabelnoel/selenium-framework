package SeleniumLearning.stepsdefinitions;

import java.io.IOException;

import org.testng.AssertJUnit;

import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest{


@Given("being on the landing page")
public void being_on_the_landing_page() throws IOException {
	this.landingPage = launchApplication();
}

@When("I try to log in with incorrect username {string} and  password {string}")
public void i_try_to_log_in_with_incorrect_username_and_password(String username, String password) {
	landingPage.login(username, password); // move to Catalogue page

}
@Then("I get the mesage {string} on display.")
public void i_get_the_mesage_on_display(String errorMessageExpected) {
	AssertJUnit.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase(errorMessageExpected));
	this.quitTest();
}

}
