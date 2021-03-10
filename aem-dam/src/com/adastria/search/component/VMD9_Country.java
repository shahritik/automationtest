package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets as per country and check if assets are displayed correctly
public class VMD9_Country extends MainMethod{
	
	static WebElement el;
	static String country;
	
	public static void countrytype() throws InterruptedException, IOException
	{
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[9]/div/div/div[1]")).click();
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("//input[@type='checkbox']"));
		//check mark the countries one by one
		System.out.println(list.size());
		for(int i=list.size()-5;i<list.size()-2;i++)
		{
			list.get(i).click();
			if(i!=51)
			{
				list.get(i-1).click();
			}
			el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[9]/div/div/div[2]/div/div["+(i-list.size()+6)+"]/div/label"));
			country=el.getText();
			System.out.println(country);
			Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
		//return true if the asset's country is same as the selected country or if no asset exists
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "選択した拠点のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "選択した拠点のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]")).click();
		Thread.sleep(1000);
		}
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
	}

	private static boolean correctassetdiplayed() throws InterruptedException 
	{
		List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(element.size());
		if(element.size()!=0)
		{
		inner1:for(int j=0;j<2;j++)
		{
			//check the metadata for each asset and see if the country value is equal to the selected value
			WebElement el1=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
			el1.click();
			Thread.sleep(1000);
			inner2:for(int k=0;;k++)
			{
			String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
			System.out.println(header);
			System.out.println(value);
			if(value.equals(country)==false && header.equals("拠点")==false)
			{
				continue inner2;
			}
			else if(value.equals(country)==false && header.equals("拠点")==true)
			{
				return false;
			}
			else if(value.equals(country)==true || header.equals("拠点")==true)
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
