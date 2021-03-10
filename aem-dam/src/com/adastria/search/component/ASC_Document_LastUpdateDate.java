package com.adastria.search.component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

//check if last updated date is present in the asset metadata

public class ASC_Document_LastUpdateDate extends MainMethod{
	
	//creating a date object
	static SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
	
	public static void displaylastupdatedate() throws InterruptedException, IOException
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
				
				//open any one document asset
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article/a/img")).click();
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		Thread.sleep(1000);
		//ASSERTION CONDITION
		//check if the "最終更新日" is present in the metadata
		if(element_exists()==true)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[4]/div/h4"));
			String header= el.getText();
			System.out.println(header);
			if (header.equals("最終更新日")==true)
			{
				WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[4]/div/p"));
				Coordinates cor1=((Locatable)el1).getCoordinates();
				System.out.println(cor1);
				cor1.inViewPort();
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				String str=el1.getText();
				System.out.println(str);
				//ASSERTION CONDITION
				//check the value of "最終更新日" if it is in the date format
				if(correct(str)==true)
				{
					test.log(LogStatus.PASS,"最終更新日時が表示されていること",test.addScreenCapture(screenshotPath));
				}
				else
				{
					test.log(LogStatus.FAIL, "最終更新日時が表示されていること", test.addScreenCapture(screenshotPath));
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "最終更新日時が表示されていること");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "最終更新日時のヘッダが表示されていること",test.addScreenCapture(screenshotPath));
		}		
	}
	private static boolean element_exists() {
		WebElement el;
		try {
			el = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[4]/div/h4"));
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}
	public static boolean correct(String str) throws IOException
	{
		d.setLenient(false);
		try {
			d.parse(str);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
