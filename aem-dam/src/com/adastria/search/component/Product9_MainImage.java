package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

public class Product9_MainImage extends MainMethod{
	
	//filter the assets by main image and check if the assets are displayed correctly
	
	public static void mainimage() throws InterruptedException, IOException
	{
		//click on the item button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(1000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(1000);
		//click the main image drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[9]/div/div/div[1]")).click();
		Thread.sleep(1000);
		//check the main image check box
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[9]/div/div/div[2]/div/div/div/input")).click();
		Thread.sleep(1000);
		//click on filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i"))).click();
		//ASSERTION CONDITION
		//open the assets one by one and check if the "Main Image" metadata has a value equal to 1
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "メイン画像のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "メイン画像のアセットが表示される", test.addScreenCapture(screenshotPath));
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
			Thread.sleep(1000);
			inner2:for(int k=0;;k++)
			{
			String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
			System.out.println(header);
			System.out.println(value);
			if(value.equals("1")==false && header.equals("メインフラグ")==false)
			{
				continue inner2;
			}
			else if(value.equals("1")==false && header.equals("メインフラグ")==true)
			{
				return false;
			}
			else if(value.equals("1")==true && header.equals("メインフラグ")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				continue inner1;
			}
			}
		}
		return true;
}
}
