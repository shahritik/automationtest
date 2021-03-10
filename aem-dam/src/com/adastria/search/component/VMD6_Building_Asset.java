package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets by building and check if correct assets are displayed
public class VMD6_Building_Asset extends MainMethod{
	
	public static void asset_building() throws InterruptedException, IOException
	{
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div")).click();
		Thread.sleep(2000);
		//selecting the branch
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div/div[2]/child::div"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div/div[2]/div["+(i+1)+"]"));
			if(el.getText().equals("関東第2支店")==true)
			{
				el.click();
				break;
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div")).click();
		//selecting the area
		List<WebElement> list1 = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/child::div"));
		System.out.println(list1.size());
		for(int i=0;i<list1.size();i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/div["+(i+1)+"]"));
			Thread.sleep(2000);
			if(el.getText().equals("坂田良子エリア")==true)
			{
				el.click();
				break;
			}
		}
		Thread.sleep(2000);
		//selecting the building
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[6]/div/div")).click();
		List<WebElement> list2 = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[6]/div/div/div[2]/child::div"));
		System.out.println(list2.size());
		for(int i=0;i<list2.size();i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[6]/div/div/div[2]/div["+(i+1)+"]"));
			Thread.sleep(2000);
			if(el.getText().equals("ららぽーと富士見")==true)
			{
				el.click();
				break;
			}
		}
		//click on filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(2000);
		//take the screenshot of the screen showing filtered assets
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(2000);
		//return true if the asset's building is correctly displayed or if no asset exists
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "選択した館のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択した館のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed() throws InterruptedException 
	{
		List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(element.size());
		inner1:for(int j=0;j<2;j++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
			el.click();
			Thread.sleep(2000);
			inner2:for(int k=0;;k++)
			{
			String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
			System.out.println(header);
			System.out.println(value);
			if(value.equals("ららぽーと富士見")==false && header.equals("館")==false)
			{
				continue inner2;
			}
			else if(value.equals("ららぽーと富士見")==false && header.equals("館")==true)
			{
				return false;
			}
			else if (value.equals("ららぽーと富士見")==true && header.equals("館")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				continue inner1;
			}
			}
		}
		return true;
}
}
