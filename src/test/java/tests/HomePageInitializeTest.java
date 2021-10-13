package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;

public class HomePageInitializeTest {
	WebDriver driver;

	HomePageObjects homePageObjects;

	@BeforeTest
	public void createDriver() {
		// System.out.println("Initialize");
		System.setProperty("webdriver.gecko.driver", "D:\\programs\\selenniumdrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		// driver.manage().window().fullscreen();
	}

	@Test
	public void pageLoadTest() {
		homePageObjects = new HomePageObjects(driver);

		// check empty cart
		homePageObjects.cartImgClick();
		String cartMsg = homePageObjects.getEmptyCartMsg();
		assertEquals(cartMsg, "You cart is empty!", "Display message is incorrect");
		homePageObjects.cartImgClick();

		// check items value zero
		String itemsText = homePageObjects.getItemsText();
		validateElemIntZero("Items", itemsText);

		// check price value zero
		String priceText = homePageObjects.getPriceText();
		validateElemIntZero("Price", priceText);

		checkProducts();
	}

	public void checkProducts() {
		String[] productArr = { "Brocolli", "Potato", "Mango" };
		int[] priceArr = { 120, 22, 75 };
		for (int i = 0; i < productArr.length; i++) {
			String productName = productArr[i];
			homePageObjects.getProduct(productName);
			String price = homePageObjects.getProductPrice(productName);
			assertEquals(Integer.parseInt(price), priceArr[i], productName + " has incorrect price");
		}
	}

	// Check element value is zero
	public void validateElemIntZero(String elemName, String elemText) {
		int elemInt = 0;
		try {
			elemInt = Integer.parseInt(elemText);
		} catch (NumberFormatException e) {
			fail(elemName + " value is not an integer");
		}
		assertEquals(elemInt, 0, elemName + " value is not zero");
	}

	@AfterTest
	public void tearDown() {
		try {
			driver.close();
		} catch (Exception e1) {

		}

		try {
			driver.quit();
		} catch (Exception e) {

		}
	}

}
