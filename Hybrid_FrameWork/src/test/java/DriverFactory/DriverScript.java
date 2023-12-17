package DriverFactory;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import CommonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript
{
	public WebDriver driver;
	String inputpath ="./FileInput/TestCase.xlsx";
	String outputpath ="./FileOutPut/HybridResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	String TestCases ="MasterTestCases";
	public void startTest() throws Throwable 
	{
		String Module_Status ="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowcount(TestCases);i++)
		{
			
			String Execution_Status =xl.getcelldata(TestCases, i, 2);
			if(Execution_Status.equalsIgnoreCase("Y")) 
				
			{
				String TCModule = xl.getcelldata(TestCases, i, 1);
				for(int j = 1;j<=xl.rowcount(TCModule);j++) 
				{
					
					String Descripition =xl.getcelldata(TCModule, j, 0);
					String object_type =xl.getcelldata(TCModule, j, 1);
					String Locator_Type =xl.getcelldata(TCModule, j, 2);
					String Locator_Value =xl.getcelldata(TCModule, j, 3);
					String Test_Data =xl.getcelldata(TCModule, j, 4);
		           try {
		        	   if(object_type.equalsIgnoreCase("startBrowser"))
		        	   {
		        		   driver =FunctionLibrary.startBrowser();
		        	   }
		        	   if(object_type.equalsIgnoreCase("openUrl")) 
		        	   {
		        		   FunctionLibrary.openUrl(driver);
		        	   }
		        	   if(object_type.equalsIgnoreCase("WaitforElement"))
		        	   {
		        		   FunctionLibrary.WaitforElement(driver, Locator_Type, Locator_Value, Test_Data);
		        	   }
		        	   if(object_type.equalsIgnoreCase("typeAction")) 
		        	   {
		        		   
		        		   FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
		        		   
		        	   }
		        	   if(object_type.equalsIgnoreCase("clickAction")) 
		        	   {
		        		 FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value); 
		        		 
		        	   }
		        	   if(object_type.equalsIgnoreCase("validateTitle"))
		        	   {
		        		   FunctionLibrary.validateTitle(driver, Test_Data);
		        	   }
		        	   if(object_type.equalsIgnoreCase("closeBrowser")) 
		        	   {
		        		   FunctionLibrary.closeBrowser(driver);
		        	   }
		        	   if(object_type.equalsIgnoreCase("mouseClick"))
		        	   {
		        		   FunctionLibrary.mouseClick(driver);
		        		   
		        	   }
		        	   if(object_type.equalsIgnoreCase("categoryTable")) 
		        	   {
		        		   FunctionLibrary.categoryTable(driver, Test_Data);
		        	   
		        		   
		        	   }
		        	   if(object_type.equalsIgnoreCase("dropDownAction"))
		        	   {
		        		 FunctionLibrary.dropDownAction(driver, Locator_Type, Locator_Value, Test_Data); 
		        		 
		        	   }
		        	   if(object_type.equalsIgnoreCase("captureStock"))
		        	   {
		        		   FunctionLibrary.captureStock(driver, Locator_Type, Locator_Value);
		        		   
		        	   }
		        	   if(object_type.equalsIgnoreCase("stockTable"))
		        	   {
		        		   FunctionLibrary.stockTable(driver);
		        	   }
		        	   if(object_type.equalsIgnoreCase("captureSupplier")) 
		        	   {
		        		   FunctionLibrary.captureSupplier(driver, Locator_Type, Locator_Value);
		        	   }
		        	   if(object_type.equalsIgnoreCase("supplierTable")) 
		        	   {
		        		   FunctionLibrary.stockTable(driver);
		        	   }
		        	   xl.setcelldata(TCModule, j, 5,"pass",outputpath);
		        	   Module_Status ="True";
		        	   
		           }
		           catch(Exception e)
		           {
		        	   xl.setcelldata(TCModule, j, 5, "pass", outputpath);
		        	   Module_Status ="True";
		        	    System.out.println(e.getMessage());
		           }
		           if(Module_Status.equalsIgnoreCase("True")) 
		           {
		        	   xl.setcelldata(TestCases, i, 3, "pass", outputpath);
		           }
		           if(Module_Status.equalsIgnoreCase("False")) 
		           {
		        	   xl.setcelldata(TestCases, i, 3,"Fail",outputpath);
		           }
		          
				}
				
		    }
			else 
			{
				xl.setcelldata(TestCases, i, 3,"Blocked", outputpath);
				
			}
			
			
		}
		
		
	}
	

}
