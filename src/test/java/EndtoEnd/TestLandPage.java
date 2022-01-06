package EndtoEnd;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.Landingpage;
import resources.Base;

public class TestLandPage extends Base {
	public WebDriver driver;
    @Test(dataProvider="LandpageData")
    public void LandingPage(String from, String destination, String name, String userEmail, String Mobilenumber, String address, String text1) throws IOException, InterruptedException
    {
       
    	driver=InitializeDriver();
    	driver.get(prop.getProperty("url"));
    	Landingpage lp=new Landingpage(driver);
    	lp.getTwowayRadio().click();
    	lp.getOnewayRadio().click();
    	lp.getFromaddressbox().sendKeys(from);
    	Thread.sleep(2000L);
    	lp.getFromaddressbox().sendKeys(Keys.ENTER);
    	lp.getTOaddressBox().sendKeys(destination);
    	Thread.sleep(2000L);
    	lp.getTOaddressBox().sendKeys(Keys.ENTER);
    	
    	lp.getDatePickBox().click();
    	String month=lp.getcalenderhead().getText();
    	while(!month.contains("January"))
    	{
    		lp.getMonthnextbtn().click();
    	}
    	List<WebElement> dates=lp.getDatepicker();
    	for(int i=0;i<dates.size();i++)
    	{
    		String text=dates.get(i).getText();
    		if(text.contains("10"))
    		{
    			dates.get(i).click();
    		}
    	}
    	WebElement staticdropdown=lp.getTimepickup();
    	Select dropdown= new Select(staticdropdown);
    	dropdown.selectByVisibleText("8:15 PM ");
    	lp.getSelectCarbtn().click();
    	lp.getcarselectbtn().click();
    	lp.getuserName().sendKeys(name);
    	lp.getuserEmail().sendKeys(userEmail);
    	lp.getuserMobileNumber().sendKeys(Mobilenumber);
    	lp.getpickupaddress().sendKeys(address);
    	Thread.sleep(5000L);
    	lp.getpickupaddress().sendKeys(Keys.DOWN);
    	lp.getpickupaddress().sendKeys(Keys.ENTER);
    	Thread.sleep(3000L);
    	lp.getproceedbtn().click();
    	System.out.println(text1);
    	lp.getpay25percent().click();
    	lp.getpayproceed().click();
    	
    	
    }
    
    @AfterTest
    public void teardrops()
    {
    	driver.close();
    }
    
    
    @DataProvider(name="LandpageData")
    public Object[][] getData()
    {
    	Object[][] data=new Object[1][7];
    	//All are correct
    	data[0][0]="Eluru";
    	data[0][1]="Rajahmundry";
    	data[0][2]="raja";
    	data[0][3]="raja.siripireddy@gmail.com";
    	data[0][4]="9652287814";
    	data[0][5]="southern street";
    	data[0][6]="successfully loaded credentials";
    	return data;

    }

}
