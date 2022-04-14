package com.goCart.qa.pages;

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
	By productName = By.className("product-name");
	By quantity = By.className("quantity");
	By amount = By.className("amount");
	
	
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

	public int totalAmount() {
		return Integer.parseInt(driver.findElement(totalAmount).getText());
	}

	public String discountPerc() {
		return driver.findElement(discountPerc).getText();
	}

	public int totalAfterDiscount() {
		return Integer.parseInt(driver.findElement(totalAfterDiscount).getText());
	}
	
	public WebElement placeOrderBtn() {
		return driver.findElement(placeOrderBtn);
	}
	
	public String productName(int position) {
		return row(position).findElement(productName).getText().split(" ")[0];
	}
	
	public Integer quantity(int position) {
		return Integer.parseInt(row(position).findElement(quantity).getText());
	}
	
	public Integer amount(int position) {
		return Integer.parseInt(row(position).findElement(amount).getText());
	}
}
