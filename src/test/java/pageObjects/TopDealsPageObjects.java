package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopDealsPageObjects {

	WebDriver driver;

	By topDeal = By.xpath("//a[@href='#/offers']");
	By rows = By.cssSelector("tbody tr");
	//By firstBtn = By.xpath("//a[@aria-label='First']");
	//By previousBtn = By.xpath("//a[@aria-label='Previous']");
	By pineapple = By.xpath("//td[contains(text(),'Pineapple')]");
	By search = By.id("search-field");
	By productHeader = By.xpath("//thead/tr[1]/th[1]");
	By discountPriceHeader = By.xpath("//thead/tr[1]/th[3]");
	By pageSize = By.id("page-menu");
	By firstColumn = By.xpath("td[1]");
	
	public TopDealsPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public WebElement topDeal() {
		return driver.findElement(topDeal);
	}
	
	public List<WebElement> rows() {
		return driver.findElements(rows);
	}
	
	/*public WebElement firstBtn() {
		return driver.findElement(firstBtn);
	}
	
	public WebElement previousBtn() {
		return driver.findElement(previousBtn);
	}*/
	
	public WebElement firstPreviousBtn(String val) {
		return driver.findElement(By.xpath("//a[@aria-label='"+val+"']"));
	}
	
	public WebElement paginationBtn(String val) {
		return driver.findElement(By.xpath("//span[contains(text(),'"+val+"')]"));
	}
	
	public WebElement nextLastBtn(String val) {
		return driver.findElement(By.xpath("//a[@aria-label='" + val + "']"));
	}
	
	public String pineapple() {
		return driver.findElement(pineapple).getText();
	}
	
	public WebElement search() {
		return driver.findElement(search);
	}

	public WebElement productHeader() {
		return driver.findElement(productHeader);
	}
	
	public WebElement discountPriceHeader() {
		return driver.findElement(discountPriceHeader);
	}
	
	public WebElement pageSizeSelect() {
		return driver.findElement(pageSize);
	}
	
	public String firstRowColumnValue() {
		return rows().get(0).findElement(firstColumn).getText();
	}
}
