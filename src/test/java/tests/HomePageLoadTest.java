package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageLoadTest extends BaseTest {

	@Test(priority = 0)
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

	@Test(dataProvider = "productData", priority = 1)
	public void checkProductTest(String productName, int expectedProductPrice) {
		homePageObjects.checkProductExist(productName, expectedProductPrice);
	}

	@DataProvider(name = "productData")
	public Object[][] createData() {
		return new Object[][] { { "Brocolli", 120 }, { "Potato", 22 }, { "Mango", 75 } };
	}

}
