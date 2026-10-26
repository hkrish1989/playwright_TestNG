package testPack;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import driver.DriverManager;
import pages.SeleniumPage;

public class SeleniumTest {
	
	
	SeleniumPage seleniumPage = new SeleniumPage(DriverManager.getDriver());
	

	@Test(groups = {"SeleniumTest"})
	public void newTest1() {
		seleniumPage.openPage("https://demoqa.com/");
		//DriverManager.quitDriver();
		
	}
	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
