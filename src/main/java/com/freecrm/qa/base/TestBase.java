package com.freecrm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.freecrm.qa.util.TestUtil;
import com.freecrm.qa.util.WebEventListener;
import com.freecrm.qa.util.projectConstants;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	// EventFiringWebDriver generates selenium Action Logs

	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public static Logger log;

	// Within constructor,read the properties file
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/freecrm/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() throws InterruptedException {
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("mozilla")) {
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver",
			// "C:/Users/mozahidur.abedi/eclipse-workspace/AbedinFreeCRMTest/ChromeDriver/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", TestUtil.CHROME_DRIVER_EXE);
			driver = new ChromeDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		// Thread.sleep(1000);
		log = Logger.getLogger(TestBase.class);

	}

}
