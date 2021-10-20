package tests;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProductExcelTest extends HomePageBaseTest {

	@Test(dataProvider = "searchDataExcel")
	public void searchProductTest(String toSearch, String productName, int expectedPrice) {
		WebElement searchInput = homePageObjects.getSearchInput();
		// check value of searchbox
		String searchInputValue = searchInput.getAttribute("value");

		if (!toSearch.equals(searchInputValue)) {
			searchInput.clear();
			searchInput.sendKeys(toSearch);
		}
		checkProduct(productName, expectedPrice);
	}

	@DataProvider(name = "searchDataExcel")
	public Object[][] createData() throws Exception {
		Object[][] object = null;

		// location of excelsheet
		FileInputStream fis = new FileInputStream("C:\\Users\\Brijesha\\Downloads\\DataSheet.xlsx");
		// object of excelsheet
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// get correct sheet from number of sheets
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equals("search")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// iterating rows
				int firstRowNumber = sheet.getFirstRowNum();
				int lastRowNumber = sheet.getLastRowNum();
				for (int j = firstRowNumber; j <= lastRowNumber; j++) {
					XSSFRow row = sheet.getRow(j);
					// finding column value
					for (int k = 0; k < row.getLastCellNum(); k++) {
						object[j][k] = row.getCell(k).toString();
					}
				}
			}
		}
		return object;

		/*
		 * return new Object[][] { { "or", "Corn", 75 }, { "or", "Orange", 75 }, { "ca",
		 * "Cauliflower", 60 }, { "ca", "Carrot", 56 }, { "ca", "Capsicum", 60 }, {
		 * "ca", "Cashew", 650 } };
		 */
	}

}