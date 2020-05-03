package org.com.projectname.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestListeners extends TestListenerAdapter{
	
   public ExtentHtmlReporter htmlreporter;
   public ExtentReports extent;
   public ExtentTest test;
   
   public void onStart(ITestContext testcontext) {
	   htmlreporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myreport.html");
	   htmlreporter.config().setDocumentTitle("Automation Report");
	   htmlreporter.config().setReportName("Funtional Testing");
	   htmlreporter.config().setTheme(Theme.DARK);
	   
	   extent= new ExtentReports();
	   extent.attachReporter(htmlreporter);
	   extent.setSystemInfo("HostName", "LocalHost");
	   extent.setSystemInfo("Environment", "QA");
	   extent.setSystemInfo("User", "Siva");
   }

   public void onTestSuccess(ITestResult result) {
	   
	   test = extent.createTest(result.getName());
	   
	   test.log(Status.PASS, "The TestCase Passed is "+result.getName());
   }
   
   public void onTestFailure(ITestResult result) {
	   test=extent.createTest(result.getName());
	   
	   test.log(Status.FAIL, "The TestCase Failed is "+result.getName());
	   test.log(Status.FAIL, "The TestCase Failed is "+result.getThrowable());
   }
   
   public void onTestSkipped(ITestResult result) {
	   test=extent.createTest(result.getName());
	   
	   test.log(Status.SKIP, "The TestCase Skipped is "+result.getName());
   }
   
   public void onFinish(ITestContext testcontext) {
	   extent.flush();
   }
}
