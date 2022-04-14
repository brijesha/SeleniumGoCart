package com.goCart.qa.testcases;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.goCart.qa.base.BaseTest;
import com.goCart.qa.pages.HomePageObjects;

public class SearchProductCSVTest extends BaseTest {
	public SearchProductCSVTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		homePageObjects = new HomePageObjects(driver);
	}
	
	@Test(dataProvider = "searchDataCSV")
	public void searchProductTest(String toSearch, String productName, int expectedPrice) {
		homePageObjects.searchProduct(toSearch, productName, expectedPrice);
	}
	
	@AfterMethod
	public void tearDown() {
		try {
			driver.close();
		}catch(Exception e1) {}
		try {
			driver.quit();
		}catch(Exception e1) {}	
	}

	@DataProvider(name = "searchDataCSV")
	public Object[][] createData() throws Exception {
		String fileData = FileUtils
				.readFileToString(new File("Users/brijesha/eclipse-workspace/SeleniumGoCart/src/main/java/com/goCart/qa/testdata/DataSheet.csv"));

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