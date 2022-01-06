package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	 public WebDriver driver;
	 public Properties prop;
	 
     public WebDriver InitializeDriver() throws IOException {
    	 
	 prop=new Properties();
	FileInputStream fis=new FileInputStream(".\\src\\main\\java\\resources\\Data.propperties");
	prop.load(fis);
	String browserName=prop.getProperty("browser");
	
	if(browserName.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver",".\\src\\main\\java\\resources\\chromedriver.exe");
		 driver=new ChromeDriver();
	}
	
	else if(browserName.equals("IE"))
	{
		System.setProperty("webdriver.IE.driver",  System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
		 driver=new InternetExplorerDriver();
		
	}
	
	else if(browserName.equals("mozillafirefix"))
	{
		System.setProperty("webdriver.gecko.driver",  System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
		 driver=new FirefoxDriver();
		
	}
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	return driver;
	
     }
     
     public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException 
 	{
 		TakesScreenshot ts=(TakesScreenshot)driver;
 		File source= ts.getScreenshotAs(OutputType.FILE);
 		Date d=new Date();
 		SimpleDateFormat sdf=new SimpleDateFormat();
 		String timestamp=sdf.format(d);
 	
 		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+timestamp+testCaseName+".png";
 		FileUtils.copyFile(source,new File(destinationFile));
 		return destinationFile;
 		
 		
 	}

}
