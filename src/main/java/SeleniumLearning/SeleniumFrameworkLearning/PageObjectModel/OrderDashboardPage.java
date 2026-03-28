package SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstratComponents.AbstractComponent;

public class OrderDashboardPage extends AbstractComponent {

	By orderedProductsBy = By.xpath("//tr/td[2]");

	public OrderDashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getOrderedProducts() {
		return driver.findElements(orderedProductsBy);
	}
	
	
	public boolean verifyProductIntoOrderListByName(String productName) {
		this.goToOrders();
		List<WebElement> orderedProductsWebElement = this.getOrderedProducts();
		boolean present = orderedProductsWebElement.stream()
				.anyMatch(orderedProductWebElement -> orderedProductWebElement.getText().equalsIgnoreCase(productName));
		return present;
	}

}
