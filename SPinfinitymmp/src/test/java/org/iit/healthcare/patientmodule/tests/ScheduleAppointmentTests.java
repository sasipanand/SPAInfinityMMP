package org.iit.healthcare.patientmodule.tests;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentTests {
	WebDriver driver;
	@Test
	public void bookAppointment() throws InterruptedException, Exception 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
		
		//Login 	
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.xpath("//input[@id ='username']")).sendKeys("ria1");
		driver.findElement(By.xpath("//input[@id ='password']")).sendKeys("Ria12345");
		driver.findElement(By.xpath("//input[@name ='submit']")).click();
		
		
		//Home Page loading verification
		String actual = driver.findElement(By.xpath("//h3[normalize-space()='ria1']")).getText().trim();
		String expected ="ria1";
		Assert.assertEquals(actual, expected);
		
		// Clicking Schedule Appointment and create new appointment
		
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		
		// Select the provider
		driver.findElement(By.xpath("//p[text() ='Description:Orthopedic']/ancestor::ul/following-sibling::button")).click();
		
		// Getting the provider name for later verification 
	//	WebElement Provider = driver.findElement(By.xpath("//p[text() ='Description:Orthopedic']/parent::div/parent::li/child::h4"));
		WebElement Provider = driver.findElement(By.xpath("//p[text() ='Description:Orthopedic']/ancestor::li/child::h4"));
		String selectedProvider = Provider.getText();
		String trimProvider = selectedProvider.replace("Dr.", "").trim();
		
		
		// Selecting the appointment details
		
		driver.switchTo().frame("myframe");
		
		//Finding and clicking the inside of the date field 
		WebElement date0 = driver.findElement(By.xpath("//input[@id='datepicker']"));
		date0.click();
		
		// Getting the month name details and to be converted it into corresponding month number to be used for later verification
		WebElement  monthname = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
		String monthName1 = monthname.getText();
		 
	    //Converting month name into corresponding month number to be used for later verification
	    DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
	    Month month = Month.from(parser.parse(monthName1));
	    int monthNumber = month.getValue();
	    
	    // Getting the year for later verification 
	    WebElement year = driver.findElement(By.xpath("(//span[@class='ui-datepicker-year'])[1]"));
	    String year1 = year.getText();
	    
		// Generating random numbers to be used for selecting random days and different appointment times each time using random index
		Random rand = new Random();
		
		// Select the appointment from next month
		driver.findElement(By.xpath("(//span[text() ='Next']")).click();
		
		//Selecting random appointment day from the calendar displayed
		WebElement date1 = driver.findElement(By.xpath("//a[text()="+rand.nextInt(1,28)+"]"));
		date1.click();
		
		//Storing the selected day for later verification
		String date2 = date1.getText();

		//Selecting random appointment time from the time field dropdown displayed
		WebElement Time = driver.findElement(By.xpath("//select[@id='time']"));
		Select sel = new Select(Time);
		sel.selectByIndex(rand.nextInt(1,3));
		
		//Storing the selected time for later verification
		WebElement Time1 = sel.getFirstSelectedOption();
		String selectedTimeoption =Time1.getText();
		
		
		//Clicking the continue button
		driver.findElement(By.xpath("(//button[normalize-space()='Continue'])[1]")).click();
		
		
		//Entering the symptoms 
		WebElement symptom =  driver.findElement(By.xpath("//textarea[@id='sym']"));
		symptom.click();
		symptom.sendKeys("Not feeling well");
		
		//Getting the entered symptom using the getAttribute(value) method. Storing the symptoms value for later verification
		String enteredsymptom = symptom.getAttribute("value");
		
		
		// Clicking Submit button
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		// Concatenating  entered month number,day,year to be used for later verification
		String date3 = monthNumber+"/"+date2+"/"+year1;
		
		//Changing the format of the selected appointment date(M/d/yyyy) to the format(MM/dd/YYYY) of the date to verified later
		SimpleDateFormat originalFormat = new SimpleDateFormat("M/d/yyyy", Locale.ENGLISH);
		SimpleDateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = originalFormat.parse(date3);	
		String formattedDate = targetFormat.format(date).trim();
		
		
		// Get the appointment date for verification
		
		WebElement AppointmentDate = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
		String AppointmentDate1 = AppointmentDate.getText().trim();
		System.out.println(AppointmentDate1 + ":" + formattedDate);
		
		//Assert.assertEquals(AppointmentDate1,formattedDate);
		
		if(AppointmentDate1.equalsIgnoreCase(formattedDate ))
		{
			System.out.println("Appointment date is correct");
			
		}
		else
		{
			System.out.println("Appointment date is incorrect");
		}
	
		
		// Get the appointment time for verification
		String AppointmentTime = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
		System.out.println(AppointmentTime + " : " +  selectedTimeoption );
		
		
		//boolean AppointmentTimeResult = (AppointmentTime.equalsIgnoreCase(selectedTimeoption) );
		//Assert.assertTrue(AppointmentTimeResult);
		
		
		if(AppointmentTime.equalsIgnoreCase(selectedTimeoption))
		{
			System.out.println("Appointment Time is correct");
			
		}
		else
		{
			System.out.println("Appointment Time is incorrect");
		}
		
		// Get the appointment symptom for verification

		String AppointmentSymptom = driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText();
		System.out.println(AppointmentSymptom + " : " +  enteredsymptom );
		
		//boolean AppointmentSymptomResult = (.equalsIgnoreCase(enteredsymptom));
		//Assert.assertTrue(AppointmentSymptomResult);
		
		if(AppointmentSymptom.equalsIgnoreCase(enteredsymptom) )
		{
			System.out.println("Appointment Symptom is correct");
			
		}
		else
		{
			System.out.println("Appointment Symptom is incorrect");
		}
		
		
		// Get the appointment provider detail for verification

		String AppointmentProvider = driver.findElement(By.xpath("//tbody/tr[1]/td[4]")).getText();
		System.out.println(AppointmentProvider + " : " +  trimProvider );
		
		//boolean AppointmentProviderResult = (AppointmentProvider.equalsIgnoreCase(trimProvider));
		//Assert.assertTrue(AppointmentProviderResult);
		
		if(AppointmentProvider.equalsIgnoreCase(trimProvider))
		{
			System.out.println("Appointment Provider is correct");
			
		}
		else
		{
			System.out.println("Appointment Provider is incorrect");
		}
		
			
	}
	
}
