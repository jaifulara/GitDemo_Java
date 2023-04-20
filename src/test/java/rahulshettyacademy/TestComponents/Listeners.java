package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
	}
	

	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test passed");
		extentTest.set(test);         //unique thread id
	}
	

	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
