package com.dappwithmm.qa.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.dappwithmm.qa.base.TestBase;

public class TestUtils extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 60;
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Xmart\\eclipse-workspace\\SonarWalletTest\\src\\main\\java\\com\\sonarwallet\\qa\\testdata\\SonarWallet.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
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
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
//	public void saveScreenshotToDisk(WebDriver driver, String methodName) {
//	    try {
//	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//	        String currentDir = System.getProperty("user.dir");
//	        File screenshotDir = new File(currentDir + "/screenshots");
//	        if (!screenshotDir.exists()) {
//	            screenshotDir.mkdirs();
//	        }
//	        String filePath = screenshotDir + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
//	        FileUtils.copyFile(scrFile, new File(filePath));
//	        System.out.println("Screenshot also saved to disk: " + filePath);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
	
	

}
