package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Utility;

public class HomePageLoadTest extends HomePageBaseTest {

	@Test
	public void pageLoadTest() {
		// check empty cart
		homePageObjects.cartImgClick();
		String cartMsg = homePageObjects.getEmptyCartMsg();
		assertEquals(cartMsg, "You cart is empty!", "Display message is incorrect");
		homePageObjects.cartImgClick();

		// check items value zero
		String itemsText = homePageObjects.getItemsText();
		Utility.checkStrEqualsInt(itemsText, 0, "Items");

		// check price value zero
		String priceText = homePageObjects.getPriceText();
		Utility.checkStrEqualsInt(priceText, 0, "Price");
	}

	@Test(dataProvider = "productData")
	public void checkProductTest(String productName, int expectedProductPrice) {
		checkProduct(productName, expectedProductPrice);
	}

	@DataProvider(name = "productData")
	public Object[][] createData() {
		return new Object[][] { { "Brocolli", 120 }, { "Potato", 22 }, { "Mango", 75 } };
	}

}
