package SeleniumLearning.stepsdefinitions;

import java.io.IOException;

import org.testng.AssertJUnit;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CartDashboardPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CheckoutPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ConfirmationPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ProductsCataloguePage;
import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionsImpl extends BaseTest {
	
	public ProductsCataloguePage productsCataloguePage;
	public CartDashboardPage cartDashboardPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
//	@Then("I redirect to the confirmation page and get the mesage {string} on display.")
//	public void retrieveConfirmmationMessage(String expectedMessage) {
//		AssertJUnit.assertTrue(confirmationPage.retrieveConfirmmationMessage().equalsIgnoreCase(expectedMessage));
//	}
//	
//	

@Given("I landed on E-commercer Page")
public void i_landed_on_e_commercer_page() throws IOException {
	this.landingPage = launchApplication();
}
@Given("Logged in with correct username {string} and  password {string}")
public void logged_in_with_correct_username_and_password(String username, String password) {
	this.productsCataloguePage = landingPage.login(username, password); // move to Catalogue page
}
@When("add the product {string} to home page")
public void add_the_product_to_home_page(String productName) {
	this.productsCataloguePage.addProductToCartByName(productName);
	this.cartDashboardPage = productsCataloguePage.goToCart();
}
@When("verify whether the product {string} is into the cart page and checkout")
public void verify_whether_the_product_is_into_the_cart_page_and_checkout(String productName) {
	this.cartDashboardPage.verifyProductIntoCart(productName);
	this.checkoutPage = this.cartDashboardPage.goToCheckout();
	this.checkoutPage.selectCountryByName("india");
	this.confirmationPage = this.checkoutPage.submitOrder();
}
@Then("I redirect to the confirmation page and get the mesage {string} on display.")
public void i_redirect_to_the_confirmation_page_and_get_the_mesage_on_display(String expectedMessage) {
	AssertJUnit.assertTrue(confirmationPage.retrieveConfirmmationMessage().equalsIgnoreCase(expectedMessage));
}
}
