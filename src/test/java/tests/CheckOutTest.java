package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Utility;

public class CheckOutTest extends HomePageBaseTest {
	
	@Test()
	public void checkOut() {
			homePageObjects.addToCartBtn("Beans").click();
			for(int i=0;i<3;i++) {
				homePageObjects.addToCartBtn("Cucumber").click();
			}
			// check price value
			String priceText = homePageObjects.getPriceText();
			Utility.checkStrEqualsInt(priceText, 226, "Price");
			
			homePageObjects.cartImgClick();
			homePageObjects.checkoutBtn().click();
			
			List<WebElement> rows = placeOrderPageObjects.rows();
			int numRows = placeOrderPageObjects.rows().size();
			assertEquals(numRows, 2, "Rows missing in place order table.");
			for(int i=0;i<rows.size()-1;i++) {
				String[] productName = rows.get(i).findElement(By.className("product-name")).getText().split(" ");
				assertEquals(productName[0], "Beans",productName +" not available in place order table.");
				
				int productQuantity = Integer.parseInt(rows.get(i).findElement(By.className("quantity")).getText());
				assertEquals(productQuantity, 1,productQuantity +" not available in place order table.");
				
				int productAmount = Integer.parseInt(rows.get(i).findElement(By.className("amount")).getText());
				assertEquals(productAmount, 82,productAmount +" not available in place order table.");
			}
			
			//assertEquals(Integer.parseInt(placeOrderPageObjects.numOfItems()), 2,"Number of items mismatch in place order table.");
			assertEquals(Integer.parseInt(placeOrderPageObjects.totalAmount()), 226,"Total amount mismatch in place order table.");
			assertEquals(placeOrderPageObjects.discount(), "0%","Discount amount mismatch in place order table.");
			assertEquals(Integer.parseInt(placeOrderPageObjects.totalDiscount()), 226,"Total discount amount mismatch in place order table.");
		
			placeOrderPageObjects.placeOrderBtn().click();
	}

}	