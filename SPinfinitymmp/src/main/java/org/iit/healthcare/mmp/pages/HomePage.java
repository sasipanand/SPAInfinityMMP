package org.iit.healthcare.mmp.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	protected WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		
		this.driver = driver;
		
	}
	
	
public HashMap<String,String> fetchPatientData(){
		
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("doctor", driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]")).getText().trim());
		actualHMap.put("appointment", driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText().trim());
		actualHMap.put("time", driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText().trim());
		actualHMap.put("date", driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).getText().trim());
		return actualHMap;
		
	}
public String getPatientName(String username)
{
	String actual = driver.findElement(By.xpath("//h3[normalize-space()='"+username+"']")).getText().trim();
	return actual;
}

}
