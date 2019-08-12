package com.kayak.AutomationProject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kayak.AutomationProject.utils.ExcelUtils;

public class Test_Search extends BaseTest {
	
	@Test(dataProvider = "getDataForSearch")
	public void TestSearch(String origin, String originIncludeNearBy, String destination, String destIncludeNearBy,
			String depDate, String retDate) throws InterruptedException {
//		LoginPage login = new LoginPage(driver);
//		login.login();
		SearchPage search=new SearchPage(driver);
		search.enterOriginCity(origin, originIncludeNearBy);
		search.enterDestinationCity(destination, destIncludeNearBy);
		search.enterdepartureDate(depDate);
		search.setReturnDate(retDate);
		search.clickOnSubmit();
		
	}
	
	@DataProvider
	public Object[][] getDataForSearch(){
		try {
			Object[][] testData = ExcelUtils.getTableArray("C:\\Users\\walia\\eclipse-workspace\\AutomationProject\\src\\test\\resources\\TestData-Kayak.xlsx", 
											"TestData", 6);
			return testData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
