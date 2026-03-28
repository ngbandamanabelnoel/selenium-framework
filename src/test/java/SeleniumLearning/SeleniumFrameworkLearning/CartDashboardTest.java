package SeleniumLearning.SeleniumFrameworkLearning;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CartDashboardPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ProductsCataloguePage;
import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.BaseTest;

public class CartDashboardTest extends BaseTest {
	
	@Test(groups={"ErrorHandling"})
	public void submitOrderCartErrorValidation() throws IOException {

		String productName = "iphone 13 pro";
		String productNameError = "iphon 13 pro";
		
		ProductsCataloguePage productsCataloguePage = landingPage.login("abelngbandaman@gmail.com", "Mba@2026"); // move to Catalogue page
		productsCataloguePage.addProductToCartByName(productName);
		
		CartDashboardPage cartDashboardPage = productsCataloguePage.goToCart(); // move to Cart page
		boolean match = cartDashboardPage.verifyProductIntoCart(productNameError);
		AssertJUnit.assertFalse(match);
		
	}
	
	@Test
	public void submitOrderCartValidation() throws IOException {

		String productName = "iphone 13 pro";
		
		ProductsCataloguePage productsCataloguePage = landingPage.login("abelngbandaman@gmail.com", "Mba@2026"); // move to Catalogue page
		productsCataloguePage.addProductToCartByName(productName);
		
		CartDashboardPage cartDashboardPage = productsCataloguePage.goToCart(); // move to Cart page
		boolean match = cartDashboardPage.verifyProductIntoCart(productName);
		AssertJUnit.assertTrue(match);
		
	}
}
