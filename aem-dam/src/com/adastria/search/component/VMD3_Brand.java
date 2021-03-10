package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//checking if the assets are displayed as per selected branch
public class VMD3_Brand extends MainMethod{
	
	public static void brand_vmd() throws InterruptedException, IOException
	{
		//click on the VMD button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[2]")).click();
		Thread.sleep(1000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]")).click();
		Thread.sleep(1000);
		//open the brand drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[1]")).click();
		Thread.sleep(1000);
		//select the brand as per the choice
		//filter the assets
		//check if the filter assets have brand name equal to the selected brand
		List<WebElement> list=driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(list.size());
		Thread.sleep(1000);
		outer:for(int i=0;i<list.size();i++)
		{
			String brand= driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div/div["+(i+1)+"]")).getText();
			System.out.println(brand);
			if(brand.equals("Heather")==true)
			{
			list.get(i).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
			Thread.sleep(1000);
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
			//making a list of all the assets displayed
			List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
			System.out.println(element.size());
			inner1:for(int j=0;j<2;j++)
			{
				//opening each asset and checking the brand name
				WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
				el.click();
				Thread.sleep(1000);
				inner2:for(int k=0;;k++)
				{
					//checking the header and value of the metadata
					String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
					String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
				System.out.println(header);
				System.out.println(value);
				if(value.equals("Heather")==false && header.equals("ブランド")==false)
				{
					continue inner2;
				}
				else if(value.equals("Heather")==false && header.equals("ブランド")==true)
				{
					test.log(LogStatus.FAIL, "選択したブランドのアセットが表示される",test.addScreenCapture(screenshotPath));
					break inner1;
				}
				else
				{
					driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
					continue inner1;
				}
				}
			}
			test.log(LogStatus.PASS, "選択したブランドのアセットが表示される",test.addScreenCapture(screenshotPath));;
			break outer;
			}
			else
			{
				continue outer;
			}
		}
	}
}
