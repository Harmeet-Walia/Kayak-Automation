package com.kayak.AutomationProject;

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
	//By departureDates=By.xpath("//*[@aria-label='August 23']");
	//By returnDates=By.xpath("//*[@aria-label='September 13']");
	By listCities = By.xpath("//div[@role='listitem']");
	By removeCities = By.xpath("//*[contains(@id,'-origin-airport-smartbox-dropdown')]/ul/li/div[3]/div[2]");
	
	By departdate=By.xpath("//*[contains(@id,'dateRangeInput-display-start-inner')]");
	By departdateClearField=By.xpath("//*[contains(@id,'depart-input')]");
	By returnDateBox=By.xpath("//*[contains(@id,'dateRangeInput-display-end-inner')]");
	By returnDateInnerBox=By.xpath("//*[contains(@id,'return-input')]");
	By submitButton=By.xpath("//button[@type='submit' and @title='Search flights']");
	By originIncludeNearByAirport = By.xpath("//*[contains(@id,'origin-airport-nearbyCheck-icon')]");
	By destIncludeNearByAirport = By.xpath("//*[contains(@id,'-destination-airport-nearbyCheck-icon')]");
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterOriginCity(String originCity, String originIncludeNearBy) throws InterruptedException {
		WebElement originLabel = driver.findElement(originPlaceholder);
		
		Actions actions = new Actions(driver);
		actions.click(originLabel)
				.pause(100)
				.build()
				.perform();
				
		
		clearExistingCities();
		WebElement element  = driver.findElement(origin);
		element.click();
		element.clear();
		element.sendKeys(originCity);
		if(originIncludeNearBy.equals("TRUE"))
			driver.findElement(originIncludeNearByAirport).click();
		element.sendKeys(Keys.RETURN);
		
		
	}
	
	private void clearExistingCities() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			WebElement removeButtons = driver.findElement(removeCities);
			removeButtons.click();
		}catch(Exception e) {
			//ignore cause sometimes the multicity does not appear
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		
	}

	public void enterDestinationCity(String destinationCity, String destIncludeNearBy) {
		WebElement destinationDiv = driver.findElement(destinationPlaceholder);
		WebElement destinationInput = driver.findElement(destination);
		
		Actions actions = new Actions(driver);
		actions.click(destinationDiv)
			.pause(1500)
			.click(destinationInput)
			.pause(1500)
			.sendKeys(destinationCity)
			.pause(1000)
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
		driver.findElements(submitButton).get(0).click();
		
	}

	
}
