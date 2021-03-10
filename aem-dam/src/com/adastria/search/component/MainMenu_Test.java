package com.adastria.search.component;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainMenu_Test extends MainMethod{
	
	public static void mainmenu_test() throws IOException {
		try {
			List<WebElement> list = driver.findElements(By.xpath("/html/body/div/div/div/div[1]/div/div/div[1]/header/div/div/child::a"));
			System.out.println(list.size());
			int itemlength=list.size()-2;
			for(int i=0;i<itemlength;i++)
			{
				WebElement el=driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div/div[1]/header/div/div/a["+(i+1)+"]"));
				if(el.getText().equalsIgnoreCase("HOME")==true)
				{
					/*
					test=extent.startTest("テスト 2: HomeページでキーワードSearch");
					Home1_SearchItem.searchItem();
					*/
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
					test=extent.startTest("テスト 7: StylingかSNSの商品");
					Home6_Path.path_type();
				}
				/*
				else if(el.getText().equalsIgnoreCase("VMD"))
				{
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
				}
				else if(el.getText().equalsIgnoreCase("商品")==true)
				{
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
				}
				else if(el.getText().equalsIgnoreCase("スタッフボード")==true)
				{
					test=extent.startTest("テスト 33: スタッフボード_EC用着画");
					StaffBoard1_EC.staffboard_ec();
					test=extent.startTest("テスト 34: スタッフボード_コーディネート画像");
					StaffBoard2_Coordinate_Image.staffboard_coordinate_image();
				}
				else if(el.getText().equalsIgnoreCase("トレンド")==true)
				{
					test=extent.startTest("テスト 35: スタッフボード_コーディネート画像");
					Trend.trend();
				}
				else if(el.getText().equalsIgnoreCase("FAQ")==true)
				{
					test=extent.startTest("テスト 33: FAQ表示");
					Display_FAQ.displayfaq();
				}
				*/
			}
		}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}
}
}
