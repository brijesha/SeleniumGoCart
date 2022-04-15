package com.goCart.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class AddToCartTest extends BaseTest {
	public AddToCartTest() {
		super();
	}
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {
		initialization(browser);
		homePageObjects = new HomePageObjects(driver);
	}

	@Test(dataProvider = "addToCartData", priority = 0)
	public void addProduct(String itemName, int numItems, int totalPrice, boolean shouldPressIncrementBtn) throws InterruptedException {
		final WebElement addToCartBtn = homePageObjects.addToCartBtn(itemName);
		if (shouldPressIncrementBtn) {
			homePageObjects.incrementBtn(itemName).click();
		}
		// explicit wait
//		@SuppressWarnings("deprecation")
//		WebDriverWait wait = new WebDriverWait(driver, 5);
//		wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
		addToCartBtn.click();
		

		// "add to cart" button background color should change
		String greenColorRGB = "rgb(7, 121, 21)";
		String addToCartBkColor = addToCartBtn.getCssValue("background-color");
		assertNotEquals(addToCartBkColor, greenColorRGB, "Add to cart button color not changed.");

		// add to cart button text should be updated to "ADDED"
		assertTrue(addToCartBtn.getText().contains("ADDED"), "Add to cart button text not updated");

		Thread.sleep(1000);
		homePageObjects.verifyCartTotals(numItems, totalPrice);
	}

	@Test(priority = 1)
	public void checkCartItems() {
		// open cart pop-up
		homePageObjects.cartImgClick();

		// verify cart not empty
		List<WebElement> cartItemsElems = homePageObjects.cartItems();
		assertFalse(cartItemsElems.isEmpty(), "Cart empty.");

		String[] expectedItemsArr = { "Cucumber", "Beans" };

		// check number of items in cart should match expected items size
		assertEquals(cartItemsElems.size(), expectedItemsArr.length, "Items in the cart are mismatch.");

		// check items in cart are as expected and in order
		for (int i = 0; i < cartItemsElems.size(); i++) {
			WebElement cartItemElem = cartItemsElems.get(i);
			String cartItemName = homePageObjects.getProductName(cartItemElem);
			String expectedItemName = expectedItemsArr[i];
			assertTrue(cartItemName.contains(expectedItemName),
					expectedItemName + " is not found in cart or not in the right place.");
		}

		// remove item from cart
		homePageObjects.removeProductFromCart("Beans");

		// verify cart total
		homePageObjects.verifyCartTotals(1, 144);
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

	@DataProvider(name = "addToCartData")
	public Object[][] cartData() {
		return new Object[][] { 
			{ "Cucumber", 1, 48, false }, 
			{ "Cucumber", 1, 144, true },
			{ "Beans",    2, 226, false } };
	}

}
