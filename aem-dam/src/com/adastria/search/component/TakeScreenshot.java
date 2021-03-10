package com.adastria.search.component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir") + screenshotName + dateName + ".png";
		System.out.println(destination);
		File finalDestination=new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
