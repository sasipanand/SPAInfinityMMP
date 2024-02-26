package org.iit.healthcare.patientmodule.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.iit.healthcare.mmp.util.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests1 extends BaseClass{
 
	//10 @Test tests 
	//@BeforeClass one time
	
	@Test
	public void bookAppointment()
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		//HashMap<String,String> actualHMap = new HashMap<String,String>();
		
	 
		driver.get(mmpProp.getProperty("patienturl"));
		driver.findElement(By.id("username")).sendKeys(mmpProp.getProperty("patientusername"));
		driver.findElement(By.id("password")).sendKeys(mmpProp.getProperty("patientpassword"));
		driver.findElement(By.name("submit")).click();
		String actual = driver.findElement(By.xpath("//h3[normalize-space()='ria1']")).getText().trim();
		String expected="ria1";
		Assert.assertEquals(actual,expected);
		
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		
		String doctorName="Smith";
		String description="Orthopedic";
		expectedHMap.put("doctor", doctorName);
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/parent::li/div/p[text()='Description:"+description+"']/ancestor::ul/following-sibling::button")).click();
	
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).click();
		
		
		String futureDate = getFutureDate(10);
		String dateArr[] = futureDate.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		String expectedYear = dateArr[2];
		String actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		
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
		
		
		
		
		driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		driver.switchTo().defaultContent();
		
		String symp="fever & flu";
		expectedHMap.put("appointment", symp);
		driver.findElement(By.id("sym")).sendKeys(symp);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		System.out.println("Expected HMAP" + expectedHMap);
		
	
	}
	public static String getFutureDate(int noofDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Date d =calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("d/MMMM/YYYY");
		String date = sdf.format(d);
		String dateArr[] = date.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		return date;
	}

}