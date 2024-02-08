package org.iit.healthcare.patientmodule.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignments {
 WebDriver driver; //we are  driver globally 
	@Test
	public void validateRediffStocks()
	{
		
		/*
		 * chrome browser version
		 * chrome executable version
		 * selenium driver version
		 * System.setProperty("webdriver.chrome.driver","path to the chromedriver.exe");
		 */
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://money.rediff.com/gainers");
		String stockName = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();
		boolean result = getStockDetails(stockName);
		Assert.assertTrue(result);// assertion checking do only in test case not in the reusable methods 
		
	}
	
	public boolean getStockDetails(String stockName)
	{
		String prevClose = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[2]")).getText();
		String currClose = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[3]")).getText();
		String change =  driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[4]")).getText();
		double actual = Double.parseDouble(change.replace("+","").trim());// for double, string format, data type conversion we need not import any class as by default all class in java.lang is imported automatically. normally when we give thread.sleep it prompts you to import relevant class
		
		System.out.println(prevClose +"===" + currClose +"==="+change);
		
		double cPrice = Double.parseDouble(currClose);//we are converting string into double so that we can use it in formula
		double pClose = Double.parseDouble(prevClose);
		
		
		
		/**
		 * Formula
		 * (CurrentPrice-PreviousClose)
		 * --------------------------------- X 100
		 * (PreviousClose)
		 */
		
		
		double expected = (cPrice-pClose)*100/pClose;
		
		System.out.println("actual" + actual);
		
		
		System.out.println("Expected" + String.format("%.2f", expected));
		boolean result = (actual==expected);
		return result;
		
		
			
	}
}