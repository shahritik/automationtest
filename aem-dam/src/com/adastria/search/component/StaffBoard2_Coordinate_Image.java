package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class StaffBoard2_Coordinate_Image extends MainMenu_Test{
	
	static String str;

	public static void staffboard_coordinate_image() throws InterruptedException, IOException
	{
		//click on Staffboard button
		driver.findElement(By.xpath("///*[@id=\"main\"]/div/div/div[1]/header/div/div/a[4]")).click();
		WaitForPage.waitForLoad(driver);
		//opening the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(2000);
		//opening the coordinate image dropdown
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[1]")).click();
		Thread.sleep(1000);
		//checking the coordinate image checkbox
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div/div/div/input")).click();
		//getting the string associated with the checkbox
		str=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/div[2]/div/div/div/label")).getText();
		//click the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(1000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		//close the side panel
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(3000);
		//open the assets one by one and check if the brand is equal to the selected brand
		if(correctassetdiplayed()==true)
		{
			test.log(LogStatus.PASS, "コーディネート画像のアセットが表示されます", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "コーディネート画像のアセットが表示されます", test.addScreenCapture(screenshotPath));
		}
	}
	private static boolean correctassetdiplayed() throws InterruptedException 
	{
		List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
		System.out.println(element.size());
		if(element.size()!=0)
		{
		for(int j=0;j<element.size();j++)
		{
			WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
			el.click();
			Thread.sleep(3000);
			WebElement headers= driver.findElement(By.className("metadata-list aem-GridColumn aem-GridColumn--default--12"));
			List <WebElement>  elements= headers.findElements(By.xpath(".//div/child::div"));
			System.out.println(elements.size());
			if(elements.size()==0)
			{
				return true;
			}
			else
			{
			inner2:for(int k=0;k<elements.size();k++)
			{
			String header=headers.findElement(By.xpath(".//div/div["+(k+1)+"]/h4")).getText();
			String value=driver.findElement(By.xpath(".//div/div["+(k+1)+"]/p")).getText();
			System.out.println(header);
			System.out.println(value);
			if(header.equals("ada-staffboard")==true)
			{
				return false;
			}
			else if(header.equals("ada-staffboard")==false)
			{
				continue inner2;
			}
			}
		}
		}
		}
		return true;
}
}
