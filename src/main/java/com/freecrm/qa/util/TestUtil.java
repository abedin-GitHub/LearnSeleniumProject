package com.freecrm.qa.util;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import com.freecrm.qa.base.TestBase;



public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT=40;
	public static long IMPLICIT_WAIT=20;
	
//	public static String TESTDATA_SHEET_PATH = "C:/Users/mozahidur.abedi/Desktop/free_crm_testData.xls";
	public static String TESTDATA_SHEET_PATH = (System.getProperty("user.dir")) 
			+ "/src/main/java/com/freecrm/qa/testdata/free_crm_testData.xls";
	
	
//	public static final String CHROME_DRIVER_EXE="C:/Users/mozahidur.abedi/eclipse-workspace/AbedinFreeCRMTest/ChromeDriver/chromedriver.exe";
	
	public static final String CHROME_DRIVER_EXE=(System.getProperty("user.dir"))+ "/ChromeDriver/chromedriver.exe";
	

	
	
	static Workbook book;
	static Sheet sheet;
	static Row row;
	
	
	
	public void switchToFrame()
	{
		driver.switchTo().frame("mainpanel");
	}
	
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	
	public static List<HashMap<String, String>> readExcelNameEmail()
	{
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet("name_email");

		List<HashMap<String, String>> entireTable = new ArrayList<HashMap<String, String>>();
	//	Iterator<HashMap<String, String>> itr = null;

		HashMap<String, String> eachRow;
		
		//Iterator<Entry<String, String>> it;
		

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			row = sheet.getRow(i);
			String keyCell = row.getCell(0).getStringCellValue();
			int colNum = row.getLastCellNum();
			
			 eachRow = new HashMap<String, String>();

			for (int j = 1; j < colNum; j++) {
				String value = row.getCell(j).getStringCellValue();
			//	System.out.println(keyCell + " = " + value);
				
			//	eachRow.put(keyCell, value);
			//	System.out.println(eachRow.get(keyCell));
				
		//		System.out.println("name is ="+ keyCell);
		//		System.out.println("email is ="+ value);
				eachRow.put("name", keyCell);
				eachRow.put("email", value);
				Iterator<Entry<String, String>> it = eachRow.entrySet().iterator();
				
				while(it.hasNext())
				{
				Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
		//		System.out.println("output from util11111: " + pair.getKey() + " = " + pair.getValue());
				}
				entireTable.add(eachRow);
				
			}
		//	entireTable.add(eachRow);
			
			
		//	System.out.println("--------------------");

		}
		
		
		
		Iterator<HashMap<String, String>> itrUtil= entireTable.iterator();
		
		
		while(itrUtil.hasNext())
		{
	
			eachRow=itrUtil.next();
			Iterator<Entry<String, String>> it = eachRow.entrySet().iterator();
	
			while(it.hasNext())
			{
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			System.out.println("output from util: " + pair.getKey() + " = " + pair.getValue());
			}
			
			System.out.println("--------This is from Util-----------");
		}
		
		
		
		return entireTable ;
	}
	
	
	
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile (scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		
		}
	
	
	public static void sendKeysWithExplicitWait(WebDriver driver, WebElement element, int timeOuts, String value)
	{
		new WebDriverWait(driver,timeOuts).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}
	
	
	public static void checkElemenetPresent(WebDriver driver, WebElement element, int timeOuts)
	{
		new WebDriverWait(driver,timeOuts).until(ExpectedConditions.invisibilityOf(element)) ;  //visibilityOf(element));
	
	}
	
	public static boolean comparemaps(HashMap<String, String> actualMap, HashMap<String, String> expected) {

		try {
			for (String k : expected.keySet()) {

				if (!actualMap.get(k).trim().equalsIgnoreCase(expected.get(k).trim())) {

					return false;
				}
			}
			for (String y : actualMap.keySet()) {
				if (!expected.containsKey(y)) {
					return false;
				}
			}
		} catch (NullPointerException np) {
			return false;
		}
		return true;
	}

	
	
	
		


	
	
	

}
