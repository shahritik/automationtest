package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

//filtering the assets by selecting the file type and checking the filtered assets

public class Home2_FileType extends MainMenu_Test{
	
	public static void fileType() throws InterruptedException, IOException
	{
		//click on Home button
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[1]")).click();
		WaitForPage.waitForLoad(driver);
		//opening the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[8]/i")).click();
		Thread.sleep(2000);
		//opening the file type dropdown
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[1]")).click();
		Thread.sleep(1000);
		//check the number of check box present on the screen
		List<WebElement> elements=driver.findElements(By.xpath("//input[@type='checkbox']"));
		//print the number of check box 
		System.out.println("Number of CheckBoxes::"+elements.size());
		Thread.sleep(2000);
		outer:for(int i=0;i<elements.size();i++)
		{
			//storing the file type (DOCUMENT, VIDEO, PRESENTATION, IMAGE) in label name
			String str="/div["+(i+1)+"]/div/label";
			String labelname=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[2]/div"+str)).getText();
			System.out.println(labelname);
			//clicking on the current check box and un-selecting the previously clicked check box
			inner:for(int j=i-1;j<=i;j++)
			{
				if(j==-1)
				{
					continue inner;
				}
				System.out.println("Counter="+j);
			WebElement el=elements.get(j);
			el.click();
			}
			//click the filter button
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[1]/div/div/div[1]/button")).click();
			Thread.sleep(1000);
			//take the screenshot
			screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
			//close the file type drop down
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[1]")).click();
			Thread.sleep(1000);
			//close the side panel
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
			Thread.sleep(3000);
			//make a list of all the assets displayed
			List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
			System.out.println(list.size());
			for(int k=0;k<list.size();k++)
			{
				WebElement el1=driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(k+1)+"]/div[1]/div/div[2]/span"));
				//if the label　name is equal to DOCUMENT then, file type can be PDF or DOC or XLS
				if(labelname.equals("DOCUMENT")==true)
				{
					if(el1.getText().equals("PDF")||el1.getText().equals(null)||el1.getText().equals("DOC")||el1.getText().equals("XLS"))
					{}
					else
					{
						test.log(LogStatus.FAIL,"選択したファイル種別("+labelname+")のアセットが表示される",test.addScreenCapture(screenshotPath));
						continue outer;
					}
				}
				//if the label name is equal to IMAGE, VIDEO or PRESENTATION then, file type will be IMAGE, VIDEO or PRESENTATION
				else
				{
				if(el1.getText().equals(labelname)==true||el1.getText().equals(null)==true)
				{}
				else
				{
					test.log(LogStatus.FAIL,"選択したファイル種別("+labelname+")のアセットが表示される",test.addScreenCapture(screenshotPath));
					continue outer;
				}
				}
			}
			test.log(LogStatus.PASS, "選択したファイル種別("+(labelname)+")のアセットが表示される",test.addScreenCapture(screenshotPath));
			//opening the side panel
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[8]/i")).click();
			Thread.sleep(2000);
			//opening the file type dropdown
			driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[2]/div/div/div[1]")).click();
			Thread.sleep(1000);
			}
		Thread.sleep(1000);
	}
}
