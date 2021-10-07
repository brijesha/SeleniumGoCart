package homeProject.cart;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Loading {
	WebDriver driver;

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
	public void checkEmptyCart() {
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		String displayMsg = driver.findElement(By.xpath("//div[@class='empty-cart']//h2")).getText();
		assertTrue(displayMsg.equals("You cart is empty!"), "Display message is incorrect");
		driver.findElement(By.className("products-wrapper")).click();

		validateElemIntZero("Items");
		validateElemIntZero("Price");

		String[] productArry = { "Brocolli", "Potato", "Mango" };
		checkProduct(productArry);
	}

	public void validateElemIntZero(String elemName) {
		List<WebElement> elems = driver.findElements(By.xpath(
				"//div[@class='cart-info']//td[contains(text(),'" + elemName + "')]/following-sibling::td[2]//strong"));
		if (elems.isEmpty()) {
			fail(elemName + " element not found");
		}
		WebElement elem = elems.get(0);
		String elemText = elem.getText();
		int elemInt = 0;
		try {
			elemInt = Integer.parseInt(elemText);
		} catch (NumberFormatException e) {
			fail(elemName + " value is not an integer");
		}
		assertEquals(elemInt, 0, elemName + " value is not zero");
	}

	public void checkProduct(String[] productArr) {
		for (int i = 0; i < productArr.length; i++) {
			try {
				driver.findElement(By.xpath("//h4[contains(text(),'" + productArr[i] + "')]"));
			} catch (NoSuchElementException e) {
				fail(productArr[i] + " is not displayed on page.");
			}
			
			String price=driver.findElement(By.xpath("//h4[contains(text(),'" +productArr[i]+"')]/following-sibling::p")).getText();
			System.out.println(price);
		}
		
		
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
