package com.goCart.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.goCart.qa.base.BaseTest;
import com.goCart.qa.pages.HomePageObjects;

public class HomePageLoadTest extends BaseTest {
	
	public HomePageLoadTest() {
		super();
	}
	
	@BeforeClass
	public void setup() {
		initialization();
		homePageObjects = new HomePageObjects(driver);
	}
	
	@Test()
	public void pageLoadTest() {
		// open the cart pop-up
		homePageObjects.cartImgClick();

		// check empty cart message
		String cartMsg = homePageObjects.getEmptyCartMsg();
		assertEquals(cartMsg, "You cart is empty!", "Display message is incorrect");

		// close the cart pop-up
		homePageObjects.cartImgClick();

		homePageObjects.verifyCartTotals(0, 0);
	}

	@Test(dataProvider = "productData")
	public void checkProductTest(String productName, int expectedProductPrice) {
		homePageObjects.checkProductExist(productName, expectedProductPrice);
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

	@DataProvider(name = "productData")
	public Object[][] createData() {
		return new Object[][] { { "Brocolli", 120 }, { "Potato", 22 }, { "Mango", 75 } };
	}

	
}
