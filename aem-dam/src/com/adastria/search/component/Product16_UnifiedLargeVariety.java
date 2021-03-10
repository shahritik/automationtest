package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets by unified large variety and check if the assets are displayed correctly

public class Product16_UnifiedLargeVariety extends MainMethod{
	
	static String largevariety="";
	
	public static void largevariety_assets() throws InterruptedException, IOException
	{
		//click on the item button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(3000);
		//open the large variety drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[16]/div/div")).click();
		Thread.sleep(3000);
		//get the list of large variety and select one large variety
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[16]/div/div/div[2]/child::div"));
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			largevariety=el.getText();
			if(largevariety.equals("ニット")==true)
			{
				el.click();
				break;
			}
		}
		//click on the filter button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(3000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		//ASSERTION CONDITION
		//open the assets one by one and check if the large variety is equal to selected large variety
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "選択した統一大品種のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択した統一大品種のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed() throws InterruptedException 
	{
		List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(element.size());
		if(element.size()!=0)
		{
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
			if(value.equals(largevariety)==false && header.equals("統一大品種")==false)
			{
				continue inner2;
			}
			else if(value.equals(largevariety)==false && header.equals("統一大品種")==true)
			{
				return false;
			}
			else if(value.equals(largevariety)==true && header.equals("統一大品種")==true)
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
