package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageObjects.HomePageObjects;
import util.Utility;

public class HomePageBaseTest {
	
	WebDriver driver;
	HomePageObjects homePageObjects;
	
	@BeforeTest
	public void createDriver() {
		// System.out.println("Initialize");
		System.setProperty("webdriver.gecko.driver", "D:\\programs\\selenniumdrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
		homePageObjects = new HomePageObjects(driver);
		// driver.manage().window().fullscreen();
	}
	
	public void checkProduct(String productName, int expectedProductPrice) {
		homePageObjects.getProduct(productName);
		String priceStr = homePageObjects.getProductPrice(productName);
		Utility.checkStrEqualsInt(priceStr, expectedProductPrice, productName + " price ");
	}
	
	@AfterTest
	public void tearDown() {
		try {driver.close();} catch (Exception e1) {}
		try {driver.quit();} catch (Exception e1) {}
	}


}
