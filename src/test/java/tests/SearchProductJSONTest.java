package tests;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchProductJSONTest extends BaseTest {

	@Test(dataProvider = "searchDataJSON")
	public void searchProductTest(String toSearch, String productName, int expectedPrice) {
		homePageObjects.searchProduct(toSearch, productName, expectedPrice);
	}

	@DataProvider(name = "searchDataJSON")
	public Object[][] createData() throws Exception {
		Object[][] objectArr = null;
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("D:\\eclipseworkspace\\cart\\src\\test\\resources\\DataSheet.json");
		JSONArray jsonArr = (JSONArray) jsonParser.parse(reader);
		objectArr = new Object[jsonArr.size()][3];
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject productData = (JSONObject) jsonArr.get(i);
			objectArr[i][0] = productData.get("searchTerm");
			objectArr[i][1] = productData.get("productName");
			objectArr[i][2] = Integer.parseInt(productData.get("price").toString());
		}

		return objectArr;
	}

}