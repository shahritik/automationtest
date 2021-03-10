package com.adastria.search.component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//click on DOWNLOAD, ADD TO CART and COPY URL in the side panel of Asset Detail Page and check if the actions are performed correctly

public class ASC_Actions extends MainMethod{
	
	public static void check_download() throws InterruptedException, IOException
	{
		//clicking on the home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(1000);
		//clicking on the image checkbox to display still images
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[1]")).click();
		Thread.sleep(500);
		List<WebElement> list_filetype= driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/child::div"));
		for(int i=0;i<list_filetype.size();i++)
		{
			WebElement ft=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/div["+(i+1)+"]/div/label"));
			if(ft.getText().contentEquals("IMAGE")==true)
			{
				driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div/div["+(i+1)+"]/div/input")).click();
				break;
			}
		}
		Thread.sleep(500);
		//click on filter button
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(500);
		
		//open any one image asset
		
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article[1]/a/img")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(1000);
		
		//getting the list of all the buttons
		List<WebElement> list= driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/child::button"));
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{
			WebElement el=list.get(i);
			//ASSERTION CONDITION
			//check if the first button is the ダウンロード button, if yes click the download button
			if(el.getText().equals("ダウンロード")==true)
			{
				el.click();
				Thread.sleep(1000);
				List<WebElement> elements=driver.findElements(By.xpath("//input[@type='checkbox']"));
				System.out.println("Number of CheckBoxes::"+elements.size());
				Thread.sleep(2000);
				//check mark all the check boxes
				for(int j=0;j<elements.size();j++)
				{
					WebElement el1=driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/div[2]/div["+(j+2)+"]/div"));
					el1.click();
				}
				//take the screenshot
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				Thread.sleep(1000);
				//click to download the assets
				driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/button")).click();
				Thread.sleep(7000);
				//ASSERTION CONDITION
				//open the download folder and check if the assets get downloaded 
				if(checkdownload()==true)
					test.log(LogStatus.PASS, "アセットがダウンロードできること", test.addScreenCapture(screenshotPath));
				else
					test.log(LogStatus.FAIL, "アセットがダウンロードできること", test.addScreenCapture(screenshotPath));
			}
			//ASSERTION CONDITION
			//check if the second button is equal to リストに追加 button, if yes click on the button
			else if(el.getText().equals("リストに追加")==true)
			{
				String cartnumber= driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[1]/div")).getText();
				Thread.sleep(1000);
				el.click();
				screenshotPath=TakeScreenshot.getScreenshot(driver,"ADA");
				Thread.sleep(3000);
				//ASSERTION CONDITION
				//check if the asset is added to the cart
				if(checkaddtolist(cartnumber)==true)
					test.log(LogStatus.PASS, "リストに追加できること", test.addScreenCapture(screenshotPath));
				else
					test.log(LogStatus.FAIL, "リストに追加できること", test.addScreenCapture(screenshotPath));
			}
			//ASSERTION CONDITION
			//check if the second button is equal to リストから削除 button, if yes click on the button
			else if(el.getText().equals("リストから削除")==true)
			{
				String cartnumber= driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[1]/div")).getText();
				Thread.sleep(1000);
				el.click();
				Thread.sleep(500);
				screenshotPath=TakeScreenshot.getScreenshot(driver,"ADA");
				Thread.sleep(3000);
				//ASSERTION CONDITION
				//check if the asset is removed from the cart
				if(checkremovefromlist(cartnumber)==true)
					test.log(LogStatus.PASS, "リストに削除できること", test.addScreenCapture(screenshotPath));
				else
					test.log(LogStatus.FAIL, "リストに削除できること", test.addScreenCapture(screenshotPath));
			}
			else
			{
				test.log(LogStatus.FAIL, "Actionボタンを押下品ことができません", test.addScreenCapture(screenshotPath));
			}
		}
		Thread.sleep(1000);
		//check if the URL button exists on the side panel of Asset Detail Page, if yes click on the URL コピー　button
		if(element_exits()==true)
		{
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/button")).click();
        	screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			test.log(LogStatus.PASS, "アセットURLをコピーをできること", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "アセットURLをコピーをできること", test.addScreenCapture(screenshotPath));
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(1000);
	}
	private static boolean element_exits() {
		try {
        	driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/button"));
		} catch (Exception e) {
			return false;	
		}	
		return true;
	}
	private static boolean checkdownload() throws InterruptedException {
		String downloadPath = "/Users/ritik.shah/Desktop/Check_Downloads";
		Thread.sleep(1000);
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
		System.out.println(dirContents.length);
        System.out.println(dirContents[0].getName());
        for (int i = 0; i<dirContents.length; i++) {
            if (dirContents[i].getName().equals("assets.zip")==true) {
                dirContents[i].delete();
                return true;
            }
        }
        return false;
	}

	private static boolean checkremovefromlist(String cartnumber) {
		String newcartnumber=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[1]/div")).getText();
		int x= Integer.parseInt(cartnumber);
		int y=Integer.parseInt(newcartnumber);
		System.out.println(x+""+y);
		if(y==(x-1))
			return true;
		return false;
	}

	private static boolean checkaddtolist(String cartnumber) {
		String newcartnumber=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[1]/div")).getText();
		int x= Integer.parseInt(cartnumber);
		int y=Integer.parseInt(newcartnumber);
		System.out.println(x+""+y);
		if(y==(x+1))
			return true;
		return false;
	}
}
