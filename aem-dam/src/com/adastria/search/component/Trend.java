package com.adastria.search.component;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

public class Trend extends MainMenu_Test{
	
	public static void trend() throws InterruptedException, IOException
	{
		//click on Trend button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[5]")).click();
		WaitForPage.waitForLoad(driver);
		//checking the URL
		String url=driver.getCurrentUrl();
		if(url.contains("trend.html")==true)
		{
			test.log(LogStatus.PASS, "トレンドページが表示されます", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "トレンドページが表示されます", test.addScreenCapture(screenshotPath));
		}
	}
}
