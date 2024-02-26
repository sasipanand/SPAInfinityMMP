package org.iit.healthcare.mmp.pages;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.iit.healthcare.mmp.util.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointmentPage {
	protected WebDriver driver;// We are declaring or initializing the driver as global variable here int i; so that it can be used anywhere in the class
	private By moduleTab = By.xpath("//span[normalize-space()='Schedule Appointment']");// storing the locator info in a  variable. i will declare it as a global variable and give access modifier rights(private, protected,...)
	private By appointmentButton = By.xpath("//input[@value='Create new appointment']");
	private By datePickerID = By.id("datepicker");
	// below is the constructor it is the method which will be called first. Instead of hardcoding  we pass value to constructor so it is initialized 
	// and we can anywhere in any method in this class 
	public  ScheduleAppointmentPage(WebDriver driver) // the driver in the argument is driver value that is chrome, firefox what we pass when we call this constructor
	{
		this.driver = driver;//assignment of the driver variable created in this class happens. here we are assigning the value to the driver variable like i = 10 here driver value will be chrome/firefox/edge based on the browser passed when this constructor is called 
		// We assigning here and not assigning at the global level when we declare the driver bcos the driver value is passed  by the other class who calls this method in that class . it is passed thru the parameter in this constructor and assigned to the driver variable declared 
		//in this class that is left hand side driver in blue 
	}
	
	public HashMap<String, String> bookAppointment()
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		driver.findElement(moduleTab).click();
		driver.findElement(appointmentButton).click();
		
		String doctorName="Smith";
		String description="Orthopedic";
		expectedHMap.put("doctor", doctorName);
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/parent::li/div/p[text()='Description:"+description+"']/ancestor::ul/following-sibling::button")).click();
	
		driver.switchTo().frame("myframe");
		driver.findElement(datePickerID).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		Random rand = new Random();
		String randomDate = rand.nextInt(29)+"";
		driver.findElement(By.linkText(randomDate)).click();
		expectedHMap.put("date",driver.findElement(By.id("datepicker")).getAttribute("value"));
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		String appointmentTime="11Am";
		timeSelect.selectByVisibleText(appointmentTime);
		expectedHMap.put("time", appointmentTime);
		
		
		
		driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		driver.switchTo().defaultContent();
		
		String symp="fever & flu";
		expectedHMap.put("appointment", symp);
		driver.findElement(By.id("sym")).sendKeys(symp);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		System.out.println("Expected HMAP" + expectedHMap);
		
		return expectedHMap;
		
	}
	
	public HashMap<String,String> bookAppoinment(int noofDays)
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		driver.findElement(moduleTab).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		
		String doctorName="Smith";
		String description="Orthopedic";
		expectedHMap.put("doctor", doctorName);
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/parent::li/div/p[text()='Description:"+description+"']/ancestor::ul/following-sibling::button")).click();
	
		driver.switchTo().frame("myframe");
		driver.findElement(datePickerID).click();
		 
		String futureDate = AppLibrary.getFutureDate(noofDays);
		String dateArr[] = futureDate.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		
		String expectedYear = dateArr[2];//2025
		String actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();//2025
		
		while(!(actualYear.equals(expectedYear)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		}
		String expectedMonth = dateArr[1];//April
		String actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();//January
		
		while(!(actualMonth.equals(expectedMonth)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		}
		
		driver.findElement(By.linkText(dateArr[0])).click();
		expectedHMap.put("date",driver.findElement(By.id("datepicker")).getAttribute("value"));
		
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		String appointmentTime="11Am";
		timeSelect.selectByVisibleText(appointmentTime);
		expectedHMap.put("time", appointmentTime);
		
	//	Duration d = new Duration(30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
		
		
		driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		driver.switchTo().defaultContent();
		
		String symp="fever & flu";
		expectedHMap.put("appointment", symp);
		driver.findElement(By.id("sym")).sendKeys(symp);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		System.out.println("Expected HMAP" + expectedHMap);
		
		return expectedHMap;
	}
	
	 

	

public static String getFutureDate(int noofDays)
{
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DAY_OF_MONTH, noofDays);
	java.util.Date d =calendar.getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/YYYY");
	String date = sdf.format(d);
	String dateArr[] = date.split("/");
	System.out.println(dateArr[0]);
	System.out.println(dateArr[1]);
	System.out.println(dateArr[2]);
	return date;
}

	
}
