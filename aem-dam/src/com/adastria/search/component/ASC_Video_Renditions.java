package com.adastria.search.component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class ASC_Video_Renditions extends MainMethod{
	
	//click on the rendition button and check if the respective asset rendition can be downloaded
	
	public static void check_renditions() throws IOException, InterruptedException
	{
		//clicking on the home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		//clicking on the video checkbox to display videos
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[1]")).click();
		Thread.sleep(500);
		List<WebElement> list_filetype= driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/child::div"));
		for(int i=0;i<list_filetype.size();i++)
		{
			WebElement ft=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/div["+(i+1)+"]/div/label"));
			if(ft.getText().contentEquals("VIDEO")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/div["+(i+1)+"]/div/input")).click();
				break;
			}
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(500);
		
		String title=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article[1]/div[1]/h3/a")).getText();
		Thread.sleep(1000);
		//click on any one video asset
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article[1]/a/img")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(1000);
		//get the list of all the rendition buttons
		List<WebElement> list= driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[3]/div/div/child::a"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			String rendition=el.getText();
			Thread.sleep(1000);
			el.click();
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			Thread.sleep(8000);
			//ASSERTION CONDITION
			//check if the asset rendition gets downloaded once we click on the rendition button
			if(click(el, title, rendition)==true)
				test.log(LogStatus.PASS, el.getText()+"サイズのアセットダウンロードできること", test.addScreenCapture(screenshotPath));
			else
				test.log(LogStatus.FAIL, el.getText()+"サイズのアセットダウンロードできること", test.addScreenCapture(screenshotPath));
		}
		List<WebElement> list1= driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/child::div"));
		System.out.println(list1.size());
		for(int i=0;i<list1.size();i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div["+(i+1)+"]/a"));
			String rendition=el.getText();
			Thread.sleep(1000);
			el.click();
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			Thread.sleep(8000);
			if(click(el, title, rendition)==true)
				test.log(LogStatus.PASS, el.getText()+"サイズのアセットダウンロードできること", test.addScreenCapture(screenshotPath));
			else
				test.log(LogStatus.FAIL, el.getText()+"サイズのアセットダウンロードできること", test.addScreenCapture(screenshotPath));
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
	}
	private static boolean click(WebElement el, String title, String rendition) throws InterruptedException {
		String downloadPath = "/Users/ritik.shah/Desktop/Check_Downloads";
		Thread.sleep(1000);
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
		System.out.println(dirContents.length);
        System.out.println(dirContents[0].getName());
        for (int i = 0; i<dirContents.length; i++) {
            if (dirContents[i].getName().equals(title+" - "+rendition+".m4v")==true || dirContents[i].getName().equals(title+" - "+rendition+".mp4")==true || dirContents[i].getName().equals(title+" - "+rendition+".wmv")==true|| dirContents[i].getName().equals(title+" - "+rendition+".qt")==true|| dirContents[i].getName().equals(title+" - "+rendition+".mov")==true) {
                dirContents[i].delete();
                return true;
            }
        }
            return false;
        }
}
