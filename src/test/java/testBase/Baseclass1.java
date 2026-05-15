package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Baseclass1 {
	public Properties p;
	public Logger logger;
	
		public WebDriver driver;
		@BeforeClass(groups= {"Sanity","Master","Regression"})
		@Parameters({"os","browser"})
		public void setup(String os, String br) throws IOException
		{
			//loading config.proprties file
			FileReader file = new FileReader("./src/test/resources/config.properties");
			p = new Properties();
			p.load(file);
			
		    logger = LogManager.getLogger(this.getClass());   //LOG4J2
		    switch(br.toLowerCase())
		    {
	        case "chrome":
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	            break;

	        case "edge":
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	            break;

	        case "firefox":
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver();
	            break;

	        default:
	            System.out.println("Invalid browser");
	            return;
	    }
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();
		}
		@AfterClass(groups= {"Sanity","Master","Regression"})
		public void tearDown()
		{
			driver.close();
		}
		public String randomeString()
		{
		    String generatedString = RandomStringUtils.randomAlphabetic(5);

		    return generatedString;
		}
		public String randomeNumber()
		{
		    String generatedString = RandomStringUtils.randomNumeric(10);

		    return generatedString;
		}
		
		public String randomeAlphaNumeric()
		{
		    String generatedString = RandomStringUtils.randomAlphabetic(3);

		    String generatedNumber = RandomStringUtils.randomNumeric(3);

		    return (generatedString + "@" + generatedNumber);
		}
		
		
		
		
		

	}


