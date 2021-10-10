package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {
	//String[] productArry = { "Brocolli", "Potato", "Mango" };
	WebDriver driver;

	By cartImg = By.cssSelector("img[alt='Cart']");
	By emptyCartMsg = By.xpath("//div[@class='empty-cart']//h2");
	By items = By.xpath("//div[@class='cart-info']//td[contains(text(),'Items')]/following-sibling::td[2]//strong");
	By price = By.xpath("//div[@class='cart-info']//td[contains(text(),'Price')]/following-sibling::td[2]//strong");

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void cartImgClick() {
		driver.findElement(cartImg).click();
	}
	
	public String getEmptyCartMsg() {
		return driver.findElement(emptyCartMsg).getText();
	}

	public String getItemsText() {
		return driver.findElement(items).getText();
	}
	
	public String getPriceText() {
		return driver.findElement(price).getText();
	}
	
/*
	// Check element value is zero
	public void validateElemIntZero(String elemName) {
		WebElement elem = getElem(items, elemName);
		String elemText = elem.getText();
		int elemInt = 0;
		try {
			elemInt = Integer.parseInt(elemText);
		} catch (NumberFormatException e) {
			fail(elemName + " value is not an integer");
		}
		assertEquals(elemInt, 0, elemName + " value is not zero");
	}

	public WebElement getElem(By xpath, String elemName) {
		List<WebElement> elems = driver.findElements(xpath);
		if (elems.isEmpty()) {
			fail(elemName + " element not found");
		}
		return elems.get(0);
	}*/
}
