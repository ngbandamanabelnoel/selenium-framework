package SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstratComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountryWebElement;

	@FindBy(css = ".action__submit")
	WebElement submitWebElement;

	By foundCountriesBy = By.cssSelector(".ta-results");
	By countryToSelectBy = By.xpath("//button[contains(@class,'ta-item')][2]");

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void waitForSelectCountryToAppear() {
		this.waitForElementsToAppear(countryToSelectBy);
	}

	public void waitForFoundCountriesToAppear() {
		this.waitForElementsToAppear(foundCountriesBy);
	}

	public void waitForCountryWebElementToAppear(WebElement webElement) {
		this.waitForWebElementToAppear(webElement);
	}

	public void selectCountryByName(String countryName) {
		this.waitForCountryWebElementToAppear(selectCountryWebElement);
		Actions action = new Actions(driver);
		action.sendKeys(selectCountryWebElement, countryName).build().perform();
		this.waitForFoundCountriesToAppear();
		driver.findElement(countryToSelectBy).click();
		;

	}

	public ConfirmationPage submitOrder() {
		Actions action = new Actions(driver);
		action.scrollByAmount(0, 500).build().perform(); // be carefull with scrolling
		submitWebElement.click();
		return new ConfirmationPage(driver);
	}

}
