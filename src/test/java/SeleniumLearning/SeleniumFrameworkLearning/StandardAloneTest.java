package SeleniumLearning.SeleniumFrameworkLearning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandardAloneTest {
	
	@Test
	public void singleTest() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String productName = "iphone 13 pro";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client/");

		// Login
		driver.findElement(By.id("userEmail")).sendKeys("abelngbandaman@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mba@2026");
		driver.findElement(By.id("login")).click();

		// wait products to load
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card-body']")));

		// Playing on the scope
		List<WebElement> products = driver.findElements(By.xpath(("//div[@class='card-body']")));
		WebElement product = products.stream()
				.filter(prod -> prod.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		// go to the cart
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		// verify items in the cart
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='cartSection']/h3")));
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		// validate checkout
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));

		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
				.perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		action.scrollByAmount(0, 500).build().perform(); // be carefull with scrolling

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confMessage = driver.findElement(By.cssSelector("td h1")).getText();

		Assert.assertTrue(confMessage.equalsIgnoreCase("Thankyou for the order."));

	}
}