package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPageObjects {

	WebDriver driver;

	By rows = By.xpath("//*[@class='cartTable']/tbody/tr");
	By numOfItems = By.xpath("//div[@class='products']/div/b[1]");
	By totalAmount = By.cssSelector(".totAmt");
	By discountPerc = By.cssSelector(".discountPerc");
	By totalAfterDiscount = By.cssSelector(".discountAmt");
	By placeOrderBtn = By.xpath("//button[text()='Place Order']");

	public CheckoutPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> rows() {
		return driver.findElements(rows);
	}
	
	public WebElement row(int position) {
		return driver.findElement(By.xpath("//*[@class='cartTable']/tbody/tr["+position+"]"));
	}

	public String numOfItems() {
		System.out.println(driver.findElement(numOfItems).getText());
		return driver.findElement(numOfItems).getText();
	}

	public String totalAmount() {
		return driver.findElement(totalAmount).getText();
	}

	public String discountPerc() {
		return driver.findElement(discountPerc).getText();
	}

	public String totalAfterDiscount() {
		return driver.findElement(totalAfterDiscount).getText();
	}
	
	public WebElement placeOrderBtn() {
		return driver.findElement(placeOrderBtn);
	}
}
