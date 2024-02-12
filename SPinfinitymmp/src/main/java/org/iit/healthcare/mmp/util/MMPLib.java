package org.iit.healthcare.mmp.util;

import org.iit.healthcare.mmp.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MMPLib {
	WebDriver  driver ;
	public MMPLib(WebDriver driver) {
		 this.driver = driver ;
	}
	
	public HomePage loginValidUser(String username,String password)
	{
		 
		 driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.name("submit")).click();
			return new HomePage(driver);
		 
	}
	public  String loginInValidUser(String username,String password)
	{
		 
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.name("submit")).click();
			String errorMsg = driver.switchTo().alert().getText();
			return errorMsg;
		 
	}
	public void navigateToAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();

	}
	public void launchBrowser(String url)
	{
	    driver.get(url);
	}
	
	 
}
