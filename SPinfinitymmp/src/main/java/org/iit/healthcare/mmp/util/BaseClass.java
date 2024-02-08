package org.iit.healthcare.mmp.util;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected WebDriver driver;// if it is in blue color then the var is a global var. public,private,default,protected are access modifiers
	protected Properties globalProp;// protected can be accessed within the package and other package in a project by subclassing that is extending. Base calss is in src/maim/java. the var in this can be accessed by package in src/test/java by using extends keywords
	protected Properties mmpProp;// private only within the class not in any other class within the package
	String browserType;// default can be accessed in another class of the same package not a class of different package 
	String environment;
	ProjectConfiguration pConfig;

	@BeforeTest // Will be executed before all class on time that is package level
	public void loadProperties() throws IOException
	{
		pConfig = new ProjectConfiguration();
		globalProp = pConfig.loadProperties("config/mmpglobal.properties");
		browserType = globalProp.getProperty("browserType");
		environment= globalProp.getProperty("environment");
	}
	@BeforeClass // Will be executed before each class in a package. @BeforeMethod will be executed before each method in a 
	public void instantiateDriver() throws IOException  
	{

		if(environment.equals("qa") &&browserType.equals("chrome"))
		{
			pConfig = new ProjectConfiguration();
			mmpProp = pConfig.loadProperties("config/qa_mmp.properties");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

		}
		else if(environment.equals("dev") &&browserType.equals("chrome"))
		{
			pConfig = new ProjectConfiguration();
			mmpProp = pConfig.loadProperties("config/dev_mmp.properties");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

		}
	}
	@AfterClass
	public void tearDown() {

		driver.quit();
	}


}
