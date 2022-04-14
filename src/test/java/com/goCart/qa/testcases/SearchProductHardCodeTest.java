package com.goCart.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.goCart.qa.base.BaseTest;
import com.goCart.qa.pages.HomePageObjects;

public class SearchProductHardCodeTest extends BaseTest {
	
	public SearchProductHardCodeTest() {
		super();
	}
	
	@BeforeClass
	public void setup() {
		initialization();
		homePageObjects = new HomePageObjects(driver);
	}
	
	@Test(dataProvider = "searchDataHardCode")
	public void searchProductTest(String toSearch, String productName, int expectedPrice) {
		homePageObjects.searchProduct(toSearch, productName, expectedPrice);
	}

	@DataProvider(name = "searchDataHardCode")
	public Object[][] createData() {
		return new Object[][] { { "or", "Corn", 75 }, { "or", "Orange", 75 }, { "ca", "Cauliflower", 60 },
				{ "ca", "Carrot", 56 }, { "ca", "Capsicum", 60 }, { "ca", "Cashew", 650 } };
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
	
	

}