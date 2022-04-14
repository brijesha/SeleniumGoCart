package com.goCart.qa.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Utility {
	
	public static void checkStrEqualsInt(String str, int expectedValue, String elemName) {
		int strInt = 0;
		try {
			strInt = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			fail(elemName + " is not an integer");
		}
		assertEquals(strInt, expectedValue, elemName + " not equals to " + expectedValue);
	}
}
