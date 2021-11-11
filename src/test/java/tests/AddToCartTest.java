package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToCartTest extends HomePageBaseTest {

	@Test(dataProvider = "addToCartData", priority = 0)
	public void addProduct(String item, int numItem, int price, boolean shouldPressIncrementBtn) {
		final WebElement addToCartBtn = homePageObjects.addToCartBtn(item);
		if (shouldPressIncrementBtn) {
			homePageObjects.incrementBtn(item).click();
		}

		addToCartBtn.click();

		// allow enough time for button color to change
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);

		// add to cart button background color should change
		String greenColorRGB = "rgb(7, 121, 21)";
		String addToCartBkColor = addToCartBtn.getCssValue("background-color");
		assertNotEquals(addToCartBkColor, greenColorRGB, "Add to cart button color not changed.");

		// add to cart button text should be updated to "ADDED"
		assertTrue(addToCartBtn.getText().contains("ADDED"), "Add to cart button text not updated");

		verifyCartTotals(numItem, price);
	}

	@Test(priority = 1)
	public void checkCartItems() {
		// open cart pop-up
		homePageObjects.cartImgClick();

		// verify cart not empty
		List<WebElement> cartItemsElems = homePageObjects.cartItems();
		assertFalse(cartItemsElems.isEmpty(), "Cart empty.");

		// check items in cart
		String[] expectedItemsArr = { "Cucumber", "Beans" };
		assertEquals(cartItemsElems.size(), expectedItemsArr.length, "Items in the cart are mismatch.");

		for (int i = 0; i < cartItemsElems.size(); i++) {
			String cartItemName = cartItemsElems.get(i).findElement(By.className("product-name")).getText();
			String expectedItemName = expectedItemsArr[i];
			assertTrue(cartItemName.contains(expectedItemName),
					expectedItemName + " is not found in cart or not in the right place.");
		}

		// remove item from list
		for (int i = 0; i < cartItemsElems.size(); i++) {
			String itemName = cartItemsElems.get(i).findElement(By.className("product-name")).getText();
			if (itemName.contains(expectedItemsArr[1])) {
				cartItemsElems.get(i).findElement(By.className("product-remove")).click();
				break;
			}
		}
		verifyCartTotals(1, 144);
	}

	@DataProvider(name = "addToCartData")
	public Object[][] cartData() {
		return new Object[][] { { "Cucumber", 1, 48, false }, { "Cucumber", 1, 144, true },
				{ "Beans", 2, 226, false } };
	}

}
