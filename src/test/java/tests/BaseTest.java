package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageObjects.CheckoutPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.PlaceOrderPageObjects;
import pageObjects.TopDealsPageObjects;

public class BaseTest {

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

	@AfterTest
	public void tearDown() {
		try {
			driver.close();
		} catch (Exception e1) {
		}
		try {
			driver.quit();
		} catch (Exception e1) {
		}
	}

}
