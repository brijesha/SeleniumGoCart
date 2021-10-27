package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Utility;

public class AddToCartTest extends HomePageBaseTest {
	
	@Test(dataProvider = "addToCartData")
	public void addProduct(String item, int numItem, int price, int counter) {
		if (counter == 0) {
			homePageObjects.addToCartBtn(item).click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.MILLISECONDS);

			if (item.equals("Cucumber")) {
				assertNotEquals(homePageObjects.addToCartBtn(item).getCssValue("background-color"), "rgb(7, 121, 21)",
						"Add to cart button color not changed.");
				String changedText[] = homePageObjects.addToCartBtn(item).getText().split(" ");
				assertEquals(changedText[1], "ADDED", "Add to cart button text not changed");

			}
			itemsAndPricesCheckInCart(numItem, price);
		} else {
			homePageObjects.incrementBtn(item).click();
			homePageObjects.addToCartBtn(item).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MILLISECONDS);
			itemsAndPricesCheckInCart(numItem, price);
		}
	}

	@Test
	public void checkCartItems() {
		homePageObjects.cartImgClick();
		String[] itemsArr = {"Cucumber","Beans"};

		List<WebElement> cartItemsElems = homePageObjects.cartItems();
		for (int i = 0; i < cartItemsElems.size(); i++) {
			String itemName = cartItemsElems.get(i).findElement(By.className("product-name")).getText();
			for (int j = 0; j < itemsArr.length; j++) {
				if (i == j) {
					if (!(itemName.contains(itemsArr[j]))) {
						fail(itemsArr[j] + " is not added to cart.");
					}
				}
			}
		}
		// remove item from list
		for (int i = 0; i < cartItemsElems.size(); i++) {
			String itemName = cartItemsElems.get(i).findElement(By.className("product-name")).getText();
			if (itemName.contains(itemsArr[1])) {
				cartItemsElems.get(i).findElement(By.className("product-remove")).click();
				break;
			}
		}
		itemsAndPricesCheckInCart(1, 144);
	}

	@DataProvider(name = "addToCartData")
	public Object cartData() {
		return new Object[][] { { "Cucumber", 1, 48, 0 }, { "Cucumber", 1, 144, 1 }, { "Beans", 2, 226, 0 } };
	}
	
	public void itemsAndPricesCheckInCart(int numItems, int price) {
		// check items value
		String itemsText = homePageObjects.getItemsText();
		Utility.checkStrEqualsInt(itemsText, numItems, "Items");

		// check price value
		String priceText = homePageObjects.getPriceText();
		Utility.checkStrEqualsInt(priceText, price, "Price");
	}

}
