package com.kayak.AutomationProject.listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class ListnersClass extends TestListenerAdapter {

	public void onTestStart(ITestResult result) {
		Reporter.log("Test case starting and the name of the test case is :  "+ result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		Reporter.log("Test case passed and the name of the test case is :  "+ result.getName());
		
	}

	public void onTestFailure(ITestResult result) {
		Reporter.log("Test case failed the name of the test case is :  "+ result.getName());
		
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test case skipped and the name of the test case is :  "+ result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		
	} 

	
}
