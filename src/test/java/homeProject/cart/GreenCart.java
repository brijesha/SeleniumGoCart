package homeProject.cart;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GreenCart {
	WebDriver driver;

	@BeforeTest
	public void createDriver() {
		System.out.println("Initialize");
		System.setProperty("webdriver.chrome.driver", "D:\\programs\\selenniumdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void emptyCart() {
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		String msg = "You cart is empty!";
		String displayMsg  = driver.findElement(By.xpath("//div[@class='empty-cart']//h2")).getText();
		System.out.println(displayMsg);
		driver.findElement(By.className("products-wrapper")).click(); 
	}
	
	@Test(priority=1)
	public void addItemsInCart() {
		int j=0;
		String[] addToCartProduct = {"Beetroot","Tomato","Capsicum"};
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		for(int i=0;i<products.size();i++) { //30 products
			String productName = products.get(i).getText();
			System.out.println(productName);
			
			//spilt product name (Brocolli - 1 Kg) by - 
			String[] productNameWithSpace = productName.split("-");
			
			//remove space from product name
			String finalProductName = productNameWithSpace[0].trim();
			System.out.println(finalProductName);
			
			// conver arry to arrylist (easy to use contains method,
			// so intially declair array) 
			List<String> addToCartProductList = Arrays.asList(addToCartProduct); 
			if(addToCartProductList.contains(finalProductName)) {
				// click on button
				//this is sysntax shows wrong put bcoz choose text and that is dymanic text.
				//driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
				//parent child xpath. this will not show wrong item in cart
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				
				j++;
				if(j==addToCartProduct.length)
					break;
			}
		}
	}
	
	@Test(priority=2)
	public void checkOut() {
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
	}

//	@AfterTest
//	public void tearDown() {
//		driver.close();
//	}
}
