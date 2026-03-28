package SeleniumLearning.SeleniumFrameworkLearning.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getExtentReporterObject() {
		// ExtentSpartReporter,  ExtentReports
		String reportsPath = System.getProperty("user.dir")+"\\reports\\index.html";
		// meant to create reports : configuration handling
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportsPath); 
		extentSparkReporter.config().setReportName("Web Automation Results");
		extentSparkReporter.config().setDocumentTitle("Test Results");
		
		// meant to drive all reports executions
		ExtentReports extentReports = new  ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Tester", "Abel");
		
		return extentReports;
	}
}
