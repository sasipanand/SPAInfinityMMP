package org.iit.healthcare.patientmodule.tests;

import java.util.Random;

import org.iit.healthcare.mmp.util.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditProfileTestsfnln extends BaseClass{
	//WebDriver driver;
	 MMPLib1 mmpLib;
	/**
	 * 
	 * Validate LastName with valid and invalid
	 * Validate License - 8 Digit Random 
	 * 
	 */
	
	@BeforeClass
	public void instantiateDriver()// have calling driver separately as we are calling in each tc 
	{
		//WebDriverManager.chromedriver().setup();
	   // driver = new ChromeDriver();
	    mmpLib = new MMPLib1(driver);
	}
	@Test
	
	public void validateEditProfileTest() throws InterruptedException
	{
	     
	    mmpLib.login("ria1", "Ria12345");
		boolean result = editFirstName();
		Assert.assertTrue(result);
		Thread.sleep(3000);	
		System.out.println("editFirstName()"+result);
		boolean result1 = editLastName();
		Assert.assertTrue(result1);
		Thread.sleep(3000);	
		System.out.println("editLastName()"+result1);
	}
	@Test
	public void validateEditProfileFileIs_NonEditable() throws InterruptedException
	{
	    
	 //   mmpLib.login();
		boolean result2 = editProfile_nonEditableFields_fn();
		Assert.assertTrue(result2);
		Thread.sleep(3000);	
		System.out.println("editProfile_nonEditableFields_fn()"+result2);
		boolean result3 = editProfile_nonEditableFields_ln();
		Assert.assertTrue(result3);
		Thread.sleep(3000);	
		System.out.println("editProfile_nonEditableFields_ln()"+result3);
	}
	
	@Test
	public void validateFName_withInvalidData() throws InterruptedException
	{
		 
	    
	   // mmpLib.login();
		boolean result = editFirstName_withInvalidData();
		Assert.assertTrue(result);
		Thread.sleep(3000);	
		System.out.println("editFirstName_withInvalidData()"+result);
	}
	@Test

	public void validateLName_withInvalidData() throws InterruptedException
	{
		 
	    
	  //  mmpLib.login();
		boolean result = editLastName_withInvalidData();
		Assert.assertTrue(result);
		Thread.sleep(3000);	
		System.out.println("editLastName_withInvalidData()"+result);
	}
	
	
	public boolean editProfile_nonEditableFields_fn()
	{
		//driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		System.out.println("clicking profile for chking fn is readonly");
		String actual = driver.findElement(By.xpath("//input[@id='fname']")).getAttribute("readonly");
		System.out.println(actual);
		String expected="readonly";
		return expected.equals(actual);
	}
	public boolean editProfile_nonEditableFields_ln()
	{
		//driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		WebElement actualtemp = driver.findElement(By.xpath("//input[@id ='lname']"));
		String actual = actualtemp.getAttribute("readonly");
		System.out.println(actual);
		String expected="readonly";
		return expected.equals(actual);
	}
	public boolean editFirstName_withInvalidData()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("fname")).clear();
		driver.findElement(By.id("fname")).sendKeys(generateRandomNumber());
		driver.findElement(By.id("Sbtn")).click();
		String actual = driver.findElement(By.id("firsterr1")).getText().trim();
		String expected="please enter only alphabets";
		return expected.equals(actual);
	}
	public boolean editLastName_withInvalidData()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("lname")).clear();
		driver.findElement(By.id("lname")).sendKeys(generateRandomNumber());
		driver.findElement(By.id("Sbtn")).click();
		String actual = driver.findElement(By.id("lasterr1")).getText().trim();
		String expected="please enter only alphabets";
		return expected.equals(actual);
	}
	public boolean editFirstName()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("fname")).clear();
		String expectedfNameValue = "FNAMEAUT"+generateRandomString();
		driver.findElement(By.id("fname")).sendKeys(expectedfNameValue);
		driver.findElement(By.id("Sbtn")).click();
		Alert alrt = driver.switchTo().alert();
		alrt.accept();
		String actualfNameValue = driver.findElement(By.id("fname")).getAttribute("value");
		return expectedfNameValue.equals(actualfNameValue);
		
	}
	public boolean editLastName()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.id("Ebtn")).click();
		driver.findElement(By.id("lname")).clear();
		String expectedlNameValue = "LNAMEAUT"+generateRandomString();
		driver.findElement(By.id("lname")).sendKeys(expectedlNameValue);
		driver.findElement(By.id("Sbtn")).click();
		Alert alrt = driver.switchTo().alert();
		alrt.accept();
		String actualfNameValue = driver.findElement(By.id("lname")).getAttribute("value");
		return expectedlNameValue.equals(actualfNameValue);
		
	}
	public String generateRandomString()
	{
		Random rand = new Random();
		int u = 65+ rand.nextInt(26);
		char upperCase = (char) u;
		System.out.println("UpperCase::" + upperCase );
		
		//lower 97 to 122
		int l = 97+rand.nextInt(122-97+1); 
		char lowercase = (char) l;
		System.out.println("lowercase:: " + lowercase);
		
		String randomString = upperCase+""+lowercase+"";
		return randomString;
		
	}
	public String generateRandomNumber()
	{
		Random rand = new Random();
		int u = 65+ rand.nextInt(26);
		char upperCase = (char) u;
		System.out.println("UpperCase::" + upperCase );
		
		//lower 97 to 122
		int l = 97+rand.nextInt(122-97+1); 
		char lowercase = (char) l;
		System.out.println("lowercase:: " + lowercase);
		
		String randomString = upperCase+lowercase+"";
		return randomString;
		
	}
	 
	

}
