package com.goCart.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.goCart.qa.base.BaseTest;
import com.goCart.qa.pages.HomePageObjects;
import com.goCart.qa.pages.TopDealsPageObjects;

public class TopDealTest extends BaseTest {
	public TopDealTest() {
		super();
	}
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {
		initialization(browser);
		topDealsPageObjects = new TopDealsPageObjects(driver);
	}

	@Test
	public void topDealTest() throws InterruptedException {

		// open topdeal page in a new tab
		topDealsPageObjects.topDeal().click();

		// Get the list of window handles (tabs)
		ArrayList tabs = new ArrayList(driver.getWindowHandles());

		// switch control to topdeal page tab
		String topDealTab = (String) tabs.get(1);
		driver.switchTo().window(topDealTab);

		// verify number of rows
		int numRows = topDealsPageObjects.rows().size();
		assertEquals(numRows, 5, "Rows mismatch.");

		// verify items in table
		String[] expectedItemNameArr = { "Wheat", "Tomato", "Strawberry", "Rice", "Potato" };
		assertRows(expectedItemNameArr);

		// verify disable buttons
		checkEnableDisableButtons("First", "true");
		checkEnableDisableButtons("Previous", "true");

		// verify enable buttons
		checkEnableDisableButtons("Next", "false");
		checkEnableDisableButtons("Last", "false");
		String[] pagination = { "1", "2", "3", "4" };
		for (int i = 0; i < pagination.length; i++) {
			assertTrue(topDealsPageObjects.paginationBtn(pagination[i]).isEnabled(),
					pagination[i] + " is not enabled.");
		}

		// change page size
		WebElement pageSizeSelectElem = topDealsPageObjects.pageSizeSelect();
		Select pageSizeSelect = new Select(pageSizeSelectElem);
		pageSizeSelect.selectByValue("10");
		assertEquals(topDealsPageObjects.rows().size(), 10, "Rows mismatch.");

		// revert back page size to default
		pageSizeSelect.selectByValue("5");

		// navigate to 2nd page
		topDealsPageObjects.paginationBtn("2").click();

		// verify item name
		assertEquals(topDealsPageObjects.pineapple(), "Pineapple", "Pinepple not found in the table.");

		// verify enable buttons
		checkEnableDisableButtons("First", "false");
		checkEnableDisableButtons("Prevoius", "false");
		checkEnableDisableButtons("Next", "false");
		checkEnableDisableButtons("Last", "false");

		// search Almond
		topDealsPageObjects.search().click();
		topDealsPageObjects.search().sendKeys("Almond");
		checkEnableDisableButtons("First", "true");
		checkEnableDisableButtons("Previous", "true");
		checkEnableDisableButtons("Next", "true");
		checkEnableDisableButtons("Last", "true");

		// check number of rows
		assertEquals(topDealsPageObjects.rows().size(), 1, "Rows mismatch.");

		// delete search item
		topDealsPageObjects.search().sendKeys(Keys.chord(Keys.COMMAND, "a"));
		topDealsPageObjects.search().sendKeys(Keys.DELETE);

		// sort by veg/Fruit name
		topDealsPageObjects.productHeader().click();
		String[] sortItemsArr = { "Almond", "Apple", "Banana", "Beans" };
		assertRows(sortItemsArr);

		// verify most costly item in table
		topDealsPageObjects.discountPriceHeader().click();
		topDealsPageObjects.discountPriceHeader().click();
		String mostCostlyItem = topDealsPageObjects.rowColumnValue(0);
		assertEquals(mostCostlyItem, "Cherry", "Most costly item not found.");
		topDealsPageObjects.productHeader().click();

		// click last button and assert rows
		topDealsPageObjects.nextLastBtn("Last").click();
		String[] lastItemsArr = { "Rice", "Strawberry", "Tomato", "Wheat" };
		assertRows(lastItemsArr);
	}
	
	@AfterClass
	public void tearDown() {
		try {
			driver.close();
		}catch(Exception e1) {}
		try {
			driver.quit();
		}catch(Exception e1) {}	
	}

	// verify enable disable buttons
	public void checkEnableDisableButtons(String btnName, String isDisabled) {
		if (btnName.equals("First") || btnName.equals("Previous")) {
			String lastMessage = "true".equals(isDisabled) ? "disabled" : "enabled";
			assertEquals(topDealsPageObjects.firstPreviousBtn(btnName).getAttribute("aria-disabled"), isDisabled,
					btnName + " isn't " + lastMessage);
		}
		if (btnName.equals("Next") || btnName.equals("Last")) {
			if ("true".equals(isDisabled)) {
				assertEquals(topDealsPageObjects.nextLastBtn(btnName).getAttribute("aria-disabled"), isDisabled,
						btnName + " isn't disable.");
			} else {
				assertEquals(topDealsPageObjects.nextLastBtn(btnName).getAttribute("aria-disabled"), isDisabled,
						btnName + " isn't enable.");
			}
		}
	}

	// verify item names in table
	public void assertRows(String[] expectedItemNameArr) {
		List<WebElement> rows = topDealsPageObjects.rows();
		for (int i = 0; i < expectedItemNameArr.length; i++) {
			WebElement row = rows.get(i);
			// first column in table
			String itemName = topDealsPageObjects.rowColumnValue(i);
			assertEquals(itemName, expectedItemNameArr[i], "Product name mismatch.");
		}
	}

}
