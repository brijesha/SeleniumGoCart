package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPageObjects {

	WebDriver driver;

	By countryDd = By.xpath("//select[@style='width: 200px;']");
	By termCb = By.cssSelector(".chkAgree");
	By proceedBtn = By.tagName("button");
	By errorMsg = By.xpath("//span[@class='errorAlert']/b");

	public CheckoutPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement countryDd() {
		return driver.findElement(countryDd);
	}
	
	public WebElement termCheckBox() {
		return driver.findElement(termCb);
	}
	
	public WebElement proccedBtn() {
		return driver.findElement(proceedBtn);
	}
	
	public WebElement errorMsg() {
		return driver.findElement(errorMsg);
	}
}
