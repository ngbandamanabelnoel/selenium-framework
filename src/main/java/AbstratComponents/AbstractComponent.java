package AbstratComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.CartDashboardPage;
import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.OrderDashboardPage;

public abstract class AbstractComponent {

	protected WebDriver driver;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	protected WebElement cartDashboardWebElement;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	protected WebElement orderDashboardWebElement;
	
	private WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void waitForElementsToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

	}
	
	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToDesappear(By findBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement webElement) {
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public CartDashboardPage goToCart() {
		this.waitForWebElementToAppear(cartDashboardWebElement);
		cartDashboardWebElement.click();
		return new CartDashboardPage(driver);
	}
	
	public OrderDashboardPage goToOrders() {
		this.waitForWebElementToAppear(orderDashboardWebElement);
		orderDashboardWebElement.click();
		return new OrderDashboardPage(driver);
	}
}
