package DriverFactory;

import org.testng.annotations.Test;

public class AppTest
{
	@Test
	public void kickstart() throws Throwable 
	{
		DriverScript dp = new DriverScript();
		Thread.sleep(2000);
		dp.startTest();
	}

}
