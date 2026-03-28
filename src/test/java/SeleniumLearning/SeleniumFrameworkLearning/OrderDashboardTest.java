package SeleniumLearning.SeleniumFrameworkLearning;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.OrderDashboardPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.ProductsCataloguePage;
import SeleniumLearning.SeleniumFrameworkLearning.TestComponents.BaseTest;

public class OrderDashboardTest extends BaseTest {

	@Test
	public void verifyProductIntoOrderListByName() {
		String productName = "iphone 13 pro";
		ProductsCataloguePage productCataloguePage = landingPage.login("abelngbandaman@gmail.com", "Mba@2026");
		OrderDashboardPage orderDashboardPage = productCataloguePage.goToOrders();
		boolean present = orderDashboardPage.verifyProductIntoOrderListByName(productName);
		AssertJUnit.assertTrue(present);
		
	}
}
