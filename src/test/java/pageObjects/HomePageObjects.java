package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {

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

}
