package features;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = "SeleniumLearning.stepsdefinitions", 
monochrome=true, tags = "@errorsvalidations", plugin= {"html:target/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
