package EndtoEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.Landingpage;
import pageObject.SigninPage;
import resources.Base;

public class TestSignIn extends Base {
	public WebDriver driver;
	
	
	/*@DataProvider (name="data1")
	public Object[][] dataprovidermethod() throws Exception {
		String filepath="‪C:\\Users\\wins\\Desktop\\Data.xlsx.xlsx";
		FileInputStream fis=new FileInputStream(filepath);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		/*int sheets=workbook.getNumberOfSheets();
		(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("Login"));
			{
				XSSFSheet sheet=workbook.getSheetAt(i);
							
			}workbook.close();
			
		}*/
		/*XSSFSheet sheet=workbook.getSheetAt(0);
		workbook.close();
		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[rowcount][1];
		for(int i=0;i<rowcount;i++){
		Map<Object, Object> map=new HashMap<Object,Object>();

		for(int j=0;j<colcount;j++){
		map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
		}
		data[i][0]=map;
				
		}

		return data;
		
	}*/
	
	
    @Test(dataProvider="SignInData")
    public void Signin(String username, String mobilenum, String Emailid, String password, String Repassword, String text ) throws IOException, InterruptedException
    {
       
    	driver=InitializeDriver();
    	driver.get(prop.getProperty("url"));
    	Landingpage lp=new Landingpage(driver);
    	lp.getsigninButton().click();
    	SigninPage si=new SigninPage(driver);
    	si.getRegisterButton().click();
    	si.getUserName().sendKeys(username);
    	si.getUserMobileNumber().sendKeys(mobilenum);
    	si.getUserEmail().sendKeys(Emailid);
    	si.getUserPassword().sendKeys(password);
    	si.getUserConfirmPassword().sendKeys(Repassword);
    	System.out.println(text);
    	si.getUserRegister().click();
    	
    	
    }
    
   
    
    
    
    @AfterTest
    public void teardrops()
    {
    	driver.close();
    }
    
    
    @DataProvider(name="SignInData")
    public Object[][] getData()
    {
    	Object[][] data=new Object[3][6];
    	//All are correct
    	data[0][0]="raja";
    	data[0][1]="9652287814";
    	data[0][2]="raja.siripireddy@gmail.com";
    	data[0][3]="raja1992";		
    	data[0][4]="raja1992";
    	data[0][5]="Correct credentials";
    	//usearname not correct
    	data[1][0]="+++";
    	data[1][1]="9652287814";
    	data[1][2]="raja.siripireddy@gmail.com";
    	data[1][3]="raja1992";		
    	data[1][4]="raja1992";
    	data[1][5]="username wrong";
    	//mob num not correct
    	data[2][0]="raja";
    	data[2][1]="9652287";
    	data[2][2]="raja.siripireddy@gmail.com";
    	data[2][3]="raja1992";		
    	data[2][4]="raja1992";
    	data[2][5]="mobile number wrong";
    	
    	return data;
    	
    }

}