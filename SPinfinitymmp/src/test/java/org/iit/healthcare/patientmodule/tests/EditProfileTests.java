package org.iit.healthcare.patientmodule.tests;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditProfileTests {

		WebDriver driver;
		@BeforeClass
		public void instantiateDriver()
		{
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		}
		
		@Test
		public void validatedEditProfileTest()
		{
			MMPLib1 mmpLib = new MMPLib1(driver);
		    mmpLib.login("ria1", "Ria12345");
			boolean result =editFirstName();
			Assert.assertTrue(result);
	}
		@Test
		public void validatedFname_withInvalidData()
		{
			MMPLib1 mmpLib = new MMPLib1(driver);
		    mmpLib.login("ria1", "Ria12345");
			boolean result =editFirstName_withInvalidData();
			Assert.assertTrue(result);
	}
		
		
		@Test
		public void validateEditProfileIs_NonEditable()
		{
			MMPLib1 mmpLib = new MMPLib1(driver);
		    mmpLib.login("ria1", "Ria12345");
			boolean result =EditProfileIs_NonEditable();
			Assert.assertTrue(result);
	}
		

		private boolean EditProfileIs_NonEditable() {
			driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
			String actual =driver.findElement(By.id("fname")).getAttribute("readonly");;
			String expected = "readonly";
			return expected.equals(actual);
		}

		public boolean editFirstName()
		{
			driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
			driver.findElement(By.id("Ebtn")).click();
			driver.findElement(By.id("fname")).clear();
			String expectedfnameValue = "FNAMEAUT" + generateRandomString();
			driver.findElement(By.id("fname")).sendKeys(expectedfnameValue);
			driver.findElement(By.id("Sbtn")).click();
			Alert alrt = driver.switchTo().alert();// pop appears once you enter and save the values in the profile page. Pops are handled by alert statements
			alrt.accept();// u click ok in the alert pop up
			String actualfNameValue = driver.findElement(By.id("fname")).getAttribute("value");// In a text box the entered text will be stored in an attribute call value. so u can retrieve that text box value with attribute function
			return expectedfnameValue.equals(actualfNameValue);
			
		}
		private boolean editFirstName_withInvalidData() {
			driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
			driver.findElement(By.id("Ebtn")).click();
			driver.findElement(By.id("fname")).clear();
			driver.findElement(By.id("fname")).sendKeys(generateRandomNumber());
			driver.findElement(By.id("Sbtn")).click();
			String actual =driver.findElement(By.id("firsterr1")).getText().trim();
			String expected = "please enter only alphabets";
			return expected.equals(actual);
			
			
		}

		private String generateRandomString() {
			Random rand = new Random();
			int u = 65+ rand.nextInt(26);
			char uppercase = (char) u;
			System.out.println("UpperCase::"+uppercase);
			
			//lower 97 to 122
			int l = 97+rand.nextInt(122-97+1);
			char lowercase = (char)l;
			System.out.println("LowerCase::"+lowercase);
			
			String randomString = uppercase+lowercase+"";
						
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
