package com.adastria.search.component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class VMD2_ReportWeek extends MainMethod{
	
	//create a date object
	static SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
	static Date date1,date2,date3,date4;
	
	public static void reportweek_vmd() throws InterruptedException, ParseException, IOException
	{
		//click on VMD Screen
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[2]")).click();
		Thread.sleep(1000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(1000);
		//open the reporting week drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]")).click();
		Thread.sleep(1000);
		//enter the initial and end date
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div[1]/div/div/input")).sendKeys("1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[4]/td[2]")).click();
		Thread.sleep(1000);
		date1=d.parse("2019-01-21");
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div/div/input")).sendKeys("1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[5]/td[2]")).click();
		Thread.sleep(1000);
		date2=d.parse("2019-01-28");
		//click on the filter button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		//take the screenshot of the assets shown after filtering
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(1000);
		//close the side panel
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		//open all the assets one by one and check if the reporting date is between the initial and the final date
		if(correctassetdisplayed()==true)
		{
			test.log(LogStatus.PASS, "選択した報告週のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択した報告週のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	public static boolean correctassetdisplayed() throws InterruptedException, ParseException
	{
		//getting the list of total assets which are displayed on the screen
		List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(element.size());
		inner1:for(int j=0;j<element.size();j++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
			el.click();
			Thread.sleep(1000);
			//getting the list of metadata
			List<WebElement> element1 = driver.findElements(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/child::div"));
			int l=element1.size();
			//if there is no metadata return false
			if(l==0)
			{
				return false;
			}
			System.out.print(l);
			inner2:for(int k=0;k<l;k++)
			{
				//checking all the metadata one by one and extracting the reporting date
			String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
			value=value.substring(0,10);
			System.out.println(header);
			System.out.println(value);
			if(header.equals("APHOTO報告日時")==false)
			{
				continue inner2;
			}
			else if(header.equals("APHOTO報告日時")==true)
			{
				Date value1=d.parse(value);
				if(value1.compareTo(date1)<0 || value1.compareTo(date2)>0)
				return false;
				else if(value1.compareTo(date1)>=0 && value1.compareTo(date2)<=0)
				{
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				continue inner1;
				}
			}
			}
		}
		return true;
	}
}
