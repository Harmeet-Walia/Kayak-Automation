package com.kayak.AutomationProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
	protected WebDriver driver;
	protected ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	protected ExtentTest test;
	
	@BeforeMethod
	public void initiateDriver() {
		System.setProperty("webdriver.chrome.driver", "tools/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http:\\www.kayak.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	@BeforeTest
	public void setExtent() {
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/extentreport.html");
		htmlReporter.config().setDocumentTitle("Kayak Automation Report");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester Name", "Harmeet Kaur");
		extent.setSystemInfo("Browser Name", "Chrome");
	}
		
	public void tearDown(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + result.getName());
			
			test.log(Status.FAIL, "Test Failed: " + result.getName());
		}else {
			test.pass("Test Passed :" + result.getName());
			test.log(Status.PASS, "Test Passed: " + result.getName());
		}
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}

}
