package com.adastria.search.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class Product5_Category_Brand extends MainMethod{

	//check if only the categories related to a particular brand are shown
	
	public static void brand_category() throws InterruptedException, IOException
	{
		//click on item
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[3]")).click();
		Thread.sleep(3000);
		//open the side panel
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[6]/i")).click();
		Thread.sleep(3000);
		//check if initially the category tab should be empty since no brand is selected
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div")).click();
		Thread.sleep(3000);
		//take the screenshot for the empty category dropdown
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/child::div"));
		if(list.size()==1)
		{
			test.log(LogStatus.PASS, "最初は、カテゴリのドロップダウンは空です", test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL, "最初は、カテゴリのドロップダウンは空です", test.addScreenCapture(screenshotPath));
		}
		Thread.sleep(3000);
		//close the category drop down
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div")).click();
		Thread.sleep(3000);
		//selecting the brand so that category is displayed
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div")).click();
		Thread.sleep(3000);
		//selecting the brand 1 by 1
		List<WebElement> list1 = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div/div[2]/child::div"));
		System.out.println(list1.size());
		for(int i=2;i<3;i++)
		{
			WebElement el=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div/div[2]/div["+(i+1)+"]"));
		    el.click();
		    Thread.sleep(3000);
		    screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		    //ASSERTION CONDITION
		    //check if the category is filtered as per the brand ID
		    if(correctareadisplayed(el)==true)
		    {
		    	test.log(LogStatus.PASS, "ブランドに関連したカテゴリのみ表示される", test.addScreenCapture(screenshotPath));
		    }
		    else
		    {
		    	test.log(LogStatus.FAIL, "ブランドに関連したカテゴリのみ表示される", test.addScreenCapture(screenshotPath));
		    }
		}
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/span/div/div/div[2]/div/a[2]/i")).click();
		Thread.sleep(500);
	}
	private static boolean correctareadisplayed(WebElement el) throws InterruptedException {
		//extracting the brand ID of the selected branch
		String str=el.getAttribute("data-value");
		String brandId=str.substring(str.lastIndexOf("/")+1);
		System.out.println(brandId);
		//clicking on the category drop down
	    driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div")).click();
	    Thread.sleep(3000);
		List<WebElement> list1 = driver.findElements(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/child::div"));
		System.out.println(list1.size());
		String ar[]=new String[list1.size()-1];
		for(int i=0;i<list1.size();i++)
		{
			WebElement el1=driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div/div[2]/div["+(i+1)+"]"));
			String str1=el1.getAttribute("data-value");
			System.out.println(str1);
			if(str1.contains("adastria")==true)
			{
				ar[i-1]=str1.substring(str1.lastIndexOf("/")+1);
			}
		}
		Thread.sleep(3000);
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    Thread.sleep(500);
	    driver.get("http://13.113.230.58:4502/libs/cq/search/content/querydebug.html?_charset_=UTF-8&query=path%3D%2Fetc%2Ftags%2Fadastria%2Fitem%2Fcategory%0D%0Aproperty%3D.%2FbrandId%0D%0Aproperty.value%3D"+(brandId)+"%0D%0Ap.limit%3D-1");
		Thread.sleep(3000);
		if(k==0)
		{
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("b2iztPtesQmc");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"submit-button\"]/coral-button-label")).click();
		k++;
		Thread.sleep(4000);
		}
		List<WebElement> list2 = driver.findElements(By.xpath("/html/body/div[3]/ul/child::li"));
		String ar2[]=new String[list2.size()];
		for(int i=0;i<list2.size();i++)
		{
			WebElement el2=driver.findElement(By.xpath("/html/body/div[3]/ul/li["+(i+1)+"]/a[1]"));
			String str2=el2.getText();
			System.out.println(str2);
			ar2[i]=str2.substring(str2.lastIndexOf("/")+1);
		}
		Thread.sleep(500);
		driver.close();
		Thread.sleep(500);
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[5]/div/div")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rail\"]/div/div/div/div/div/div[4]/div/div")).click();
		Thread.sleep(500);
		Arrays.sort(ar);
		Arrays.sort(ar2);
		if(Arrays.equals(ar,ar2)==true && ar.length==ar2.length)
			return true;
		else
			return false;
	}
}
