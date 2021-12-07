package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TCPageObjects {

	WebDriver driver;

	By countrySelect = By.xpath("//select[@style='width: 200px;']");
	By termCheckBox = By.cssSelector(".chkAgree");
	By proceedBtn = By.tagName("button");
	By errorMsg = By.xpath("//span[@class='errorAlert']/b");
	By confirmMsg = By.xpath("//div[@class='products']//span");

	public TCPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement countrySelect() {
		return driver.findElement(countrySelect);
	}
	
	public WebElement termCheckBox() {
		return driver.findElement(termCheckBox);
	}
	
	public WebElement proceedBtn() {
		return driver.findElement(proceedBtn);
	}
	
	public WebElement errorMsg() {
		return driver.findElement(errorMsg);
	}
	
	public WebElement confirmMsg() {
		return driver.findElement(confirmMsg);
	}
}
