package tests;

import static org.testng.Assert.assertEquals;

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
		assertEquals(itemsText, "0", "Items value is not zero.");

		// check price value zero
		String priceText = homePageObjects.getPriceText();
		assertEquals(priceText, "0", "Price is not zero.");

		/*
		 * validateElemIntZero("Items"); validateElemIntZero("Price");
		 * 
		 * String[] productArry = { "Brocolli", "Potato", "Mango" };
		 * checkProduct(productArry);
		 */ }

	/*// Check product is displayed on page
	public void checkProduct(String[] productArr) {
		for (int i = 0; i < productArr.length; i++) {
			String xpath = "//h4[contains(text(),'" + productArr[i] + "')]";
			getElem(xpath, productArr[i]);

			String price = driver
					.findElement(By.xpath("//h4[contains(text(),'" + productArr[i] + "')]/following-sibling::p"))
					.getText();

			System.out.println(price);
		}

	}
*/
	/*// Check element value is zero
	public void validateElemIntZero(String elemName) {
		String xpath = "//div[@class='cart-info']//td[contains(text(),'" + elemName
				+ "')]/following-sibling::td[2]//strong";
		WebElement elem = getElem(xpath, elemName);
		String elemText = elem.getText();
		int elemInt = 0;
		try {
			elemInt = Integer.parseInt(elemText);
		} catch (NumberFormatException e) {
			fail(elemName + " value is not an integer");
		}
		assertEquals(elemInt, 0, elemName + " value is not zero");
	}

	public WebElement getElem(String xpath, String elemName) {
		List<WebElement> elems = driver.findElements(By.xpath(xpath));
		if (elems.isEmpty()) {
			fail(elemName + " element not found");
		}
		return elems.get(0);
	}
*/
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
