package SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstratComponents.AbstractComponent;

public class CartDashboardPage extends AbstractComponent {

// we use @FindBy for static or fixed webElement : doesnt change or rarely

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProductsWebElement;

	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkoutWebElement;

// we use By for List of elements, waitdriver use ... element that change enough

	By productsBy = By.xpath("//div[@class='cartSection']/h3");
	By foundCountriesBy = By.cssSelector(".ta-results");
	By countryToSelectBy = By.xpath("//button[contains(@class,'ta-item')][2]");

	public CartDashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void waitForCartProductsToAppear() {

		this.waitForElementsToAppear(productsBy);
	}

	public List<WebElement> getcartProductsWebElement() {
		this.waitForCartProductsToAppear();
		return cartProductsWebElement; // driver.findElements(productsBy);
	}

	public boolean verifyProductIntoCart(String productName) {
		this.getcartProductsWebElement();
		boolean match = cartProductsWebElement.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutWebElement.click();
		return new CheckoutPage(driver);
	}
}
