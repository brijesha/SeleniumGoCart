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

		topDealsPageObjects.topDeal().click();

		// Get the current window handle
		String windowHandle = driver.getWindowHandle();

		// Get the list of window handles
		ArrayList tabs = new ArrayList(driver.getWindowHandles());

		// swith to another tab
		driver.switchTo().window((String) tabs.get(1));

		int numRows = topDealsPageObjects.rows().size();
		assertEquals(numRows, 5, "Rows are missing.");
		assertRows(expectedItemNameArr, numRows);

		assertEquals(topDealsPageObjects.firstBtn().getAttribute("aria-disabled"), "true",
				"First button in pagination is not disable.");
		assertEquals(topDealsPageObjects.previousBtn().getAttribute("aria-disabled"), "true",
				"Previous button in pagination is not disable.");

		for (int i = 0; i < pagination.length; i++) {
			if ((pagination[i].equals("Next")) || (pagination[i]).equals("Last")) {
				assertEquals(topDealsPageObjects.nextLastBtn(pagination[i]).getAttribute("aria-disabled"), "false",
						pagination[i] + " is not enabled.");
			} else {
				assertTrue(topDealsPageObjects.paginationBtn(pagination[i]).isEnabled(),
						pagination[i] + " is not enabled.");
			}
		}

		// Change page size to 10
		WebElement staticDropdwn = driver.findElement(By.id("page-menu"));
		Select dropdown = new Select(staticDropdwn);
		dropdown.selectByValue("10");
		assertEquals(topDealsPageObjects.rows().size(), 10, "Rows are missing.");

		// click number 2
		dropdown.selectByValue("5");
		topDealsPageObjects.paginationBtn("2").click();
		assertEquals(topDealsPageObjects.pineapple().getText(), "Pineapple", "Pinepple not found in the table.");
		assertEquals(topDealsPageObjects.firstBtn().getAttribute("aria-disabled"), "false",
				"First button in pagination is not disable.");
		assertEquals(topDealsPageObjects.previousBtn().getAttribute("aria-disabled"), "false",
				"Previous button in pagination is not disable.");
		assertEquals(topDealsPageObjects.nextLastBtn("Next").getAttribute("aria-disabled"), "false",
				"Next is not enabled.");
		assertEquals(topDealsPageObjects.nextLastBtn("Last").getAttribute("aria-disabled"), "false",
				"Last is not enabled.");

		// search Almond
		topDealsPageObjects.search().click();
		topDealsPageObjects.search().sendKeys("Almond");
		assertEquals(topDealsPageObjects.firstBtn().getAttribute("aria-disabled"), "true",
				"First button in pagination is not disable.");
		assertEquals(topDealsPageObjects.previousBtn().getAttribute("aria-disabled"), "true",
				"Previous button in pagination is not disable.");
		assertEquals(topDealsPageObjects.nextLastBtn("Next").getAttribute("aria-disabled"), "true",
				"Next is not enabled.");
		assertEquals(topDealsPageObjects.nextLastBtn("Last").getAttribute("aria-disabled"), "true",
				"Last is not enabled.");
		assertEquals(topDealsPageObjects.rows().size(), 1, "Number of row is not 1.");

		topDealsPageObjects.search().clear();

		// sort by veg/Fruit name
		topDealsPageObjects.productHeader().click();
		String[] sortItemsArr = { "Almond", "Apple", "Banana", "Beans" };
		assertRows(sortItemsArr, 4);
		// most costly item in table
		topDealsPageObjects.discountPriceHeader().click();
		topDealsPageObjects.discountPriceHeader().click();
		String costlyItem = topDealsPageObjects.rows().get(0).findElement(By.xpath("td[1]")).getText();
		assertEquals(costlyItem, "Cherry", "Most costly item isn't cherry.");
		topDealsPageObjects.productHeader().click();

		// click last button and assert rows
		topDealsPageObjects.nextLastBtn("Last").click();
		String[] lastItemsArr = { "Rice", "Strawberry", "Tomato", "Wheat" };
		assertRows(lastItemsArr, 4);

	}

	public void assertRows(String[] expectedArr, int numRows) {
		List<WebElement> rows = topDealsPageObjects.rows();
		for (int i = 0; i < numRows; i++) {
			WebElement row = rows.get(i);
			WebElement firstColum = row.findElement(By.xpath("td[1]"));
			String itemName = firstColum.getText();
			assertEquals(itemName, expectedArr[i], "Product name mismatch.");
		}
	}

}
