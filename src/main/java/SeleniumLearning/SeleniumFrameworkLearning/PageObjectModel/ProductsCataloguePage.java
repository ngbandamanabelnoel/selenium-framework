package SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstratComponents.AbstractComponent;

public class ProductsCataloguePage extends AbstractComponent {

	By productsBy = By.xpath("//div[@class='card-body']");
	By confirmationBy = By.cssSelector("#toast-container");
	By loadingConfirmationBy = By.cssSelector(".ng-animating");
	By addButtonBy = By.cssSelector(".card-body button:last-of-type");

	public ProductsCataloguePage(WebDriver driver) {

		super(driver);
		PageFactory.initElements(driver, this); // initialize with values when it calls
	}

	public void waitForProductsToAppear() {

		this.waitForElementsToAppear(productsBy);
	}
	
	public void waitForAddButtonToAppear() {
		this.waitForElementToAppear(addButtonBy);
	}

	public void waitForConfirmationToAppear() {

		this.waitForElementToAppear(confirmationBy);
	}

	public void waitForLoadingConfirmationToDesappear() {

		this.waitForElementToDesappear(loadingConfirmationBy);
	}

	public List<WebElement> getProductsWebElement() {
		return driver.findElements(productsBy);
	}

	public WebElement getProductWebElementByName(String productName) {
		this.waitForProductsToAppear();
		List<WebElement> productsWebElement = this.getProductsWebElement();
		WebElement productWebElement = productsWebElement.stream()
				.filter(prod -> prod.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		return productWebElement;
	}

	public void addProductToCartByName(String productName) {
		this.waitForAddButtonToAppear();
		WebElement productWebElement = getProductWebElementByName(productName);
		productWebElement.findElement(addButtonBy).click();
		this.waitForConfirmationToAppear();
		this.waitForLoadingConfirmationToDesappear();
	}
	
}
