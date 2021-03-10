package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

//filter the assets by colour and check if the assets are displayed correctly

public class Product11_Colour extends MainMethod{
	
	static String colour;
	
	public static void colour_product() throws InterruptedException, IOException
	{
		//click on the item button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(2500);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(2500);
		//open the colour drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[10]/div/div/div[1]")).click();
		Thread.sleep(2500);
		//select the colour as Black
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[10]/div/div/div[2]/div/child::div"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			colour=el.getText();
			WebElement el_click=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[10]/div/div/div[2]/div/div["+(i+1)+"]/div/input"));
			if(colour.equals("BLK")==true)
			{
				el_click.click();
				break;
			}
		}
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(2000);
		//click the filter button
		WebElement e=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button"));
		Coordinates cor=((Locatable)e).getCoordinates();
		cor.inViewPort();
		Thread.sleep(2500);
		e.click();
		Thread.sleep(2000);
		WebElement e1=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i"));
		Coordinates cor1=((Locatable)e1).getCoordinates();
		cor1.inViewPort();
		e1.click();
		Thread.sleep(2000);
		//ASSERTION CONDITION
		//open the assets one by one and check if the colour is equal to the selected colour
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "指定されたカラーのアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "指定されたカラーのアセットが表示される", test.addScreenCapture(screenshotPath));
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
			Thread.sleep(2500);
			inner2:for(int k=0;;k++)
			{
			String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
			System.out.println(header);
			System.out.println(value);
			if(value.equals(colour)==false && header.equals("カラー")==false)
			{
				continue inner2;
			}
			else if(value.equals(colour)==false || header.equals("カラー")==false)
			{
				return false;
			}
			else if(value.equals(colour)==true && header.equals("カラー")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
				continue inner1;
			}
			}
		}
		return true;
}
}
