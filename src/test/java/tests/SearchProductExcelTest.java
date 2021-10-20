package tests;

import static org.testng.Assert.fail;

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
		Object[][] objectArr = null;
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;

		try {
			// location of excelsheet
			fis = new FileInputStream("D:\\eclipseworkspace\\cart\\DataSheet.xlsx");
			// object of excelsheet
			workbook = new XSSFWorkbook(fis);

			// get correct sheet from number of sheets
			int sheets = workbook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < sheets; sheetIndex++) {
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
			if (fis != null)
				fis.close();
		}
		return objectArr;

		/*
		 * return new Object[][] { { "or", "Corn", 75 }, { "or", "Orange", 75 }, { "ca",
		 * "Cauliflower", 60 }, { "ca", "Carrot", 56 }, { "ca", "Capsicum", 60 }, {
		 * "ca", "Cashew", 650 } };
		 */
	}

}