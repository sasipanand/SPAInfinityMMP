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

	public class EditProfileTestCorrectCode {
		WebDriver driver;
		 MMPLib1 mmpLib;
		/**
		 * 
		 * Validate LastName with valid and invalid
		 * Validate License - 8 Digit Random 
		 * 
		 */
		
		@BeforeClass
		public void instantiateDriver()
		{
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    mmpLib = new MMPLib1(driver);
		}
		@Test
		public void validateEditProfileTest()
		{
		     
		    mmpLib.login("ria1", "Ria12345");
			boolean result = editFirstName();
			Assert.assertTrue(result);
		}
		@Test
		public void validateFName_withInvalidData()
		{
			 
		    
		    mmpLib.login("ria1", "Ria12345");
			boolean result = editFirstName_withInvalidData();
			Assert.assertTrue(result);
		}
		@Test
		public void validateEditProfileFiels_NonEditable()
		{
			 
		    
		    mmpLib.login("ria1", "Ria12345");
			boolean result = editProfile_nonEditableFields();
			Assert.assertTrue(result);
		}
		public boolean editProfile_nonEditableFields()
		{
			driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
			String actual = driver.findElement(By.id("fname")).getAttribute("readonly");
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


