package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class Product5_Category_Asset extends MainMethod{
	
	//filter the assets by category and check if correct assets are displayed
	
	public static void category_asset() throws InterruptedException, IOException
	{
		//clicking on item
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//clicking on the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]")).click();
		Thread.sleep(3000);
		//clicking on the brand drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div")).click();
		Thread.sleep(3000);
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div/div[2]/child::div"));
		System.out.println(list.size());
		//selecting the brand Andemiu
		for(int i=0;i<list.size();i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div/div[2]/div["+(i+1)+"]"));
			if(el.getText().equals("Andemiu")==true)
			{
				el.click();
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div")).click();
		List<WebElement> list1 = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/child::div"));
		System.out.println(list1.size());
		//selecting the category as レディース
		for(int i=0;i<list1.size();i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/div["+(i+1)+"]"));
			if(el.getText().equals("レディース")==true)
			{
				el.click();
			}
		}
		//clicking the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(3000);
		//taking the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(3000);
		//ASSERTION CONDITION
		//return true if the asset's category is correctly displayed or if no asset exists
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "選択したカテゴリのアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択したカテゴリのアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed() throws InterruptedException 
	{
		//collating the list of assets that are displayed
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
			if(value.equals("レディース")==false && header.equals("カテゴリ")==false)
			{
				continue inner2;
			}
			else if(value.equals("レディース")==false && header.equals("カテゴリ")==true)
			{
				return false;
			}
			else if (value.equals("レディース")==true && header.equals("カテゴリ")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				continue inner1;
			}
			}
		}
		return true;
}
}
