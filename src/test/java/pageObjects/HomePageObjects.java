package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObjects {

	WebDriver driver;
	
	By cartImg = By.cssSelector("img[alt='Cart']");
	By emptyCartMsg = By.xpath("//div[@class='empty-cart']//h2");
	By items = By.xpath("//div[@class='cart-info']//td[contains(text(),'Items')]/following-sibling::td[2]//strong");
	By price = By.xpath("//div[@class='cart-info']//td[contains(text(),'Price')]/following-sibling::td[2]//strong");
	By searchInput = By.className("search-keyword");
	By cartItems= By.xpath("//div[@class='cart-preview active']//li[@class='cart-item']");
	By checkoutBtn = By.xpath("//div[@class='cart-preview active']/div[2]/button");
	By productName = By.className("product-name");
	
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

	public WebElement getProduct(String productName) {
		return driver.findElement(By.xpath("//h4[contains(text(),'"+ productName+"')]"));
	}

	public String getProductPrice(String productName) {
		return driver.findElement(By.xpath("//h4[contains(text(),'"+productName+"')]/following-sibling::p")).getText();
	}
	
	public WebElement getSearchInput() {
		return driver.findElement(searchInput);
	}
	
	public WebElement addToCartBtn(String productName) {
		return driver.findElement(By.xpath("//h4[contains(text(),'"+ productName +"')]/following-sibling::div[2]/button"));
	}
	
	public WebElement incrementBtn(String productName) {
		return driver.findElement(By.xpath("//h4[contains(text(),'"+ productName +"')]/following-sibling::div[1]/a[2]"));
	}
	
	public List<WebElement> cartItems() {
		return (List<WebElement>) driver.findElements(cartItems);
	}
	
	public WebElement checkoutBtn() {
		return driver.findElement(checkoutBtn);
	}
	
	public String getProductName(WebElement cartItemElem) {
		return cartItemElem.findElement(productName).getText();
	}
	
	public void removeProductFromCart(String productName) {
		driver.findElement(By.xpath("//div[@class='cart-preview active']//li[@class='cart-item']//p[contains(text(),'"+ productName +"')]/parent::div/following-sibling::a")).click();
	}
	
}
