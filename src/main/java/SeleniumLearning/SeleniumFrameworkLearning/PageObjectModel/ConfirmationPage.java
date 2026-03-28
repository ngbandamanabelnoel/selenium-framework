package SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstratComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	@FindBy(css = "td h1")
	WebElement confirmationMessageWebElement;

	By confirmationMessageBy = By.cssSelector("td h1");

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void waitForconfirmationMessageWebElementToAppear() {
		this.waitForWebElementToAppear(confirmationMessageWebElement);
	}

	public String retrieveConfirmmationMessage() {
		this.waitForconfirmationMessageWebElementToAppear();
		return confirmationMessageWebElement.getText();
	}
	
	

}
