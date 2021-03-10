package com.adastria.search.component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//checking the display order (Ascending, Descending) of the assets

public class Home4_DisplayOrder extends MainMethod{
	//creating a Date object
	static SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
	//creating a string array of size 6
	static String d_array[]=new String[6];
	public static void vieworder() throws InterruptedException, IOException, ParseException
	{
		//click on the home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(4000);
		//click on the drop down for sorting by "Last Update Date" or "Size"
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div[1]/i")).click();
		Thread.sleep(1000);
		//create a list of all the options shown in the drop down
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div[1]/div[2]/child::div"));
		//print the size of the list
		System.out.println(list.size());
		outer:for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			//select the option of sorting by "Last Update Date"
			if(el.getText().equals("最終更新日"))
			{
				//click the option for "Last Update Date"
				el.click();
				Thread.sleep(1000);
				//click the 2nd tab for "Ascending" and "Descending"
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div[2]/i")).click();
				Thread.sleep(1000);
				//select the descending option
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div[2]/div[2]/div[2]")).click();
				//take screenshot
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				Thread.sleep(1000);
				//open the assets one by one and check the date
				//date for current asset should be less than or equal to the previous asset
				if(checkdescending()==true)
				{
					test.log(LogStatus.PASS,"検索結果が最終更新日降順で表示される", test.addScreenCapture(screenshotPath));
				}
				else
				{
					test.log(LogStatus.FAIL, "検索結果が最終更新日降順で表示される", test.addScreenCapture(screenshotPath));
				}
				Thread.sleep(1000);
				//click on the ascending button to sort all the assets in Ascending order
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div[2]/i")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div[2]/div[2]/div[1]")).click();
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				Thread.sleep(1000);
				//open the assets one by one and check the date
				//date for current asset should be greater than or equal to the previous asset
				if(checkascending()==true)
				{
					test.log(LogStatus.PASS,"検索結果が最終更新日昇順で表示される", test.addScreenCapture(screenshotPath));
				}
				else
				{
					test.log(LogStatus.FAIL, "検索結果が最終更新日昇順で表示される", test.addScreenCapture(screenshotPath));
				}
				Thread.sleep(1000);
				break outer;
			}
			else
			{
				continue outer;
			}
		}
	}

	private static boolean checkascending() throws ParseException, InterruptedException {
			List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
			System.out.println(list.size());
			Date date_asset_previous=d.parse("1856-01-01");
			for(int i=0;i<2;i++)
			{
				WebElement el=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/a/img"));
				el.click();
				Thread.sleep(1000);
				WebElement el1= driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/div/p"));
				Date date_asset = d.parse(el1.getText());
				System.out.println(date_asset);
				if(date_asset.compareTo(date_asset_previous)<0)
				{
					return false;
				}
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				Thread.sleep(1000);
				date_asset_previous=date_asset;
			}
			return true;
		}

	private static boolean checkdescending() throws ParseException, InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(list.size());
		Date date_asset_previous=d.parse("2100-01-01");
		for(int i=0;i<2;i++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/a/img"));
			el.click();
			Thread.sleep(1000);
			WebElement el1= driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/div/p"));
			Date date_asset = d.parse(el1.getText());
			System.out.println(date_asset);
			if(date_asset.compareTo(date_asset_previous)>0)
			{
				return false;
			}
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
			Thread.sleep(1000);
			date_asset_previous=date_asset;
		}
		return true;
	}
}
