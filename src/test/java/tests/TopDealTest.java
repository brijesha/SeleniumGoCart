package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.Utility;

public class TopDealTest extends HomePageBaseTest {

	@Test
	public void topDeal() throws InterruptedException {
		String[] expectedItemName = { "Wheat", "Tomato", "Strawberry", "Rice", "Potato" };
		String[] pagination = { "1", "2", "3", "4", "Next", "Last" };

		topDealPageObject.topDeal().click();

		// Get the current window handle
		String windowHandle = driver.getWindowHandle();

		// Get the list of window handles
		ArrayList tabs = new ArrayList(driver.getWindowHandles());

		// swith to another tab
		driver.switchTo().window((String) tabs.get(1));

		List<WebElement> rows = topDealPageObject.rows();
		int numRows = topDealPageObject.rows().size();
		assertEquals(numRows, 5, "Rows are missing.");

		for (int i = 0; i < numRows; i++) {
			WebElement row = rows.get(i);
			WebElement firstColum = row.findElement(By.xpath("td[1]"));
			String itemName = firstColum.getText();
			assertEquals(itemName, expectedItemName[i], "Product name mismatch.");
		}
		assertEquals(topDealPageObject.firstBtn().getAttribute("aria-disabled"),"true", "First button in pagination is not disable.");
		assertEquals(topDealPageObject.previousBtn().getAttribute("aria-disabled"),"true", "Previous button in pagination is not disable.");

		for (int i = 0; i < pagination.length; i++) {
			assertTrue(topDealPageObject.paginationBtn(pagination[i]).isEnabled(), pagination[i] + " is not enabled.");
			if ((pagination[i].equals("Next")) || (pagination[i]).equals("Last")) {
				assertTrue(topDealPageObject.nextLastBtn(pagination[i]).isEnabled(),
						pagination[i] + " is not enabled.");
			}
		}

	}

}
