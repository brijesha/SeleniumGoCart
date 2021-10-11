package pageObjects;

import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObjects {
	// String[] productArry = { "Brocolli", "Potato", "Mango" };
	WebDriver driver;

	By cartImg = By.cssSelector("img[alt='Cart']");
	By emptyCartMsg = By.xpath("//div[@class='empty-cart']//h2");
	By items = By.xpath("//div[@class='cart-info']//td[contains(text(),'Items')]/following-sibling::td[2]//strong");
	By price = By.xpath("//div[@class='cart-info']//td[contains(text(),'Price')]/following-sibling::td[2]//strong");

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void cartImgClick() {
		getElem(cartImg, "Cart").click();
	}

	public String getEmptyCartMsg() {
		return getElem(emptyCartMsg, "Cart message").getText();
	}

	public String getItemsText() {
		return getElem(items, "Items").getText();
	}

	public String getPriceText() {
		return getElem(price, "Price").getText();
	}

	public WebElement getElem(By locator, String elemName) {
		List<WebElement> elems = driver.findElements(locator);
		if (elems.isEmpty()) {
			fail(elemName + " element not found");
		}
		return elems.get(0);
	}
	/*
	 * // Check element value is zero public void validateElemIntZero(String
	 * elemName) { WebElement elem = getElem(items, elemName); String elemText =
	 * elem.getText(); int elemInt = 0; try { elemInt = Integer.parseInt(elemText);
	 * } catch (NumberFormatException e) { fail(elemName +
	 * " value is not an integer"); } assertEquals(elemInt, 0, elemName +
	 * " value is not zero"); }
	 * 
	 */
}
