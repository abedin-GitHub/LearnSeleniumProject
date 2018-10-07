package com.freecrm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.util.TestUtil;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement ContactLabel;
	
	@FindBy(name="title")
	WebElement ContactTitle;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement companyName;
	
	
	@FindBy(xpath="//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//a[text()='\"+ftname+\" \"+ltname+\"']//parent::td//following-sibling::td//following-sibling::td\"\r\n" + 
			"				+ \"//following-sibling::td//following-sibling::td//following-sibling::td//following-sibling::td//a\"\r\n" + 
			"				+ \"//following-sibling::a//following-sibling::a//following-sibling::a//i[@title='Delete']")
	WebElement deleteBtn;
	
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyContactLabel()
	{
		return ContactLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name)
	{
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']"
				+ "//input[@name='contact_id']")).click();			
	}
	
	public void createNewContact(String title,String ftName,String ltName,String comp)
	{
	//	Select select= new Select(ContactTitle);
		Select select= new Select(driver.findElement(By.name("title")));
	
		select.selectByVisibleText(title);
		
		firstName.sendKeys(ftName);
		System.out.println(ftName);
		lastName.sendKeys(ltName);
		companyName.sendKeys(comp);
		saveBtn.click();
		String completeName=ftName+ltName;
		System.out.println(completeName);
	}
	
	public void deleteContact(String ftname,String ltname) throws InterruptedException
	{
		
	//	deleteBtn.click();
		WebElement element=driver.findElement(By.xpath("//a[text()='"+ftname+" "+ltname+"']//parent::td//following-sibling::td//following-sibling::td"
				+ "//following-sibling::td//following-sibling::td//following-sibling::td//following-sibling::td//a"
				+ "//following-sibling::a//following-sibling::a//following-sibling::a//i[@title='Delete']"));
//		driver.findElement(By.xpath("//a[text()='"+ftname+" "+ltname+"']//parent::td//following-sibling::td//following-sibling::td"
//				+ "//following-sibling::td//following-sibling::td//following-sibling::td//following-sibling::td//a"
//				+ "//following-sibling::a//following-sibling::a//following-sibling::a//i[@title='Delete']")).click();
		element.click();
		driver.switchTo().alert().accept();
		TestUtil.checkElemenetPresent(driver, element, 30);
	//	Thread.sleep(1000);
		
	}
	
	public boolean CheckIfContactDeletedSuccessfully(String ftname,String ltname)
		{
			try {
					//WebElement element=
				driver.findElement(By.xpath("//a[text()='"+ftname+" "+ltname+"']//parent::td//following-sibling::td//following-sibling::td"
				+ "//following-sibling::td//following-sibling::td//following-sibling::td//following-sibling::td//a"
				+ "//following-sibling::a//following-sibling::a//following-sibling::a//i[@title='Delete']"));
				
			      return true;
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
		        return false;
		    }
		}
	

}
