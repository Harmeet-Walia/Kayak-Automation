package com.kayak.AutomationProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	protected WebDriver driver;
	
	@BeforeMethod
	public void initiateDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\walia\\SeleniumDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http:\\www.kayak.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}

}
