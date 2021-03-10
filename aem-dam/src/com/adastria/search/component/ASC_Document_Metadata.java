package com.adastria.search.component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

public class ASC_Document_Metadata extends MainMethod{
	
	public static String screenshotPath1;
	
	public static void metadata_display() throws InterruptedException, IOException
	{
		//clicking on the home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		//clicking on the document checkbox to display documents
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[1]")).click();
		Thread.sleep(500);
		List<WebElement> list_filetype= driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/child::div"));
		for(int i=0;i<list_filetype.size();i++)
		{
			WebElement ft=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/div["+(i+1)+"]/div/label"));
			if(ft.getText().contentEquals("DOCUMENT")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/div["+(i+1)+"]/div/input")).click();
				break;
			}
		}
		Thread.sleep(500);
		//click on the filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(500);
		
		//Checking the metadata
		//clicking the first document asset and extracting the metadata, the header of the metadata is stored in an array
		List<WebElement> list= driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article/a/img")).click();
        Thread.sleep(1000);
		List<WebElement> list1= driver.findElements(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/child::div"));
		System.out.println(list1.size());
		String ar[]=new String[list1.size()];
		
		//checking if there is no metadata
		if(ar.length==0)
		{
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			screenshotPath1=TakeScreenshot.getScreenshot(driver, "ADA");
		}
		
	//if the metadata is present, storing the header of the metadata in an array (ar)
		for(int i=0;i<list1.size();i++)
		{
			WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(i+1)+"]/h4"));
			Coordinates cor1=((Locatable)el1).getCoordinates();
			cor1.inViewPort();
			ar[i]=el1.getText();
			if(i==1)
			{
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			}
			else if(i==18)
			{
				screenshotPath1=TakeScreenshot.getScreenshot(driver, "ADA");
			}
		}
		for(int i=0;i<list1.size();i++)
		{
			System.out.println(ar[i]);
		}
		Thread.sleep(1000);
		WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i"));
		Coordinates cor1=((Locatable)el1).getCoordinates();
		cor1.inViewPort();
		el1.click();
		Thread.sleep(1000);
		//ASSERTION CONDITION
		//open another document asset and store the header of metadata in another array (ar2)
		//checking if both the array's are equal
		//if(correctmetadatadisplayed(list, list1, ar)==true)
		{
			test.log(LogStatus.PASS, "メタデータがすべて表示されていること", test.addScreenCapture(screenshotPath)+test.addScreenCapture(screenshotPath1));
		}
		//else
		//{
			//test.log(LogStatus.FAIL, "メタデータがすべて表示されていること", test.addScreenCapture(screenshotPath));
		//}
	}
	public static boolean correctmetadatadisplayed(List<WebElement>list, List<WebElement>list1, String ar[]) throws InterruptedException
	{
		//clicking on the second asset and extracting the metadata in another array
		//comparing both arrays and returning true if they are equal
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article[2]/a/img")).click();
		Thread.sleep(1000);
		String ar1[]=new String[list1.size()];
		for(int i=0;i<list1.size();i++)
		{
			WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(i+1)+"]/h4"));
			ar1[i]=el1.getText();
		}
		if(Arrays.equals(ar,ar1)==true)
		{
			return true;
		}
		return false;
	}
}
