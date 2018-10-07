package com.freecrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.qa.base.TestBase;
import com.freecrm.qa.util.TestUtil;

public class LoginPage extends TestBase {
	
	//page factrory -OR
	
	@FindBy(name="username")
//	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	
	//@FindBy(name="password")
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement lgnBtn;
	
	@FindBy(xpath="//btn[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(class,'img-responsive')]")
	WebElement crmLogo;

	
	//Initialize objects
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public Boolean validateLogo()
	{
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd)
	{
		//username.sendKeys(un);
		TestUtil.sendKeysWithExplicitWait(driver, username, 5, un);
		
		password.sendKeys(pwd);
		lgnBtn.submit();
		
		return new HomePage();
		
	}

}
