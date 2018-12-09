package com.freecrm.qa.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	@FindBy(id="email")
	WebElement clientEMail;
	
	
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
	
	public void createNewContact(String title,String ftName,String ltName,String comp,String eMail)
	{
	//	Select select= new Select(ContactTitle);
		Select select= new Select(driver.findElement(By.name("title")));
	
		select.selectByVisibleText(title);
		
		firstName.sendKeys(ftName);
		System.out.println(ftName);
		lastName.sendKeys(ltName);
		companyName.sendKeys(comp);
		clientEMail.sendKeys(eMail);
		
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
		TestUtil.checkElemenetPresent(driver, element, 10);
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

	
	
	
	public boolean CheckIfUserDataIsCorrectInContactPage() {
		List<HashMap<String, String>> wholeTableActual = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> wholeTableExpected = TestUtil.readExcelNameEmail();

		List<WebElement> allRows = driver.findElements(By.xpath("//form[@id='vContactsForm']/table/tbody/tr"));
		int noOfRows = allRows.size();

		for (int rnum = 4; rnum < noOfRows; rnum++) {

			HashMap<String, String> eachRowDetail = new HashMap<String, String>();
			WebElement row = allRows.get(rnum);

			String name = row.findElement(By.xpath("//form[@id='vContactsForm']/table/tbody/tr[" + rnum + "]/td[2]"))
					.getText();
			String email = row.findElement(By.xpath("//form[@id='vContactsForm']/table/tbody/tr[" + rnum + "]/td[7]"))
					.getText();

			eachRowDetail.put("name", name);
			eachRowDetail.put("email", email);
			wholeTableActual.add(eachRowDetail);
		}

		if ((wholeTableActual.size()) != wholeTableExpected.size()) {
			System.out.println("expected size list is not match with aactual size list\nactual list:-"
					+ wholeTableActual.size() + "\n expected list:-" + wholeTableExpected.size());
		} else {

			for (int i = 0; i < wholeTableActual.size(); i++) {
				boolean ismached = false;
				HashMap<String, String> comapreactualMap = wholeTableActual.get(i);
				for (int j = 0; j < wholeTableExpected.size(); j++) {
					HashMap<String, String> expectedctualMap = wholeTableExpected.get(j);

					if (TestUtil.comparemaps(comapreactualMap, expectedctualMap)) {
						ismached = true;
						break;
					}

				}
				if (!ismached) {
					// return false
					System.out.println("name:-" + comapreactualMap.get("name"));
					System.out.println(ismached);
					return false;

				}

			}

		}
		return true;

	}

}
	
	
	
	
	
	

