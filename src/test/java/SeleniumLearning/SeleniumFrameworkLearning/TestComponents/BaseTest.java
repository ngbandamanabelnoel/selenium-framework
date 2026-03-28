package SeleniumLearning.SeleniumFrameworkLearning.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumLearning.SeleniumFrameworkLearning.PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\ngban\\eclipse-workspace\\SeleniumFrameworkDesign1\\src\\main\\java\\SeleniumLearning\\SeleniumFrameworkLearning\\resources\\GlobalData.properties");
		properties.load(fileInputStream);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser") ;
		
		if(browserName.contains("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			chromeOptions.addArguments("headless");				
			}
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().setSize(new Dimension(1440,900)); // run full screen
			
		}else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public List<HashMap<String, String>> getJsonDataMap(String pathFile) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + pathFile),
				StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	//to integrate to extent reports
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File screenshotFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destinationScreenFile = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(screenshotFile, destinationScreenFile);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = this.initializeDriver();
		landingPage = new LandingPage(driver);
		this.goTo();
		return landingPage;
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void quitTest(){
		driver.quit();
	}
}
