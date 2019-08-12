package com.kayak.AutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
	private WebDriver driver;
	By signInLabel=By.xpath("//*[contains(@id,'-account-label')]");
	By username=By.xpath("//*[contains(@id,'-username')]");
	By password=By.xpath("//*[contains(@id,'-password')]");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void login() {
		WebElement signInButton = driver.findElement(signInLabel); 
		Actions actions = new Actions(driver);
		actions.click(signInButton).pause(300).perform();
		
		driver.findElement(username).sendKeys("raman.w@gmail.com");
		WebElement passwordText = driver.findElement(password);
		actions.sendKeys(passwordText, "PAssword@123")
				.sendKeys(passwordText, Keys.ENTER)
				.pause(1000)
				.perform();
		
		
	}
}
