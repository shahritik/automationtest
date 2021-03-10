package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

public class ASC_Video_Resolution extends MainMethod{
		
	public static void displayresolution() throws InterruptedException, IOException
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
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article[3]/a/img")).click();
		Thread.sleep(1000);
		if(element_exists()==true)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[7]/div/h4"));
			String header= el.getText();
			System.out.println(header);
			if (header.equals("解像度")==true)
			{
				WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[7]/div/p"));
				Coordinates cor1=((Locatable)el1).getCoordinates();
				System.out.println(cor1);
				cor1.inViewPort();
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				String str=el1.getText();
				System.out.println(str);
				if(correct(str)==true)
				{
					test.log(LogStatus.PASS,"解像度が表示されていること",test.addScreenCapture(screenshotPath));
				}
				else
				{
					test.log(LogStatus.FAIL, "解像度が表示されていること", test.addScreenCapture(screenshotPath));
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "解像度が表示されていること");
			}
		}
			else
				{
				test.log(LogStatus.FAIL, "解像度が表示されていること");
				}
	}
	private static boolean element_exists() {
		WebElement el;
		try {
			el=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[7]/div/h4"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean correct(String str) throws IOException
	{
		//if(type=="IMAGE")
		if(str.equals("1920 x 1080"))
				return true;
		return false;
	}
}
