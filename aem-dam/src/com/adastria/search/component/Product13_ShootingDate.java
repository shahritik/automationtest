package com.adastria.search.component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

public class Product13_ShootingDate extends MainMethod{
	
	//filter the assets by shooting date and check if the assets are displayed correctly
	
	static SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
	static Date date3,date4;
	
	public static void startenddate() throws InterruptedException, IOException, ParseException
	{
		//click on the item button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(2000);
		//open the shooting date drop down 
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[13]/div/div/div[1]")).click();
		Thread.sleep(3000);
		//enter the range of shooting date
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[13]/div/div/div[2]/div[1]/div/div/input")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[13]/div/div/div[2]/div[1]/div/div/div/table/thead/tr[1]/th/span[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[13]/div/div/div[2]/div[1]/div/div/div/table/thead/tr/th/span[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[13]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[3]/td[3]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[13]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[1]/td[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[13]/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[4]/td[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[13]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[3]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div")).click();
		Thread.sleep(3000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(3000);
		date3=d.parse("2019-02-18");
		date4=d.parse("2020-06-09");
		Thread.sleep(3000);
		//click on the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(3000);
		WebElement e1=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i"));
		Coordinates cor1=((Locatable)e1).getCoordinates();
		cor1.inViewPort();
		e1.click();
		Thread.sleep(3000);
		//ASSERTION CONDITION
		//open the assets one by one and check if the shooting date is in between the range that is entered
		if(correctendandstartassetdisplayed()==true)
		{
			test.log(LogStatus.PASS, "指定された撮影日のアセットが表示される",test.addScreenCapture(screenshotPath));
		}
		else 
		{
			test.log(LogStatus.FAIL, "指定された撮影日のアセットが表示される",test.addScreenCapture(screenshotPath));
		}
	}
	public static boolean correctendandstartassetdisplayed() throws InterruptedException, ParseException
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
			if(header.equals("撮影日")==false)
			{
				continue inner2;
			}
			else if(header.equals("撮影日")==true)
			{
				Date value1=d.parse(value);
				if(value1.compareTo(date3)<0 || value1.compareTo(date4)>0)
				return false;
				else if(value1.compareTo(date3)>=0 && value1.compareTo(date4)<=0)
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
