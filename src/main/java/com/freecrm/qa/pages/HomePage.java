package com.freecrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.qa.base.TestBase;

public class HomePage extends TestBase  {
	
	@FindBy(xpath="//td[contains(text(),'User: abdn abdn')]")

	WebElement userNameLabel;
	
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}

	
	public String verifyHomePageTitle()
		{
			return driver.getTitle();
		}
	
	public Boolean verifyCorrectUserName()
	{
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnConatctLink()
	{
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink()
	{
		contactsLink.click();
		return new DealsPage();
	}
	
	
	public TaskPage clickOnTasksLink()
	{
		tasksLink.click();
		return new TaskPage();
	}
	
//	public void clickOnNewContactLInk()
//	{
//		Actions action = new Actions(driver);
//		action.moveToElement(newContactsLink).build().perform();
//		newContactsLink.click();
//		
//	}
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
	//	action.moveToElement(contactsLink);
	//	action.click();
	//	action.perform();
		newContactLink.click();
		
	}
	
	
	
}
