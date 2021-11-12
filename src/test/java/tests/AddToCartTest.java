package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToCartTest extends HomePageBaseTest {

	@Test(dataProvider = "addToCartData", priority = 0)
	public void addProduct(String itemName, int numItems, int totalPrice, boolean shouldPressIncrementBtn) {
		final WebElement addToCartBtn = homePageObjects.addToCartBtn(itemName);
		if (shouldPressIncrementBtn) {
			homePageObjects.incrementBtn(itemName).click();
		}

		addToCartBtn.click();

		// allow enough time for button color to change
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);

		// "add to cart" button background color should change
		String greenColorRGB = "rgb(7, 121, 21)";
		String addToCartBkColor = addToCartBtn.getCssValue("background-color");
		assertNotEquals(addToCartBkColor, greenColorRGB, "Add to cart button color not changed.");

		// add to cart button text should be updated to "ADDED"
		assertTrue(addToCartBtn.getText().contains("ADDED"), "Add to cart button text not updated");

		verifyCartTotals(numItems, totalPrice);
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
		verifyCartTotals(1, 144);
	}

	@DataProvider(name = "addToCartData")
	public Object[][] cartData() {
		return new Object[][] { { "Cucumber", 1, 48, false }, { "Cucumber", 1, 144, true },
				{ "Beans", 2, 226, false } };
	}

}
