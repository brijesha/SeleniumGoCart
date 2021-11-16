package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Utility;

public class CheckOutTest extends BaseTest {

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
	public void verifyRowsTest(String expectedProductName, int expectedQuantity, int expectedAmount, int position) {
		WebElement rowElem = checkoutPageObjects.row(position-1);

		String productName = rowElem.findElement(By.className("product-name")).getText().split(" ")[0];
		assertEquals(productName, expectedProductName, productName + " not present.");

		int productQuantity = Integer.parseInt(rowElem.findElement(By.className("quantity")).getText());
		assertEquals(productQuantity, expectedQuantity, productQuantity + " not present.");

		int productAmount = Integer.parseInt(rowElem.findElement(By.className("amount")).getText());
		assertEquals(productAmount, expectedAmount, productAmount + " not present.");
	}

	@DataProvider(name = "rowsData")
	public Object[][] rowsData() {
		return new Object[][] { { "Beans", 1, 82, 1 }, { "Cucumber", 3, 48, 2 } };
	}

	@Test(priority = 2)
	public void checkout2Test() {
		// verify totals
		assertEquals(Integer.parseInt(checkoutPageObjects.totalAmount()), 226,
				"Total amount mismatch.");
		assertEquals(checkoutPageObjects.discountPerc(), "0%", "Discount percentage mismatch.");
		assertEquals(Integer.parseInt(checkoutPageObjects.totalAfterDiscount()), 226,
				"Total after discount amount mismatch.");

		// proceed to Terms and Condition page 
		checkoutPageObjects.placeOrderBtn().click();

		// select country
		Select countrySelect = new Select(tcPageObjects.countrySelect());
		countrySelect.selectByValue("United States");
		
		// verify error scenario
		tcPageObjects.proceedBtn().click();
		if (tcPageObjects.errorMsg().getText() == null) {
			fail("Terms and Condition error message is not displaying");
		}
		
		// click terms and condition
		tcPageObjects.termCheckBox().click();
		
		// proceed to confirmation page
		tcPageObjects.proceedBtn().click();

	}

}