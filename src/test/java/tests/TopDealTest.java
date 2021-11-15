package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TopDealTest extends BaseTest {

	@Test
	public void topDeal() throws InterruptedException {
		String[] expectedItemNameArr = { "Wheat", "Tomato", "Strawberry", "Rice", "Potato" };
		String[] pagination = { "1", "2", "3", "4", "Next", "Last" };

		topDealPageObject.topDeal().click();

		// Get the current window handle
		String windowHandle = driver.getWindowHandle();

		// Get the list of window handles
		ArrayList tabs = new ArrayList(driver.getWindowHandles());

		// swith to another tab
		driver.switchTo().window((String) tabs.get(1));

		int numRows = topDealPageObject.rows().size();
		assertEquals(numRows, 5, "Rows are missing.");
		assertRows(expectedItemNameArr, numRows);

		assertEquals(topDealPageObject.firstBtn().getAttribute("aria-disabled"), "true",
				"First button in pagination is not disable.");
		assertEquals(topDealPageObject.previousBtn().getAttribute("aria-disabled"), "true",
				"Previous button in pagination is not disable.");

		for (int i = 0; i < pagination.length; i++) {
			if ((pagination[i].equals("Next")) || (pagination[i]).equals("Last")) {
				assertEquals(topDealPageObject.nextLastBtn(pagination[i]).getAttribute("aria-disabled"), "false",
						pagination[i] + " is not enabled.");
			} else {
				assertTrue(topDealPageObject.paginationBtn(pagination[i]).isEnabled(),
						pagination[i] + " is not enabled.");
			}
		}

		// Change page size to 10
		WebElement staticDropdwn = driver.findElement(By.id("page-menu"));
		Select dropdown = new Select(staticDropdwn);
		dropdown.selectByValue("10");
		assertEquals(topDealPageObject.rows().size(), 10, "Rows are missing.");

		// click number 2
		dropdown.selectByValue("5");
		topDealPageObject.paginationBtn("2").click();
		assertEquals(topDealPageObject.pineapple().getText(), "Pineapple", "Pinepple not found in the table.");
		assertEquals(topDealPageObject.firstBtn().getAttribute("aria-disabled"), "false",
				"First button in pagination is not disable.");
		assertEquals(topDealPageObject.previousBtn().getAttribute("aria-disabled"), "false",
				"Previous button in pagination is not disable.");
		assertEquals(topDealPageObject.nextLastBtn("Next").getAttribute("aria-disabled"), "false",
				"Next is not enabled.");
		assertEquals(topDealPageObject.nextLastBtn("Last").getAttribute("aria-disabled"), "false",
				"Last is not enabled.");

		// search Almond
		topDealPageObject.search().click();
		topDealPageObject.search().sendKeys("Almond");
		assertEquals(topDealPageObject.firstBtn().getAttribute("aria-disabled"), "true",
				"First button in pagination is not disable.");
		assertEquals(topDealPageObject.previousBtn().getAttribute("aria-disabled"), "true",
				"Previous button in pagination is not disable.");
		assertEquals(topDealPageObject.nextLastBtn("Next").getAttribute("aria-disabled"), "true",
				"Next is not enabled.");
		assertEquals(topDealPageObject.nextLastBtn("Last").getAttribute("aria-disabled"), "true",
				"Last is not enabled.");
		assertEquals(topDealPageObject.rows().size(), 1, "Number of row is not 1.");

		topDealPageObject.search().clear();

		// sort by veg/Fruit name
		topDealPageObject.productHeader().click();
		String[] sortItemsArr = { "Almond", "Apple", "Banana", "Beans" };
		assertRows(sortItemsArr, 4);
		// most costly item in table
		topDealPageObject.discountPriceHeader().click();
		topDealPageObject.discountPriceHeader().click();
		String costlyItem = topDealPageObject.rows().get(0).findElement(By.xpath("td[1]")).getText();
		assertEquals(costlyItem, "Cherry", "Most costly item isn't cherry.");
		topDealPageObject.productHeader().click();

		// click last button and assert rows
		topDealPageObject.nextLastBtn("Last").click();
		String[] lastItemsArr = { "Rice", "Strawberry", "Tomato", "Wheat" };
		assertRows(lastItemsArr, 4);

	}

	public void assertRows(String[] expectedArr, int numRows) {
		List<WebElement> rows = topDealPageObject.rows();
		for (int i = 0; i < numRows; i++) {
			WebElement row = rows.get(i);
			WebElement firstColum = row.findElement(By.xpath("td[1]"));
			String itemName = firstColum.getText();
			assertEquals(itemName, expectedArr[i], "Product name mismatch.");
		}
	}

}
