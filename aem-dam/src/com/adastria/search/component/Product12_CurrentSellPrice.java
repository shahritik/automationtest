package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets by current selling price and check if the assets are correctly displayed

public class Product12_CurrentSellPrice extends MainMethod{
	
	//intializing minimum and maximum current selling price
	static String min= "6000.00";
	static String max="8000.00";
	static Double min1=Double.parseDouble(min);
	static Double max1=Double.parseDouble(max);
	
	public static void currentsell() throws InterruptedException, IOException
	{
		//click on the item button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(3000);
		//open the current selling price drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[12]/div/div/div[1]")).click();
		Thread.sleep(3000);
		//input the minimum and maximum selling price
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[12]/div/div/div[2]/div[1]/div/div/input[2]")).sendKeys(min);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[12]/div/div/div[2]/div[2]/div/div/input[2]")).sendKeys(max);
		Thread.sleep(3000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(4000);
		//click on filter button
		WebElement e=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button"));
		Coordinates cor=((Locatable)e).getCoordinates();
		cor.inViewPort();
		Thread.sleep(3000);
		e.click();
		Thread.sleep(6000);
		WebElement e1=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i"));
		Coordinates cor1=((Locatable)e1).getCoordinates();
		cor1.inViewPort();
		e1.click();
		Thread.sleep(3000);
		//ASSERTION CONDITION
		//open the assets one by one and check if the current selling price is in between the minimum and maximum
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "指定された当初売価のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "指定された当初売価のアセットが表示される", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed() throws InterruptedException 
	{
		try {
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
				if(header.equals("現売価")==false)
				{
					continue inner2;
				}
				else if(header.equals("現売価")==true)
				{
					Double val=Double.parseDouble(value);
					if(val<min1 || val>max1)
					return false;
					else if(val>=min1 || val<=max1)
					{
					driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
					continue inner1;
					}
				}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
}
}