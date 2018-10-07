package com.freecrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.pages.ContactsPage;
import com.freecrm.qa.pages.HomePage;
import com.freecrm.qa.pages.LoginPage;
import com.freecrm.qa.util.TestUtil;



public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage  homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName="contacts";
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException 
	{
		initialization();
		testUtil= new TestUtil();
		loginPage= new LoginPage();
		contactsPage = new ContactsPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		homePage.clickOnConatctLink();
	}
	
	@Test(priority=1,enabled=false)
	public void verifyContacyLabelTest()
	{
		Assert.assertTrue(contactsPage.verifyContactLabel(),"Contact label is missing");
	}
	
	
	@Test(priority=2,enabled=false)
	public void selectContactsByName()
	{
		contactsPage.selectContactsByName("abdn abdn");
	}
	
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	
	
	@Test(priority=3, dataProvider="getCRMTestData",enabled=true)
	
	public void validateNewContact(String title,String fName,String lName,String comapnyName)
	{
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title,fName,lName,comapnyName);
	//	System.out.println("First Name is : " +fName);
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateDeleteContact(String title,String fName,String lName,String comapnyName) throws InterruptedException
	{
		
		contactsPage.deleteContact(fName,lName);
		Assert.assertFalse(contactsPage.CheckIfContactDeletedSuccessfully(fName, lName),(fName+lName) +" contact could not be deleted");
	//	Assert.assertTrue(contactsPage.CheckIfContactDeletedSuccessfully(fName, lName),(fName+lName) +"Contact deleted successfully");
	
	//	System.out.println("First Name is : " +fName);
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	


}
