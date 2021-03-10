package com.adastria.search.component;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MainMethod{
	
	static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ITestResult result;
	public static String screenshotPath;
	static int k=0;
	
	public static void invokeBrowser() {
		
		try {
			extent= new ExtentReports("/Users/ritik.shah/Desktop/アダストリア.html",true);
			test=extent.startTest("テスト 1: Browserが開く確認します");
			System.setProperty("webdriver.chrome.driver", "/Users/ritik.shah/Desktop/chromedriver");
			Map<String, Object> prefs = new HashMap<String, Object>();
	        // Use File.separator as it will work on any OS
	        prefs.put("download.default_directory","/Users/ritik.shah/Desktop/Check_Downloads");       
	        // Adding capabilities to ChromeOptions
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", prefs);
	        // Printing set download directory
	        //System.out.println(options.getExperimentalOption("prefs"));
	        // Launching browser with desired capabilities
	        driver= new ChromeDriver(options);
			//driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			test.log(LogStatus.INFO,"Google Chromeが開きます");
			driver.get("http://13.230.208.152:4503/content/cdam-portal/jp/ja/search.html");
			WaitForPage.waitForLoad(driver);
			//driver.get("http://localhost:34503/content/cdam-portal/jp/ja/search.html");
			//driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin");
			//Thread.sleep(1000);
			//driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("b2iztPtesQmc");
			//Thread.sleep(1000);
			//driver.findElement(By.xpath("//*[@id=\"submit-button\"]/coral-button-label")).click();
			//WaitForPage.waitForLoad(driver);
			test.log(LogStatus.INFO, "アダストリアウェブサイトが開きます");
			MainMenu_Test.mainmenu_test();
			/*
			test=extent.startTest("テスト 2: HomeページでキーワードSearch");
			Home1_SearchItem.searchItem();
			test=extent.startTest("テスト 3: ファイル種別を設定する（画像、ドキュメント、動画）");
			Home2_FileType.fileType();
			test=extent.startTest("テスト 4: 最終更新日を設定する");
			Home3_SetDate.startdate();
			Home3_SetDate.enddate();
			Home3_SetDate.startenddate();
			test=extent.startTest("テスト 5: 並び替え");
			Home4_DisplayOrder.vieworder();
			test=extent.startTest("テスト 6: 表示形式切り替え");
			Home5_ToggleDisplay.toggledisplay();
			test=extent.startTest("テスト 7: VMD_ページでキーワードSearch");
			VMD1_SearchItem.searchItem_vmd();
			test=extent.startTest("テスト 8: VMD_報告週");
			VMD2_ReportWeek.reportweek_vmd();
			test=extent.startTest("テスト 9: VMD_ブランド");
			VMD3_Brand.brand_vmd();
			test=extent.startTest("テスト 10: VMD_支店");
			VMD4_Branch.branch_vmd();
			test=extent.startTest("テスト 11: VMD_エリア");
			VMD5_Area_Branch.areas_branch();
			++k;
			VMD5_Area_Asset.areas_asset();
			test=extent.startTest("テスト 12: VMD_館");
			VMD6_Building_Area.building_area();
			VMD6_Building_Asset.asset_building();
			test=extent.startTest("テスト 13: VMD_報告種別");
			VMD7_ReportType.reporttype();
			test=extent.startTest("テスト 14: VMD_写真種別");
			VMD8_Phototype.phototype();
			test=extent.startTest("テスト 15: VMD_拠点");
			VMD9_Country.countrytype();
			test=extent.startTest("テスト 16: Product_ページでキーワードSearch");
			Product1_SearchItem.searchItem_product();
			test=extent.startTest("テスト 17: Product_年度");
			Product2_Year.year_product();
			test=extent.startTest("テスト 18: Product_シーズン");
			Product3_Season.season_product();
			test=extent.startTest("テスト 19: Product_ブランド");
			Product4_Brand.brand_product();
			test=extent.startTest("テスト 20: Product_カテゴリ");
			Product5_Category_Brand.brand_category();
			Product5_Category_Asset.category_asset();
			test=extent.startTest("テスト 21: Product_部門");
			Product6_Department_Brand.department_brand();
			Product6_Department_Assets.department_asset();
			test=extent.startTest("テスト 22: Product_大品種");
			Product7_LargeVariety_Department.largevariety_department();
			Product7_LargeVariety_Assets.largevariety_asset();
			test=extent.startTest("テスト 23: Product_品種");
			Product8_Variety_LargeVariety.variety_largevariety();
			Product8_Variety_Assets.variety_asset();
			test=extent.startTest("テスト 24: Product_メイン画像フラグ");
			Product9_MainImage.mainimage();
			test=extent.startTest("テスト 25: Product_当初売価");
			Product10_InitialSellPrice.initialsell();
			test=extent.startTest("テスト 26: Product_カラー");
			Product11_Colour.colour_product();
			test=extent.startTest("テスト 27: Product_現売価");
			Product12_CurrentSellPrice.currentsell();
			test=extent.startTest("テスト 28: Product_撮影日");
			Product13_ShootingDate.startenddate();
			test=extent.startTest("テスト 29: Product_統一カテゴリ");
			Product14_UnifiedCategory.category_product();
			test=extent.startTest("テスト 30: Product_統一部門");
			Product15_UnifiedDepartment.department_assets();
			test=extent.startTest("テスト 31: Product_統一大品種");
			Product16_UnifiedLargeVariety.largevariety_assets();
			test=extent.startTest("テスト 32: Product_統一品種");
			Product17_UVariety_ULargeVariety.variety_largevariety();
			Product17_UVariety_Assets.variety_asset();
			test=extent.startTest("テスト 33: FAQ表示");
			Display_FAQ.displayfaq();
			test=extent.startTest("テスト 34: ASC_メタデータ");
			ASC_Metadata.metadata_display();
			ASC_LastUpdateDate.displaylastupdatedate();
			ASC_Type.display_type();
			ASC_Size.displaysize();
			ASC_Resolution.displayresolution();
			test=extent.startTest("テスト 35: ASC_Actions");
			ASC_Actions.check_download();
			test=extent.startTest("テスト 36: ASC_Renditions");
			ASC_Renditions.check_renditions();
			test=extent.startTest("テスト 37: ASC_メタデータ「ドキュメント」");
			ASC_Document_Metadata.metadata_display();
			ASC_Document_LastUpdateDate.displaylastupdatedate();
			ASC_Document_Type.display_type();
			ASC_Document_Size.displaysize();
			test=extent.startTest("テスト 38: ASC_Actions「ドキュメント」");
			ASC_Document_Actions.check_download();
			test=extent.startTest("テスト 39: ASC_Renditions「ドキュメント」");
			ASC_Document_Renditions.check_renditions();
			test=extent.startTest("テスト 40: ASC_メタデータ「動画」");
			ASC_Video_Metadata.metadata_display();
			ASC_Video_LastUpdateDate.displaylastupdatedate();
			ASC_Video_Type.display_type();
			ASC_Video_Size.displaysize();
			ASC_Video_Resolution.displayresolution();
			test=extent.startTest("テスト 41: ASC_Actions「動画」");
			ASC_Video_Actions.check_download();
			test=extent.startTest("テスト 42: ASC_Renditions「動画」");
			ASC_Video_Renditions.check_renditions();
			//run test case 43 and 44 together
			test=extent.startTest("テスト 43: ダウンロードモーダル");
			Download_List_Modal.download_asset();
			test=extent.startTest("テスト 44: リストモーダル");
			Download_List_Modal.list_asset();
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.endTest(test);
		extent.flush();
		driver.close();
	}
	
public static void main(String[] args) {
			    MainMethod.invokeBrowser();
	}
}
