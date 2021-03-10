package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets as per year and check if the assets are correctly displayed
public class Product2_Year extends MainMethod{
	
	public static void year_product() throws InterruptedException, IOException
	{
		//click on the item 
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(3000);
		//open the year drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div[2]/div")).click();
		Thread.sleep(3000);
		//get a list of all the options shown and click on the year "2019"
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div[2]/div/div[2]/child::div"));
		System.out.println(list.size());
		String year="";
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			year=el.getText();
			if(year.equals("2019")==true)
			{
				el.click();
				break;
			}
		}
		//click on filter button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(3000);
		//take the screenshot of the assets displayed
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		//open all the assets one by one and check if the year is equal to 2019
		if(correctassetdiplayed(year)==true)
		{
			test.log(LogStatus.PASS, "選択した年度のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択した年度のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed(String year) throws InterruptedException 
	{
		List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(element.size());
		inner1:for(int j=0;j<2;j++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
			el.click();
			Thread.sleep(3000);
			inner2:for(int k=0;;k++)
			{
			String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
			System.out.println(header);
			System.out.println(value);
			if(value.equals(year)==false && header.equals("年度")==false)
			{
				continue inner2;
			}
			else if(value.equals(year)==false && header.equals("年度")==true)
			{
				return false;
			}
			else if(value.equals(year)==true && header.equals("年度")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				continue inner1;
			}
			}
		}
		return true;
}
}
