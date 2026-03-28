package SeleniumLearning.SeleniumFrameworkLearning.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumLearning.SeleniumFrameworkLearning.resources.ExtentReportNG;

public class TestListeners extends BaseTest implements ITestListener{
	
	ExtentTest extentTest;
	ExtentReports extentReports = ExtentReportNG.getExtentReporterObject();
	ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<ExtentTest>(); // avoid misunderstanding in method execution while runing parallel
	
	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName()); //try out my getName()
		threadLocalTest.set(extentTest); // unique thread id (error validation test)
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS,result.getMethod().getMethodName()+" : Passed !");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadLocalTest.get().fail(result.getMethod().getMethodName()+" : Failed ! Reason : "+result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String screnShotPath = null;
		try {
			screnShotPath = this.getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(screnShotPath,result.getMethod().getMethodName()) ;
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.log(Status.SKIP,result.getMethod().getMethodName()+" : Skiped !");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush(); // notify test is done
	}

	
}
