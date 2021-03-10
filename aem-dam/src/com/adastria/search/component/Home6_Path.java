package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filtering the assets by selecting the file type and checking the filtered assets

public class Home6_Path extends MainMenu_Test{
	
	public static void path_type() throws InterruptedException, IOException
	{
		//click on Home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		WaitForPage.waitForLoad(driver);
		//opening the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(2000);
		//opening the path dropdown
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div[2]/div")).click();
		Thread.sleep(1000);
		//list the options displayed in the dropdown
		List<WebElement> elements=driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div[2]/div/div[2]/child::div"));
		//print the number of check box 
		System.out.println("Number of Options::"+elements.size());
		Thread.sleep(2000);
		outer:for(int i=1;i<elements.size();i++)
		{
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div[2]/div/div[2]/div["+(i+1)+"]")).click();
			Thread.sleep(1000);
			//storing the product type (Styling, SNS) in label name
			String labelname=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div[2]/div/div[1]")).getText();
			System.out.println(labelname);
			//click the filter button
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
			Thread.sleep(1000);
			//take the screenshot
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			//close the side panel
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
			Thread.sleep(3000);
			//make a list of all the assets displayed
			List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
			System.out.println(list.size());
			inner: for(int k=0;k<list.size();k++)
			{
				//click each asset and check if the URL matches the pattern
				//styling-> URL should end with /styling/image_name
				//SNS-> URL should end with /sns/image_name
				driver.findElement(By.xpath("//*[@id=\\\"asset-share-commons__form-id__1\\\"]/div/div/div[1]/div/article["+(k+1)+"]")).click();
				WaitForPage.waitForLoad(driver);
				String url=driver.getCurrentUrl();
				String lastPart = url.substring(url.lastIndexOf('/'), url.lastIndexOf('/') + 1);
				if (lastPart == labelname) {
				   continue inner;
				}
				else
				{
					test.log(LogStatus.FAIL,"選択したファイル種別("+labelname+")のアセットが表示される",test.addScreenCapture(screenshotPath));
					continue outer;
				}
			}
			test.log(LogStatus.PASS, "選択したファイル種別("+(labelname)+")のアセットが表示される",test.addScreenCapture(screenshotPath));
			}
		Thread.sleep(1000);
	}
}
