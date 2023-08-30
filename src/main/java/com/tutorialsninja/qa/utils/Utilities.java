package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	public static String GenerateEmailWithTimeStamp() {

		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "varun"+timestamp+"@gmail.com";
	}
	
	public static Object[][] GetTestDataFromExcel(String SheetName) {
		
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\NinjaTestData.xlsx");
		XSSFWorkbook workbook=null;
		try {
		FileInputStream fis = new FileInputStream(file);
		 workbook = new XSSFWorkbook(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(SheetName);
		int rows = sheet.getLastRowNum();
		int cells = sheet.getRow(0).getLastCellNum();
		Object [][] data= new Object [rows][cells];
		
		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cells;j++) {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				switch(celltype) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				}
			}
		}
		return data;
	}
	
	public static String caputureScreeshot(WebDriver driver, String testname) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshot=System.getProperty("user.dir")+"\\Screenshots\\"+testname+".png";
		try {
			FileHandler.copy(screenshot,new File(destinationScreenshot));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destinationScreenshot;
	}
	
  
}
