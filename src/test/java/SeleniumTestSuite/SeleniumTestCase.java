package SeleniumTestSuite;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.qa.Main.Constant;
import com.qa.Main.Landing;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


import static org.junit.Assert.*;


public class SeleniumTestCase {
	
	ChromeDriver driver;
	ExtentReports report;
	ExtentTest test;
	Actions action;
	
	@Before
	public void setup() {
		
		
		System.setProperty("webdriver.chrome.driver", Constant.filepath+Constant.chromedriver);
		driver = new ChromeDriver();
		action = new Actions(driver);
		
		driver.manage().window().maximize();

		
		report = new ExtentReports(Constant.filepath + Constant.seleniumReport,true);
		test = report.startTest("Setup");
		
		driver.get(Constant.url);
	}
	
	@After
	public void teardown() throws InterruptedException {
	
		Thread.sleep(1000);
		driver.quit();
		report.endTest(test);
		report.flush();
	}
	
	@Test
	public void addOwner() {
		
		Landing main = PageFactory.initElements(driver, Landing.class);
		
		assertEquals("200", main.addOwner(driver, action));
	}
	
	@Test
	@Ignore
	public void updateOwner() {
		
	}

}
