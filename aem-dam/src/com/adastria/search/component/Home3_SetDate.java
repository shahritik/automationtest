package com.adastria.search.component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filtering the assets as per start and end date

public class Home3_SetDate extends MainMethod{
	
	//creating a date object
	static SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
	static Date date1,date2,date3,date4;
	
	//creating a function for checking the start date
	public static void startdate() throws InterruptedException, IOException, ParseException
	{
		//clicking the home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		//opening the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[8]/i")).click();
		Thread.sleep(2000);
		//opening the date drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[1]")).click();
		Thread.sleep(1000);
		//entering the start date as 2019-02-18
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/input")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/thead/tr[1]/th/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/thead/tr/th/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[3]/td[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[1]/td[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[4]/td[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div")).click();
		Thread.sleep(1000);
		//storing the start date in date1 variable
		date1=d.parse("2019-02-18");
		Thread.sleep(1000);
		//clicking anywhere the screen to close the calendar
		//driver.findElement(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div")).click();
		//Thread.sleep(1000);
		//click on the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		//close the side panel
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
		//check the date of the assets
		if(correctstartassetdisplayed()==true)
		{
			test.log(LogStatus.PASS, "最終更新日が(Start Date)以降の日付のアセットが表示される",test.addScreenCapture(screenshotPath));
		}
		else 
		{
			test.log(LogStatus.FAIL, "最終更新日が(Start Date)以降の日付のアセットが表示される",test.addScreenCapture(screenshotPath));
		}
	}
	public static void enddate() throws InterruptedException, IOException, ParseException
	{
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[1]")).click();
		Thread.sleep(1000);
		//entering the end date as 2019-11-22
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/div/input")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div")).click();
		Thread.sleep(1000);
		//storing the end date in date2
		date2=d.parse("2020-06-09");
		Thread.sleep(1000);
		//clicking on the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
		//checking the date of assets displayed
		if(correctendassetdisplayed()==true)
		{
			test.log(LogStatus.PASS, "最終更新日が(End Date)以前の日付のアセットが表示される",test.addScreenCapture(screenshotPath));
		}
		else 
		{
			test.log(LogStatus.FAIL, "最終更新日が(End Date)以前の日付のアセットが表示される",test.addScreenCapture(screenshotPath));
		}
	}
	public static void startenddate() throws InterruptedException, IOException, ParseException
	{
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/input")).click();
		Thread.sleep(1000);
		//selecting the start date=2019-02-18 and end date=2019-11-22 
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/thead/tr[1]/th/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/thead/tr/th/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[3]/td[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[1]/td[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[4]/td[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div")).click();
		Thread.sleep(1000);
		date3=d.parse("2019-02-18");
		date4=d.parse("2020-06-09");
		Thread.sleep(1000);
		//click on the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
		//check the date of all the assets displayed
		if(correctendandstartassetdisplayed()==true)
		{
			test.log(LogStatus.PASS, "最終更新日が(Start Date)から(End Date)の範囲のアセットが表示される。",test.addScreenCapture(screenshotPath));
		}
		else 
		{
			test.log(LogStatus.FAIL, "最終更新日が(Start Date)から(End Date)の範囲のアセットが表示される。",test.addScreenCapture(screenshotPath));
		}
	}
	public static boolean correctstartassetdisplayed() throws InterruptedException, ParseException
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(list.size());
		for(int i=0;i<2;i++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/a/img"));
			el.click();
			Thread.sleep(1000);
			WebElement el1= driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/div/p"));

			Date date_asset = d.parse(el1.getText());
			if(date_asset.compareTo(date1)<0)
			{
				return false;
			}
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
			Thread.sleep(1000);
		}
		return true;
	}
	public static boolean correctendassetdisplayed() throws InterruptedException, ParseException
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(list.size());
		for(int i=0;i<2;i++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/a/img"));
			el.click();
			Thread.sleep(1000);
			WebElement el1= driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/div/p"));

			Date date_asset = d.parse(el1.getText());
			if(date_asset.compareTo(date2)>0)
			{
				return false;
			}
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
			Thread.sleep(1000);
		}
		return true;
	}
	public static boolean correctendandstartassetdisplayed() throws InterruptedException, ParseException
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(list.size());
		for(int i=0;i<2;i++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/a/img"));
			el.click();
			Thread.sleep(1000);
			WebElement el1= driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/div/p"));
			Date date_asset = d.parse(el1.getText());
			if(date_asset.compareTo(date3)<0 && date_asset.compareTo(date4)>0)
			{
				return false;
			}
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
			Thread.sleep(1000);
		}
		return true;
	}
}
