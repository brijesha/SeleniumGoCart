package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopDealsPageObjects {

	WebDriver driver;

	By topDeal = By.xpath("//a[@href='#/offers']");
	By rows = By.cssSelector("tbody tr");
	By firstBtn = By.xpath("//a[@aria-label='First']");
	By previousBtn = By.xpath("//a[@aria-label='Previous']");
	/*By oneBtn = By.xpath("//span[contains(text(),'1')]");
	By secondBtn = By.xpath("//span[contains(text(),'2')]");
	By thirdBtn = By.xpath("//span[contains(text(),'3')]");
	By forthBtn = By.xpath("//span[contains(text(),'4')]");*/
	/*By nextBtn = By.xpath("//a[@aria-label='Next']");
	By lastBtn = By.xpath("//a[@aria-label='Last']");*/

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
	
	public WebElement firstBtn() {
		return driver.findElement(firstBtn);
	}
	
	public WebElement previousBtn() {
		return driver.findElement(previousBtn);
	}
	
	public WebElement paginationBtn(String val) {
		return driver.findElement(By.xpath("//span[contains(text(),val)]"));
	}
	
	public WebElement nextLastBtn(String val) {
		return driver.findElement(By.xpath("//a[@aria-label=val]"));
	}
	/*public WebElement secondBtn() {
		return driver.findElement(secondBtn);
	}
	public WebElement thirdBtn() {
		return driver.findElement(thirdBtn);
	}
	public WebElement forthBtn() {
		return driver.findElement(forthBtn);
	}*/
	/*public WebElement nextBtn() {
		return driver.findElement(nextBtn);
	}
	public WebElement lastBtn() {
		return driver.findElement(lastBtn);*/
/*	}*/
}
