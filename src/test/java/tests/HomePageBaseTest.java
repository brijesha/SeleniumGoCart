package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageObjects.CheckoutPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.PlaceOrderPageObjects;
import pageObjects.TopDealsPageObjects;
import util.Utility;

public class HomePageBaseTest {
	
	WebDriver driver;
	HomePageObjects homePageObjects;
	PlaceOrderPageObjects placeOrderPageObjects;
	CheckoutPageObjects checkoutPageObject;
	TopDealsPageObjects topDealPageObject;
	
	@BeforeTest
	public void createDriver() {
		// System.out.println("Initialize");
		System.setProperty("webdriver.gecko.driver", "D:\\programs\\selenniumdrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	
		// Provide value to object
		homePageObjects = new HomePageObjects(driver);
		placeOrderPageObjects = new PlaceOrderPageObjects(driver);
		checkoutPageObject = new CheckoutPageObjects(driver);
		topDealPageObject = new TopDealsPageObjects(driver);
	}
	
	public void checkProductExist(String productName, int expectedProductPrice) {
		// check if product element exists on page
		homePageObjects.getProduct(productName);
		
		// verify price match
		String priceStr = homePageObjects.getProductPrice(productName);
		Utility.checkStrEqualsInt(priceStr, expectedProductPrice, productName + " price ");
	}

	public void searchProduct(String toSearch, String productName, int expectedPrice) {
		WebElement searchInput = homePageObjects.getSearchInput();

		// get existing value of searchbox
		String searchInputValue = searchInput.getAttribute("value");

		// if present value is not the desired one, replace it
		if (!toSearch.equals(searchInputValue)) {
			searchInput.clear();
			searchInput.sendKeys(toSearch);
		}

		checkProductExist(productName, expectedPrice);
	}
	
	public void verifyCartTotals(int numItems, int price) {
		// check items value
		String itemsText = homePageObjects.getItemsText();
		Utility.checkStrEqualsInt(itemsText, numItems, "Items");

		// check price value
		String priceText = homePageObjects.getPriceText();
		Utility.checkStrEqualsInt(priceText, price, "Price");
	}
	
	@AfterTest
	public void tearDown() {
		try {driver.close();} catch (Exception e1) {}
		try {driver.quit();} catch (Exception e1) {}
	}


}
