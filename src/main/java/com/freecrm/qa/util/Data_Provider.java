package com.freecrm.qa.util;

import org.testng.annotations.DataProvider;

import com.freecrm.qa.base.TestBase;

public class Data_Provider extends TestBase {

	TestUtil testUtil;
	static String sheetName = "contacts";

	@DataProvider(name="applicationCustomerNames")
	public static Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;

	}

}
