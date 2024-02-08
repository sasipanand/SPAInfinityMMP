package org.iit.healthcare.patientmodule.tests;

import java.util.HashMap;
import org.iit.healthcare.mmp.pages.ScheduleAppointmentPage;
import org.iit.healthcare.mmp.util.BaseClass;
import org.iit.healthcare.mmp.util.MMPLib;
import org.iit.healthcare.mmp.pages.HomePage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



// if there is large data to be used and if data can't be hard coded even in the test scripts we can read it from JSON - Java script object notation as whenever u read a file
// it is copied into the memory so if it is large then we will get memory error for xml file, property files, xlsx,xls,DB, CSV so go for JSON
// if you create an xml file of a program it will help to invoke the program . For example if you create an xml for this java program it will help to invoke this java program/class
//Create each xml file for each class then in a suite xml call all these xml's
//hybrid framework can be a combination of modular and/ data driven and/keyword driven framework
public class ScheduleAppointmentDatePicker_Tests extends BaseClass{
	@Parameters({"username", "password"})// we have the values for the var's user name and password 
	
	@Test
	public void scheduleAppointmentTest(String username, String password)// there is no driver.findelement bcos logic must be in the method 
	{
		
	    MMPLib mmpLib = new MMPLib(driver); // if we pass an argument while creating an object of the class we can access the arg anywhere in the class 
	    //whereas if we pass the driver as arg in a method we need to pass this arg everytime we call the method. 
	    //Also when we pass an arg in the class it creates an constructor in the class we creating an object of that is MMPLib 
	
	    mmpLib.launchBrowser(mmpProp.getProperty("patienturl"));
	   HomePage hPage = mmpLib.loginValidUser(username, password);
	    String actual = hPage.getPatientName(mmpProp.getProperty("patientusername"));
		String expected=mmpProp.getProperty("patientusername");
		SoftAssert sa = new SoftAssert();//if more than one assert use soft assert whole program will be executed all assert will be executed irrespective if program has issue or not  then
		// true/passed will be displayed if all assert pass else if one assert fails then failed will be displayed
		sa.assertEquals(actual,expected);
		
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		
		
		HashMap<String,String> expectedHMap = sPage.bookAppoinment(40);
		HashMap<String,String> actualHMap = hPage.fetchPatientData();
		System.out.println(expectedHMap);
		System.out.println(actualHMap);
		
		sa.assertEquals(actualHMap,expectedHMap);
		
		sa.assertAll();
	}
}
	

	
	
	
	/*public HashMap<String, String> bookAppointment()
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		
		String doctorName="Smith";
		String description="Orthopedic";
		expectedHMap.put("doctor", doctorName);
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/parent::li/div/p[text()='Description:"+description+"']/ancestor::ul/following-sibling::button")).click();
	
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		Random rand = new Random();
		String randomDate = rand.nextInt(29)+"";
		driver.findElement(By.linkText(randomDate)).click();// To get the text in a element defined with anchor tag <a text a> we need to use linktext not text()
		expectedHMap.put("date",driver.findElement(By.id("datepicker")).getAttribute("value"));
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		String appointmentTime="11Am";
		timeSelect.selectByVisibleText(appointmentTime);// if index number is not there for drop down values when we inspect you can use selectByVisibleText  
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
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		
		String doctorName="Smith";
		String description="Orthopedic";
		expectedHMap.put("doctor", doctorName);
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/parent::li/div/p[text()='Description:"+description+"']/ancestor::ul/following-sibling::button")).click();
	
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).click();
		 
		String futureDate = getFutureDate(noofDays);
		String dateArr[] = futureDate.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		
		String expectedYear = dateArr[2];//2025
		String actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();//2025 u are getting to make sure 
		
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
	
	public HashMap<String,String> fetchPatientData(){
		
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("doctor", driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]")).getText().trim());
		actualHMap.put("appointment", driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText().trim());
		actualHMap.put("time", driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText().trim());
		actualHMap.put("date", driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).getText().trim());
		return actualHMap;
		
	}
	public static String getFutureDate(int noofDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noofDays);
		Date d =calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/YYYY");
		String date = sdf.format(d);
		String dateArr[] = date.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		return date;
	}

}*/