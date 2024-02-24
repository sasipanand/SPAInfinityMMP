package org.iit.healthcare.openmrs;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrationDeskAssignment {
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
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		
		//** Demographics - Name Page
		
		driver.get("https://demo.openmrs.org/openmrs/login.htm");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Admin123");
		driver.findElement(By.xpath("//ul//li[@id='Inpatient Ward']")).click();
		driver.findElement(By.xpath("//input[@id='loginButton']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register a patient']")).click();
		
		// *** Demographics - Gender Page
		
		Random rand = new Random();
		int i = rand.nextInt(100);
		
		driver.findElement(By.xpath("//input[@name='givenName']")).sendKeys("givenName" + i);
		driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys("middleName" + i);
		driver.findElement(By.xpath("//input[@name='familyName']")).sendKeys("familyName" + i);
		//driver.findElement(By.xpath("//input[@id='checkbox-unknown-patient']")).click();
		driver.findElement(By.xpath("//button[@id='next-button']")).click();
		
		driver.findElement(By.xpath("//option[text()='Female']")).click();
		driver.findElement(By.xpath("//icon[@class='fas fa-chevron-right']")).click();
	//	driver.findElement(By.xpath("//input[@id='submit']")).click();
		
		//***  Demographics - Birthdate page
		
		driver.findElement(By.xpath("//input[@id='birthdateDay-field']")).sendKeys("" + rand.nextInt(1,31));
		
		WebElement birthMonth = driver.findElement(By.xpath("//select[@id ='birthdateMonth-field']"));
		Select sel1 = new Select(birthMonth);
	    sel1.selectByIndex(rand.nextInt(13));
		
	    driver.findElement(By.xpath("//input[@id='birthdateYear-field']")).sendKeys("" + rand.nextInt(1903,2023));
	    driver.findElement(By.xpath("//icon[@class='fas fa-chevron-right']")).click();
	    
	    
	    //**** Contact Info - Address Page
	    
	    driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("Apt " + i);
	    driver.findElement(By.xpath("//input[@id='address2']")).sendKeys(i +" Street");
	    driver.findElement(By.xpath("//input[@id='cityVillage']")).sendKeys("City" + i);
	    driver.findElement(By.xpath("//input[@id='stateProvince']")).sendKeys("State/Province" + i );
	    driver.findElement(By.xpath("//input[@id='country']")).sendKeys("Country" + i);
	    driver.findElement(By.xpath("//input[@id='postalCode']")).sendKeys("082" + i);
	    driver.findElement(By.xpath("//icon[@class='fas fa-chevron-right']")).click();
	    
	    
	    //**** Contact Info - Phone Number Page
	    
	    driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("732 - 223 - " + rand.nextInt(0000,9999));
	    driver.findElement(By.xpath("//icon[@class='fas fa-chevron-right']")).click();
	    
	   
	    //**** Relationships - Relatives Page
	    
	    WebElement RelationshipType = driver.findElement(By.xpath("//select[@id ='relationship_type']"));
		Select sel2 = new Select(RelationshipType);
	    sel2.selectByIndex(rand.nextInt(1,10));
	   
	    
	    driver.findElement(By.xpath("//input[@placeholder='Person Name']")).sendKeys("PersonName" + i );
	    driver.findElement(By.xpath("//icon[@class='fas fa-chevron-right']")).click();
	    
	    
	    //**** Confirm Page 
	    driver.findElement(By.xpath("//input[@id='submit']")).click();
	    
	}
	
	
}