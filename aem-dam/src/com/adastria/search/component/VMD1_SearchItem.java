package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class VMD1_SearchItem extends MainMethod{
	
	static String brand="";
	
	public static void searchItem_vmd() throws IOException, InterruptedException
	{
	//click on the VMD button in the header
	driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[1]/header/div/div/a[2]")).click();
	Thread.sleep(1000);
	try {
		//clicking the input field
		driver.findElement(By.id("asc-search-form_input")).click();
		Thread.sleep(8000);
		//typing "He" in the input field
		driver.findElement(By.id("asc-search-form_input")).sendKeys("He");
		Thread.sleep(2000);
		//make a list of the options shown in auto-complete drop-down
		List<WebElement> list = driver.findElements(By.xpath("//ul[@id=\"ui-id-1\"]/child::li"));
		//print the number of options shown in auto-complete drop-down
		System.out.println("Auto Suggest List ::" + list.size());
		//take the screenshot of the screen
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
	    for(int i = 0 ;i< list.size();i++)
	    {
	        System.out.println(list.get(i).getText());
	        //ASSERTION CONDITION 1
	        //to check if the auto-complete works good
	        //if one of the options is equal to "Heather" then PASS the test case
	        if(list.get(i).getText().equals("Heather"))
	        {
	        	brand=list.get(i).getText();
	        	test.log(LogStatus.PASS, "キーワード入力時に、キーワードのオートコンプリートが表示される",test.addScreenCapture(screenshotPath));
				Thread.sleep(1000);
	            list.get(i).click();
	            break;
	        }
	        //if we check all the option and no option is equal to "Heather"
	        //Test Case Fails
	        else if(i==(list.size()-1))
	        {
	        	test.log(LogStatus.FAIL, "キーワード入力時に、キーワードのオートコンプリートが表示される");
	        }
	    }
		Thread.sleep(5000);
		//click on the submit button
		driver.findElement(By.id("asc-search-form_submit")).click();
		Thread.sleep(2000);
		//take the screenshot
		screenshotPath=TakeScreenshot.getScreenshot(driver, "ADA");
		//ASSERTION CONDITION 2
		//to check if correct assets are displayed after we click on submit button
		//Open all the assets and check if the brand name is equal to "Heather"
		if(correctassetdisplayed()==true)
		{
			test.log(LogStatus.PASS, "キーワードでヒットしたアセットが表示される",test.addScreenCapture(screenshotPath));
		}
		else
		{
			test.log(LogStatus.FAIL,"キーワードでヒットしたアセットが表示される",test.addScreenCapture(screenshotPath));
		}
		
		
	}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}  

private static boolean correctassetdisplayed() throws InterruptedException {
	//check the number of assets("article") that appear after the we hit the search button
	List<WebElement> element = driver.findElements(By.xpath("//*[@id=\"asset-share-commons__form-id__1\"]/div/div/div[1]/div/child::article"));
	//printing the number of assets
	System.out.println(element.size());
	inner1:for(int j=0;j<2;j++)
	{
		//opening each asset and check if the brand name is equal to the keyword entered
		WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[2]/div/div[4]/form/div/div/div[1]/div/article["+(j+1)+"]/a/img"));
		el.click();
		Thread.sleep(1000);
		inner2:for(int k=0;;k++)
		{
		//get the header of metadata one by one
		String header=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/h4")).getText();
		//get the value of that header one by one
		String value=driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[3]/div/div["+(k+1)+"]/p")).getText();
		System.out.println(header);
		System.out.println(value);
		if(value.equals(brand)==false && header.equals("ブランド")==false)
		{
			continue inner2;
		}
		else if(value.equals(brand)==false && header.equals("ブランド")==true)
		{
			return false;
		}
		else if(value.equals(brand)==true && header.equals("ブランド")==true)
		{
			driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div[1]/h1/a/i")).click();
			continue inner1;
		}
		}
	}
	return true;
}
}