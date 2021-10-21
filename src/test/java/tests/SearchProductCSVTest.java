package tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProductCSVTest extends HomePageBaseTest {

	@Test(dataProvider = "searchDataCSV")
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

	@DataProvider(name = "searchDataCSV")
	public Object[][] createData() throws Exception {
		String fileData = FileUtils
				.readFileToString(new File("D:\\eclipseworkspace\\cart\\src\\test\\resources\\DataSheet.csv"));

		String[] lineArr = fileData.split("\r\n");
		Object[][] objectArr = new Object[lineArr.length-1][3];

		for (int i = 1; i < lineArr.length; i++) {
			String[] itemsArr = lineArr[i].split(",");
			objectArr[i - 1][0] = itemsArr[0];
			objectArr[i - 1][1] = itemsArr[1];
			objectArr[i - 1][2] = Integer.parseInt(itemsArr[2]);
		}

		return objectArr;
	}

}