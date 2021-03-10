package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets by unified category and check if the assets are displayed correctly

public class Product14_UnifiedCategory extends MainMethod{
	
	public static void category_product() throws InterruptedException, IOException
	{
		//click on the item button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(3000);
		//open the unified category drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[14]/div[2]/div")).click();
		Thread.sleep(3000);
		//get the list of unified category and select one category
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[14]/div[2]/div/div[2]/child::div"));
		System.out.println(list.size());
		String category="";
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			category=el.getText();
			if(category.equals("生活雑貨")==true)
			{
				System.out.println(category);
				el.click();
				break;
			}
		}
		//click on the filter button
		driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(3000);
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(3000);
		WebElement e1=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i"));
		Coordinates cor1=((Locatable)e1).getCoordinates();
		cor1.inViewPort();
		e1.click();
		//ASSERTION CONDITION
		//open the assets one by one and check if the unified category is equal to the selected category
		if(correctassetdiplayed(category)==true)
		{
			test.log(LogStatus.PASS, "選択したカテゴリのアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択したカテゴリのアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed(String category) throws InterruptedException 
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
			if(value.equals(category)==false && header.equals("統一カテゴリ")==false)
			{
				continue inner2;
			}
			else if(header.equals("統一カテゴリ")==true)
			{
				if(value.equals(category)==false)
				return false;
				else if(value.equals(category)==true)
				{
					driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
					continue inner1;
				}
			}
			}
		}
		}
		return true;
}
}
