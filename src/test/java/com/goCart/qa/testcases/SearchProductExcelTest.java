package com.goCart.qa.testcases;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.goCart.qa.base.BaseTest;
import com.goCart.qa.pages.HomePageObjects;

public class SearchProductExcelTest extends BaseTest {
	
	public SearchProductExcelTest() {
		super();
	}
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {
		initialization(browser);
		homePageObjects = new HomePageObjects(driver);
	}

	@Test(dataProvider = "searchDataExcel")
	public void searchProductTest(String toSearch, String productName, int expectedPrice) {
		System.out.println("child class2----");
		homePageObjects.searchProduct(toSearch, productName, expectedPrice);
	}
	
	@AfterClass
	public void tearDown() {
		try {
			driver.close();
		}catch(Exception e1) {}
		try {
			driver.quit();
		}catch(Exception e1) {}	
	}

	@DataProvider(name = "searchDataExcel")
	public Object[][] createData() throws Exception {
		Object[][] objectArr = null;
		InputStream is = null;
		XSSFWorkbook workbook = null;

		try {
			// location of excelsheet
			is = new FileInputStream(
					"/Users/brijesha/eclipse-workspace/SeleniumGoCart/src/main/java/com/goCart/qa/testdata/DataSheet.xlsx");
			// object of excelsheet
			workbook = new XSSFWorkbook(is);

			// get correct sheet from number of sheets
			int numOfSheets = workbook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < numOfSheets; sheetIndex++) {
				if (workbook.getSheetName(sheetIndex).equals("search")) {
					XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
					int lastRowNumber = sheet.getLastRowNum();
					objectArr = new Object[lastRowNumber][3];

					// iterating rows (skipping top row)
					for (int rowIndex = 1; rowIndex <= lastRowNumber; rowIndex++) {
						XSSFRow excelRow = sheet.getRow(rowIndex);

						// take row array
						Object[] dataRowArr = objectArr[rowIndex - 1];
						dataRowArr[0] = excelRow.getCell(0).getStringCellValue();
						dataRowArr[1] = excelRow.getCell(1).getStringCellValue();
						dataRowArr[2] = (int) (excelRow.getCell(2).getNumericCellValue());
					}
				}
			}
		} catch (Exception e) {
			fail("Exception reading data from excel", e);
		} finally {
			if (workbook != null)
				workbook.close();
			if (is != null)
				is.close();
		}

		return objectArr;
	}

}