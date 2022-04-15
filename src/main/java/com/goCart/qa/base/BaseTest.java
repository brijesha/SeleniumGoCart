package com.goCart.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.goCart.qa.pages.CheckoutPageObjects;
import com.goCart.qa.pages.HomePageObjects;
import com.goCart.qa.pages.TCPageObjects;
import com.goCart.qa.pages.TopDealsPageObjects;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	
	public static HomePageObjects homePageObjects;
	public static CheckoutPageObjects checkoutPageObjects;
	public static TCPageObjects tcPageObjects;
	public static TopDealsPageObjects topDealsPageObjects;

	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"/Users/brijesha/eclipse-workspace/SeleniumGoCart/src/main/java/com/goCart/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initialization(String browserName) {
		//String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/brijesha/Selenium Drivers/chromedriver");
			driver = new ChromeDriver();
			System.out.println("Base class----");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/brijesha/Selenium Drivers/geckodriver");
			driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "/Users/brijesha/Selenium Drivers/msedgedriver");
			driver = new EdgeDriver();
		}
		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("page_load_timeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicit_wait")),
				TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}
}

/*
 * @BeforeTest public void createDriver() { // System.out.println("Initialize");
 * System.setProperty("webdriver.chrome.driver",
 * "/Users/brijesha/Selenium Drivers/chromedriver"); driver = new
 * ChromeDriver();
 * driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
 * driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
 * 
 * // Provide value to object homePageObjects = new HomePageObjects(driver);
 * checkoutPageObjects = new CheckoutPageObjects(driver); tcPageObjects = new
 * TCPageObjects(driver); topDealsPageObjects = new TopDealsPageObjects(driver);
 * app}
 * 
 * 
 */
