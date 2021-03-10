package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//check if the assets can be viewed in list or card view
public class Home5_ToggleDisplay extends MainMethod{
	
	//make an array for the column names of list view
	static String ar[]= {"PREVIEW","名前","日付","解像度","サイズ",""};
	
	public static void toggledisplay() throws InterruptedException, IOException
	{
		//click on the HOME button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		//click on the list view button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[2]/button[2]/i")).click();
		Thread.sleep(1000);
		//initializing a assertion variable
		int a=0;
		//take the screenshot of the list view of the assets
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		//make a list of all the column names of the list view
		//compare the list with the array and return PASS if the list is equal to the array
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/table/thead/tr/child::th"));
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			if(el.getText().equals(ar[i])==false)
			{
				a=1;
				test.log(LogStatus.FAIL, "検索結果がリスト表示になる", test.addScreenCapture(screenshotPath));
				break;
			}
		}
		if(a==0)
		{
			test.log(LogStatus.PASS, "検索結果がリスト表示になる", test.addScreenCapture(screenshotPath));
		}
		Thread.sleep(1000);
		//click on the card icon to get the card view
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[2]/button[1]/i")).click();
		Thread.sleep(1000);
		//take screenshot of the card view
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		//check the class name of the card view screen
		WebElement el=driver.findElement(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div"));
		//if the class name is equal to "ui special cards cmp-cards" return PASS
		if(el.getAttribute("class").equals("ui special cards cmp-cards")==true)
		{
			test.log(LogStatus.PASS, "検索結果がタイル表示になる", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "検索結果がタイル表示になる", test.addScreenCapture(screenshotPath));
		}
		Thread.sleep(1000);
	}
}
