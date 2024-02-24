package org.iit.healthcare.patientmodule.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// How do we call the driver used inside the login() method below we need to pass form the class where we have instantiated it and declare a local variable and use the driver
public class MMPLib1 {
	WebDriver driver;//  Even though driver is a global variable passed thru other class  we need declare a local driver variable and 
	
	public MMPLib1(WebDriver driver)
	{
		this.driver = driver;//assign the global variable value to the local variable and use/modify the local variable in this class. 
	}
	
	// if a method in a class is non static(here the login method) we can create an object of this class( MMPlib )
	//in some other class  and call this login method. Whichever methods we will be using in several class we can keep them in a separate class 
	//create a object of that class in the class we want to use the method. We must avoid using extends as we can extend only one class. for example if you want to use login from one class 
	//and random method from the other class you can create an object of the classes and use the methods instead of extends as 
	//you will be able to extend only one class.
	// we can avoid hard coding by passing values as parameters .Here username, password.
	//In keyword driver framework we read values from the excel or xlsx using a keyword . against that keyword in excel or xlsx values will be stored and we connect the xlsx  or excel using the keyword 
	
	
	public String login(String username, String password)
	{
		    driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.name("submit")).click();
			String actual = driver.findElement(By.xpath("//h3[normalize-space()='ria1']")).getText().trim();
			return actual;
	}
	
/*public void navigateToModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()= "+moduleName+"]")).click();
	}
	public void launchBrowser(String url)
	{
		driver.get(url);
	}*/

}
