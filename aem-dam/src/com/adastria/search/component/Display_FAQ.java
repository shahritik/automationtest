package com.adastria.search.component;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

//open the FAQ page and check if the header and footer are displayed are correctly

public class Display_FAQ extends MainMethod{

	public static void displayfaq() throws InterruptedException, IOException
	{
		//click on the FAQ button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[4]")).click();
		Thread.sleep(1000);
		//take the screenshot of the FAQ page
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		//get the value of header of FAQ page
		String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1")).getText();
		Thread.sleep(500);
		//get the value of footer of FAQ page
		String footer=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[3]/footer/div")).getText();
		Thread.sleep(500);
		//ASSERTION CONDITION
		//check if the header = "FAQ" and footer = "© Adastria Co., Ltd. All rights reserved."
		if(header.equals("FAQ")==true && footer.equals("© Adastria Co., Ltd. All rights reserved.")==true)
		{
			test.log(LogStatus.PASS, "FAQが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "FAQが表示される", test.addScreenCapture(screenshotPath));
		}
	}
}
