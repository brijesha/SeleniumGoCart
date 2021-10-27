package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import util.Utility;

public class AddToCartTest extends HomePageBaseTest {
	@Test
	public void addProduct() {
		String[] productName = { "Cucumber", "Beans" };

		homePageObjects.addToCartBtn(productName[0]).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.MILLISECONDS);
		assertNotEquals(homePageObjects.addToCartBtn(productName[0]).getCssValue("background-color"), "rgb(7, 121, 21)",
				"Add to cart button color not changed.");
		String changedText[] = homePageObjects.addToCartBtn(productName[0]).getText().split(" ");
		assertEquals(changedText[1], "ADDED", "Add to cart button text not changed");
		itemsAndPricesCheckInCart(1, 48);

		homePageObjects.incrementBtn(productName[0]).click();
		homePageObjects.addToCartBtn(productName[0]).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MILLISECONDS);
		itemsAndPricesCheckInCart(1, 144);

		homePageObjects.addToCartBtn(productName[1]).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MILLISECONDS);
		itemsAndPricesCheckInCart(2, 226);

		homePageObjects.cartImgClick();

		List<WebElement> cartItemsElems = homePageObjects.cartItems();
		for (int i = 0; i < cartItemsElems.size(); i++) {
			String itemName = cartItemsElems.get(i).findElement(By.className("product-name")).getText();
			for (int j = 0; j < productName.length; j++) {
				if (i == j) {
					if (!(itemName.contains(productName[j]))) {
						fail(productName[j]+" is not added to cart.");
					}
				}
			}

		}
		//remove item from list
		for(int i=0;i<cartItemsElems.size();i++) {
			String itemName = cartItemsElems.get(i).findElement(By.className("product-name")).getText();
			if(itemName.contains(productName[1])) {
				cartItemsElems.get(i).findElement(By.className("product-remove")).click();
				break;
			}
		}
		itemsAndPricesCheckInCart(1, 144);
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