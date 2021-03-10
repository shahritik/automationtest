package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;

import com.relevantcodes.extentreports.LogStatus;

public class ASC_Document_Type extends MainMethod{

	//check if size is displayed in the metadata of the document asset
	
	public static void display_type() throws InterruptedException, IOException
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
		Thread.sleep(1000);
			WebElement el=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[5]/div/h4"));
			String header= el.getText();
			System.out.println(header);
			//ASSERTION CONDITION
			//check if the metadata has a header equal to タイプ
			if (header.equals("タイプ")==true)
			{
				WebElement el1=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[5]/div/p"));
				Coordinates cor1=((Locatable)el1).getCoordinates();
				System.out.println(cor1);
				cor1.inViewPort();
				screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
				String str=el1.getText();
				System.out.println(str);
				//ASSERTION CONDITION
				//check if the type of the asset is "PDF" or "XLS" or "DOC"
				if(correct(str)==true)
				{
					test.log(LogStatus.PASS,"タイプが表示されていること",test.addScreenCapture(screenshotPath));
				}
				else
				{
					test.log(LogStatus.FAIL, "タイプが表示されていること", test.addScreenCapture(screenshotPath));
				}
			}
			else
			{
				test.log(LogStatus.FAIL, "タイプが表示されていること");
			}
	}
	public static boolean correct(String str) throws IOException
	{
		if(str.equals("DOC")||str.equals("PDF")||str.equals("XLS"))
			return true;
		return false;
	}
}
