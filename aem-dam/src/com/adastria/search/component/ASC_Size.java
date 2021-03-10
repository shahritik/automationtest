package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

public class ASC_Size extends MainMethod{
	
	//check if the Size is displayed in Asset Metadata
		
	public static void displaysize() throws InterruptedException, IOException
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
				
				// open any one image asset
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article[1]/a/img")).click();
		Thread.sleep(1000);
		
			WebElement el=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[6]/div/h4"));
			String header= el.getText();
			System.out.println(header);
			//ASSERTION CONDITION 1
			//check if the header is equal to "サイズ"
			if (header.equals("サイズ")==true)
			{
				WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[6]/div/p"));
				Coordinates cor1=((Locatable)el1).getCoordinates();
				System.out.println(cor1);
				cor1.inViewPort();
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				String str=el1.getText();
				System.out.println(str);
				//ASSERTION CONDITION 2
				//check if the size of the asset is displayed in KB, MB, GB or TB
				if(correct(str)==true)
				{
					test.log(LogStatus.PASS,"サイズが表示されていること",test.addScreenCapture(screenshotPath));
				}
				else
				{
					test.log(LogStatus.FAIL, "サイズが表示されていること", test.addScreenCapture(screenshotPath));
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "サイズが表示されていること");
			}
	}
	public static boolean correct(String str) throws IOException
	{
		int n= str.length();
		String k= str.substring(n-2,n);
		if(k.contentEquals("KB") ||k.contentEquals("MB") ||k.equals("GB") ||k.equals("TB"))
				return true;
		return false;
	}
}
