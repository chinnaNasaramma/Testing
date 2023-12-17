package CommonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.io.FileWriteMode;

public class FunctionLibrary 
{
	
	
	private static final String Exp_Data = null;
	public static WebDriver driver;
	public static Properties conpro;
	private static int value;
	public static WebDriver startBrowser() throws FileNotFoundException, IOException 
	{ 
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.Properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			
		}else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
			
		}
		else
		{
			Reporter.log("browser value is not matching",true);
		}
		return driver;
		
		
	}
	public static void openUrl(WebDriver driver) 
	{
		driver.get(conpro.getProperty("Url"));
	}
	public static void WaitforElement(WebDriver driver,String Locator_Type,String Locator_Value,String Test_Data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Test_Data)));
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
		}
		if(Locator_Type.equalsIgnoreCase("name")) 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
		}
		if(Locator_Type.equalsIgnoreCase("Xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));
		}
		
		
		
	}
	public static void typeAction(WebDriver driver,String Locator_Type,String Locator_Value,String Test_Data) 
	{
		if(Locator_Type.equalsIgnoreCase("name"))
		{
		
			driver.findElement(By.name(Locator_Type)).clear();
		    driver.findElement(By.name(Locator_Type)).sendKeys(Test_Data);
		}
		if(Locator_Type.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(Locator_Type)).clear();
		    driver.findElement(By.id(Locator_Type)).sendKeys(Test_Data);
		}
		if(Locator_Type.equalsIgnoreCase("Xpath")) 
		{
			driver.findElement(By.xpath(Locator_Type)).clear();
		    driver.findElement(By.xpath(Locator_Type)).sendKeys(Test_Data);
		}
		
	}
	public static void clickAction(WebDriver driver,String Locator_Type,String Locator_Value)
	{
		if(Locator_Type.equalsIgnoreCase("Xpath")) 
		{
			driver.findElement(By.xpath(Locator_Value)).click();
		}
		if(Locator_Type.equalsIgnoreCase("name")) 
		{
			driver.findElement(By.xpath(Locator_Value)).click();
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
		   driver.findElement(By.id(Locator_Value)).sendKeys(Keys.ENTER);
		}
		
		
	}
	public static void validateTitle(WebDriver driver,String Exp_Title) 
	{
		String Actual_Title = driver.getTitle().trim();
		try {
		Assert.assertEquals(Actual_Title, Exp_Title.trim(),"title is not matching");
		}catch(Throwable t) 
		{
			System.out.println(t.getMessage());
			
		}
		
		
	}
	public static void closeBrowser(WebDriver driver) 
	{
		driver.quit();
	}
	public static String generateDate() 
	{
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyy_MM_DD_hh_mm_ss");
		return df.format(date);
		
		
	}
	public static void mouseClick(WebDriver driver) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("(//a[contains(normalize-space(),'Stock Items')])[2]"))).perform();
		ac.perform();
		Thread.sleep(2000);
		ac.moveToElement(driver.findElement(By.xpath("//li[@id='mi_a_stock_categories']//a[@href='a_stock_categorieslist.php'][normalize-space()='Stock Categories']"))).click().perform();
		ac.pause(2000).click().perform();
	}
	public static void categoryTable(WebDriver driver,String Test_Data) throws Throwable
	{
		if(!driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).isDisplayed());
			driver.findElement(By.xpath(conpro.getProperty("search_panel"))).click();
			driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).clear();
			driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).sendKeys(Test_Data);
			driver.findElement(By.xpath(conpro.getProperty("search_button"))).click();
			Thread.sleep(2000);
			String Act_Data =driver.findElement(By.xpath("//table[@class='ewTable ewTableSeparate']//span[@class='ewTableHeaderCaption'][normalize-space()='Category Name']")).getText();
			
			
			Reporter.log(Exp_Data+"   "+Act_Data,true);
			try {
			Assert.assertEquals(Exp_Data, Act_Data,"category name not matching");
			}catch(Throwable t) 
			{
				System.out.println(t.getMessage());
				
			}
	}


	
	public static void dropDownAction(WebDriver driver, String Locator_Type,String Locator_Value,String Test_Data)
	{
		if(Locator_Type.equalsIgnoreCase("id")) 
		{
			int in =Integer.parseInt(Test_Data);
			WebElement element = driver.findElement(By.id(Locator_Type));
			Select select =new Select(element);
			select.selectByIndex(value);
			
		}
		if(Locator_Type.equalsIgnoreCase("xpath")) 
		{
			int in =Integer.parseInt(Test_Data);
			WebElement element = driver.findElement(By.xpath(Locator_Type));
			Select select =new Select(element);
			select.selectByIndex(value);
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{

			int in =Integer.parseInt(Test_Data);
			WebElement element = driver.findElement(By.name(Locator_Type));
			Select select =new Select(element);
			select.selectByIndex(value);
		}
		
		}
	public static void captureStock(WebDriver driver,String Locator_Type, String Locator_Value) throws Throwable 
	{
		String StockNumber ="";
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			StockNumber =driver.findElement( By.name(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("xpath")) 
		{
			StockNumber =driver.findElement( By.xpath(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			StockNumber =driver.findElement( By.xpath(Locator_Value)).getAttribute("value");
		}
		FileWriter writer = new FileWriter("./CaptureData/stocknumber.txt");
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write(StockNumber);
		bw.flush();
		bw.close();
	}
	public static void stockTable(WebDriver driver) throws Throwable 
	{
		FileReader fr =new FileReader("./CaptureData/stocknumber.txt");
		BufferedReader br =new BufferedReader(fr);
		String Exp_Data =br.readLine();
		if(!driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).isDisplayed());
		driver.findElement(By.xpath(conpro.getProperty("search_panel"))).click();
		driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).sendKeys(Exp_Data);
		driver.findElement(By.xpath(conpro.getProperty("search_button"))).click();
		Thread.sleep(2000);
		String Act_Data =driver.findElement(By.xpath("")).getText();
		Reporter.log(Exp_Data+"  "+Act_Data);
		try {
		Assert.assertEquals(Exp_Data, Act_Data,"stock is not matching");
	}catch(Throwable t) 
		{
		System.out.println(t.getMessage());
		}
	}
	public static void captureSupplier(WebDriver driver,String Locator_Type,String Locator_Value) throws Throwable 
	{
		String supplierNumber="";
		if(Locator_Type.equalsIgnoreCase("name")) 
		{
			supplierNumber =driver.findElement(By.name(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			supplierNumber =driver.findElement(By.id(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			supplierNumber =driver.findElement(By.xpath(Locator_Value)).getAttribute("value");
		}
		FileWriter fw =new FileWriter("./CaptureData/stocknumber.txt");
		BufferedWriter bw =new BufferedWriter(fw);
		bw.write(supplierNumber);
		bw.flush();
		bw.close();
		
		
	}
	public static void supplierTable(WebDriver driver) throws Throwable 
	{
		FileReader fr = new FileReader("./CaptureData/stocknumber.txt");
		BufferedReader br = new BufferedReader(fr);
		String Exp_Data =br.readLine();
		if(!driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).isDisplayed());
		driver.findElement(By.xpath(conpro.getProperty("search_panel"))).click();
		driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).clear();
		driver.findElement(By.xpath(conpro.getProperty("search_Textbox"))).sendKeys(Exp_Data);
		driver.findElement(By.xpath(conpro.getProperty("search_button"))).click();
		Thread.sleep(2000);
		String Act_Data = driver.findElement(By.xpath("")).getText();
		Reporter.log(Exp_Data+""+Act_Data,true);
		try {
			Assert.assertEquals(Exp_Data, Act_Data,"supplier is not matching");
		}catch(Throwable t) 
		{
			System.out.println(t.getMessage());
			
		}
		}
		

	}




	
	
















