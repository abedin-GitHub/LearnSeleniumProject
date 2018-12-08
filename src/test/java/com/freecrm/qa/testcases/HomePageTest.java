package com.freecrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.pages.ContactsPage;
import com.freecrm.qa.pages.HomePage;
import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	// Cntrl+shit+O : Imports all required packages

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1, enabled = true)
	public void homePageVerifyCorrectUserName() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = { "homePageVerifyCorrectUserName" })
	public void homePageVerifyTitleTest() {
		String homePageTile = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTile, "CRMPRO", "Home page title does not match.");

	}

	@Test(priority = 3, enabled = true, dependsOnMethods = { "homePageVerifyTitleTest" })
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnConatctLink();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
