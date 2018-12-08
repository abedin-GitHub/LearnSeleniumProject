package com.freecrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.pages.HomePage;
import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
	}

	// Below approach makes each test case independent.
	// This will open the browser and execute each test case and then close browser
	// and giving us the advantages by minimizing issues arising due to cookies and
	// cache.

	@Test(priority = 1, enabled = false)

	public void loginPageTitleTest() {
		log.info("************* Test case starts************************");
		log.info("************* Verify Title of Login Page *************");
		String title = loginPage.validateLoginPageTitle();
		// log.info("Comparing application Title");
		// log.warn("Comparing application Title1");
		// log.debug("Comparing application Title2");
		// log.fatal("Comparing application Title3");

		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");

		log.info("************* Test case ends **************************");
		log.info("************** Login Page title is verified ***********");

	}

	@Test(priority = 2, enabled = false)
	public void crmLogoImageTest() {
		log.info("************* Test case starts************************");
		log.info("************* Verify Logo of Login Page *************");
		Boolean flag = loginPage.validateLogo();
		Assert.assertTrue(flag);
		log.info("************* Test case ends **************************");
		log.info("************** Login Page logo is verified ***********");

	}

	@Test(priority = 3, enabled = true)
	public void loginTest() {
		log.info("************* Test case starts************************");
		log.info("************* Verify Login user *************");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
		log.info("************* Test case ends **************************");
		log.info("************** Login user is verified ***********");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("********* Browser is closed **************************");
	}

}
