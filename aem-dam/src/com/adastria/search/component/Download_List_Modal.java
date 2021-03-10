package com.adastria.search.component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class Download_List_Modal extends MainMethod{
	
	//Check the function of download modal and list modal
	
	//assign a big size array
	static String ar[]=new String[1000];
	static int index=0;
	
	public static void download_asset() throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		Thread.sleep(500);
		//clicking on the image check box to display still images
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
				//click on the filter button
				driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
				Thread.sleep(500);
			
				//clicking on the an image asset and add it to the cart
				List<WebElement> list= driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
				System.out.println(list.size());
				for(int i=0;i<2;i++)
				{
					WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/a/img"));
					String heading= driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(i+1)+"]/div/h3/a")).getText();
					Thread.sleep(1000);
					//store the heading of the image asset in the array (ar)
					ar[index]=heading;
					el.click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
					Thread.sleep(500);
					driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/button[2]")).click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i"))).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
					index++;
				}
				Thread.sleep(500);
				
				//click on the cart icon to open the cart
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[5]/i")).click();	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/form/div[3]/button"))).click();
			Thread.sleep(3000);
			
			//take the screenshot of the cart
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			String str= driver.findElement(By.xpath("/html/body/div[2]/form/div[1]")).getText();
			//ASSERTION CONDITION
			//check if the cart has a download button
			if(str.equals("ダウンロード")==true)
			{
				test.log(LogStatus.PASS, "ダウンロードモーダルが表示される", test.addScreenCapture(screenshotPath));
			}
			else
			{
				test.log(LogStatus.FAIL, "ダウンロードモーダルが表示される", test.addScreenCapture(screenshotPath));
			}
			
			//check mark the download option shown for the assets one by one
			List<WebElement> elements=driver.findElements(By.xpath("//input[@type='checkbox']"));
			System.out.println("Number of CheckBoxes::"+elements.size());
			Thread.sleep(2000);
			for(int j=0;j<2;j++)
			{
				WebElement el1=driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/div[2]/div["+(j+2)+"]/div"));
				String str1= el1.getText();
				el1.click();
				Thread.sleep(500);
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/button")).click();
				Thread.sleep(7000);
				//ASSERTION CONDITION
				//check the download folder if the asset get's downloaded
				if(checkdownload()==true)
					test.log(LogStatus.PASS, str1, test.addScreenCapture(screenshotPath));
				else
					test.log(LogStatus.FAIL, str1, test.addScreenCapture(screenshotPath));
				Thread.sleep(1000);
				if(j!=1)
				{
				WebElement e1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[5]/i"));
				Coordinates cor1=((Locatable)e1).getCoordinates();
				cor1.inViewPort();
				e1.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/button")).click();
				Thread.sleep(1000);
				}
			}
	}
	public static void list_asset() throws InterruptedException, IOException
	{
		int result=1;
		WebElement e1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[5]/i"));
		Coordinates cor1=((Locatable)e1).getCoordinates();
		cor1.inViewPort();
		e1.click();
		Thread.sleep(1000);
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		List<WebElement> list=driver.findElements(By.xpath("/html/body/div[2]/form/div[2]/table/tbody/child::tr"));
		for(int i=0;i<list.size();i++)
		{
			WebElement et1=driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/table/tbody/tr["+(i+1)+"]/td[2]/a"));
			if(et1.getText().equals(ar[i])==false)
			{
				result=0;
			}
		}
		if(result==1)
		{
			test.log(LogStatus.PASS, "リストに追加されているアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "リストに追加されているアセットが表示される", test.addScreenCapture(screenshotPath));
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[2]/form/div[2]/table/tbody/tr[1]/td[3]/button")).click();
		Thread.sleep(1000);
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(500);
		List<WebElement> list1=driver.findElements(By.xpath("/html/body/div[2]/form/div[2]/table/tbody/child::tr"));
		if((list1.size()+1)==list.size())
			test.log(LogStatus.PASS, "リストから削除できる", test.addScreenCapture(screenshotPath));
		else
			test.log(LogStatus.PASS, "リストから削除できる", test.addScreenCapture(screenshotPath));
		Thread.sleep(1000);
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
}
