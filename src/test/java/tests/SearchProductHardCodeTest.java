package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProductHardCodeTest extends HomePageBaseTest {

	@Test(dataProvider = "searchDataHardCode")
	public void searchProductTest(String toSearch, String productName, int expectedPrice) {
		searchProduct(toSearch, productName, expectedPrice);
	}

	@DataProvider(name = "searchDataHardCode")
	public Object[][] createData() {
		return new Object[][] { { "or", "Corn", 75 }, { "or", "Orange", 75 }, { "ca", "Cauliflower", 60 },
				{ "ca", "Carrot", 56 }, { "ca", "Capsicum", 60 }, { "ca", "Cashew", 650 } };
	}
	
	

}