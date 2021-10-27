package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlaceOrderPageObjects {

	WebDriver driver;

	By rows = By.xpath("//*[@class='cartTable']/tbody/tr");
	By numOfItems = By.xpath("//div[@class='products']/div/b[1]");
	By totalAmount = By.cssSelector(".totAmt");
	By discount = By.cssSelector(".discountPerc");
	By totalDiscount = By.cssSelector(".discountAmt");
	By placeOrderBtn = By.xpath("//button[text()='Place Order']");

	public PlaceOrderPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> rows() {
		return driver.findElements(rows);
	}

	public String numOfItems() {
		System.out.println(driver.findElement(numOfItems).getText());
		return driver.findElement(numOfItems).getText();
	}

	public String totalAmount() {
		return driver.findElement(totalAmount).getText();
	}

	public String discount() {
		return driver.findElement(discount).getText();
	}

	public String totalDiscount() {
		return driver.findElement(totalDiscount).getText();
	}
	
	public WebElement placeOrderBtn() {
		return driver.findElement(placeOrderBtn);
	}
}
