package SeleniumLearning.SeleniumFrameworkLearning;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CartDashboardPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CheckoutPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ConfirmationPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ProductsCataloguePage;
import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.BaseTest;

public class ConfirmationTest extends BaseTest {

	CheckoutPage checkoutPage;
	
	@Test(dataProvider = "getData")
	public void submitedOrderMatchConfirmation(HashMap<String, String> dataMap) throws IOException, InterruptedException {
		

		ProductsCataloguePage productsCataloguePage = landingPage.login(dataMap.get("email"),dataMap.get("password"));																																										// page
		productsCataloguePage.addProductToCartByName(dataMap.get("productName"));

		CartDashboardPage cartDashboardPage = productsCataloguePage.goToCart(); 
		boolean match = cartDashboardPage.verifyProductIntoCart(dataMap.get("productName"));
		
		AssertJUnit.assertTrue(match);
	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitedOrderMatchConfirmation" })
	public void submitedOrderMessageConfirmation(HashMap<String, String> dataMap)  { 
		
		ProductsCataloguePage productsCataloguePage = landingPage.login(dataMap.get("email"),dataMap.get("password"));																
		productsCataloguePage.addProductToCartByName(dataMap.get("productName"));

		CartDashboardPage cartDashboardPage = productsCataloguePage.goToCart();
		CheckoutPage checkoutPage = cartDashboardPage.goToCheckout();
		checkoutPage.selectCountryByName(dataMap.get("countryName"));

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		AssertJUnit.assertTrue(confirmationPage.retrieveConfirmmationMessage().equalsIgnoreCase("Thankyou for the order."));
	}

	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data = this.getJsonDataMap("\\src\\test\\java\\SeleniumLearning\\data\\purchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}