package SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstratComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	@FindBy(id = "userEmail")
	WebElement userEmailWebElement;

	@FindBy(id = "userPassword")
	WebElement userPasswordWebElement;

	@FindBy(id = "login") // xpath, css, tagName, className
	WebElement loginWebElement;
	
	By toastErrorBy = By.cssSelector("[class *='toast-error']");

	public LandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ProductsCataloguePage login(String userEmail, String passWord) {

		userEmailWebElement.sendKeys(userEmail);
		userPasswordWebElement.sendKeys(passWord);
		loginWebElement.click();
		
		return new ProductsCataloguePage(driver);

	}
	
	public void waitForErrorMessageElementToAppear() {
		this.waitForElementToAppear(toastErrorBy);
	}
	
	
	public String getErrorMessage() {
		this.waitForErrorMessageElementToAppear();
		return driver.findElement(toastErrorBy).getText();
	}

}
