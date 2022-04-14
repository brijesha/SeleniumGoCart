package com.goCart.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.goCart.qa.base.BaseTest;
import com.goCart.qa.pages.CheckoutPageObjects;
import com.goCart.qa.pages.HomePageObjects;
import com.goCart.qa.pages.TCPageObjects;
import com.goCart.qa.util.Utility;

public class CheckOutTest extends BaseTest {
	public CheckOutTest() {
		super();
	}
	
	@BeforeClass
	public void setup() {
		initialization();
		homePageObjects = new HomePageObjects(driver);
		checkoutPageObjects = new CheckoutPageObjects(driver);
		tcPageObjects = new TCPageObjects(driver);
	}

	@Test(priority = 0)
	public void checkout1Test() {
		// add items to cart
		homePageObjects.addToCartBtn("Beans").click();
		for (int i = 0; i < 3; i++) { 
			homePageObjects.addToCartBtn("Cucumber").click();
		}

		// check price value
		String priceText = homePageObjects.getPriceText();
		Utility.checkStrEqualsInt(priceText, 226, "Price");

		// open cart pop-up
		homePageObjects.cartImgClick();

		// proceed to checkout page
		homePageObjects.checkoutBtn().click();

		// verify number of items
		List<WebElement> rowElems = checkoutPageObjects.rows();
		int numRows = rowElems.size();
		assertEquals(numRows, 2, "Number of items mismatch.");
	}

	@Test(dataProvider = "rowsData", priority = 1)
	public void verifyRowsTest(String expectedProductName, Integer expectedQuantity, Integer expectedAmount, Integer rowNum) {
		assertEquals(checkoutPageObjects.productName(rowNum), expectedProductName);
		assertEquals(checkoutPageObjects.quantity(rowNum), expectedQuantity);
		assertEquals(checkoutPageObjects.amount(rowNum), expectedAmount);
	}

	@DataProvider(name = "rowsData")
	public Object[][] rowsData() {
		return new Object[][] { { "Beans", 1, 82, 1 }, { "Cucumber", 3, 48, 2 } };
	}

	@Test(priority = 2)
	public void checkout2Test() {
		// verify totals
		assertEquals(checkoutPageObjects.totalAmount(), 226, "Total amount mismatch.");
		assertEquals(checkoutPageObjects.discountPerc(), "0%", "Discount percentage mismatch.");
		assertEquals(checkoutPageObjects.totalAfterDiscount(), 226,	"Total after discount amount mismatch.");

		// proceed to Terms and Condition page
		checkoutPageObjects.placeOrderBtn().click();

		// select country
		Select countrySelect = new Select(tcPageObjects.countrySelect());
		countrySelect.selectByValue("United States");

		// verify error scenario
		tcPageObjects.proceedBtn().click();
		String errorText = tcPageObjects.errorMsg().getText();
		
		assertEquals(errorText, "Please accept Terms & Conditions - Required","Terms and Condition error message mismatch.");
		
		// click terms and condition
		tcPageObjects.termCheckBox().click();

		// proceed to confirmation page
		tcPageObjects.proceedBtn().click();
		
		//confirm msg
		String confirmationMsg = tcPageObjects.confirmMsg().getText();
		assertEquals(confirmationMsg,"Thank you, your order has been placed successfully\n"+
				"You'll be redirected to Home page shortly!!","Confirmation message mismatch.");
		
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