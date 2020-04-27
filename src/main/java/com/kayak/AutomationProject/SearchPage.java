package com.kayak.AutomationProject;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SearchPage {
	
	WebDriver driver;
	By originPlaceholder=By.xpath("//*[contains(@id,'origin-airport-display')]");
	By origin=By.xpath("//*[@name='origin']");
	By destinationPlaceholder=By.xpath("//*[contains(@id, 'destination-airport-display')]");
	By destination=By.xpath("//*[@name='destination']");
	By listCities = By.xpath("//div[@role='listitem']");
	By removeCities = By.xpath("//*[contains(@class,'-origin-airport-smartbox-dropdown')]/ul/li/div[3]/div[2]");
	
	By departdate=By.xpath("//*[contains(@id,'dateRangeInput-display-start-inner')]");
	By departdateClearField=By.xpath("//*[contains(@id,'depart-input')]");
	By returnDateBox=By.xpath("//*[contains(@id,'dateRangeInput-display-end-inner')]");
	By returnDateInnerBox=By.xpath("//*[contains(@id,'return-input')]");
	By submitButton=By.xpath("//button[@type='submit' and @title='Search flights']");
	By originIncludeNearByAirport = By.xpath("//div[@class='multiAirportCheckbox__checkbox']");
	By destIncludeNearByAirport = By.xpath("//*[contains(@id,'-destination-airport-nearbyCheck-icon')]");
	By closeDialogBox=By.xpath("//div[contains(@aria-label,' number 1:')]");
	By assertionOriginCity=By.xpath("//*[contains(@id,'-origin-airport-display-multi-container')]/div[1]/div[1]/div[1]");
	By assertionDestinationCity=By.xpath("//*[contains(@id,'-destination-airport-display-multi-container')]/div/div[1]/div[2]");
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterOriginCity(String originCity, String originIncludeNearBy) throws InterruptedException {
		WebElement originLabel = driver.findElement(originPlaceholder);
		
		Actions actions = new Actions(driver);
		actions.click(originLabel)
				.pause(300)
				.build()
				.perform();
				
		
		WebElement element  = driver.findElement(origin);
		element.click();
		// clear any selected cities
		actions.sendKeys(Keys.BACK_SPACE)
				.sendKeys(Keys.BACK_SPACE)
				.pause(300)
				.build()
				.perform();

		actions.sendKeys(originCity)
				.pause(400)
				.build()
				.perform();
		driver.findElement(originIncludeNearByAirport).click();
		element.sendKeys(Keys.RETURN);
		if(originIncludeNearBy.equals("TRUE")) {
			originLabel = driver.findElement(originPlaceholder);
			actions = new Actions(driver);
			actions.click(originLabel)
					.pause(100)
					.build()
					.perform();
			driver.findElement(originIncludeNearByAirport).click();

		}
	}
	

	public void enterDestinationCity(String destinationCity, String destIncludeNearBy) {
		WebElement destinationDiv = driver.findElement(destinationPlaceholder);
		WebElement destinationInput = driver.findElement(destination);
		
		Actions actions = new Actions(driver);
		actions.click(destinationDiv)
			.pause(600)
			.click(destinationInput)
			.sendKeys(Keys.BACK_SPACE)
			.sendKeys(Keys.BACK_SPACE)
			.pause(300)
			.sendKeys(destinationCity)
			.pause(1000)
			.sendKeys(Keys.RETURN)
			.perform();

		if(destIncludeNearBy.equals("TRUE")){
			driver.findElement(destIncludeNearByAirport).click();
		}
		destinationInput.sendKeys(Keys.ENTER);
	}
	
	public void enterdepartureDate(String depDate) {
		
		WebElement departdatebox= driver.findElement(departdate);
		WebElement oldDepartDate=driver.findElement(departdateClearField);
		Actions actions = new Actions(driver);
		actions.click(departdatebox)
				.pause(1500)
				.click(oldDepartDate)
				.pause(1000)
				.build()
				.perform();
	
		oldDepartDate.clear();
		oldDepartDate.sendKeys(depDate);
		oldDepartDate.sendKeys(Keys.RETURN);
		
	}
	
	public void setReturnDate(String retDate) {
		WebElement returnDate= driver.findElement(returnDateBox);
		WebElement oldReturnDate=driver.findElement(returnDateInnerBox);
		Actions actions = new Actions(driver);
		actions.click(returnDate)
				.pause(1000)
				.click(oldReturnDate)
				.pause(1500)
				.build()
				.perform();
	
		oldReturnDate.clear();
		actions.sendKeys(oldReturnDate,retDate)
				.pause(1500)
				.sendKeys(oldReturnDate, Keys.ENTER)
				.pause(1000)
				.perform();
		
		
	}
	
	public void clickOnSubmit() {
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		driver.findElements(submitButton).get(0).click();

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
			if(!winHandle.equals(winHandleBefore)) {
				driver.switchTo().window(winHandle);
				break;
			}
		}
	}
	
	public void closeTheDialogBox() {
		WebElement element = driver.findElement(closeDialogBox);
		Actions actions = new Actions(driver);
		actions.pause(500)
				.click(element)
				.build()
				.perform();
	}
	
	

	public void selectTheFlight(int N) {
		String xpathOfResult="//div[contains(@aria-label,' number "+N+"')]";
		driver.findElement(By.xpath(xpathOfResult)).click();
		
		
	}

	public String getOriginCity() {
		String originCity=driver.findElement(assertionOriginCity).getText();
		return originCity;
		
	
		
		
	}
	
	public String getDestinationCity() {
		String destinationCity=driver.findElement(assertionDestinationCity).getText();
		return destinationCity;
	}

	
}
