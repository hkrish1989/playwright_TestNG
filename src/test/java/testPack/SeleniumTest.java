package testPack;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import driver.DriverManager;
import pages.SeleniumPage;

public class SeleniumTest {
	
	
	
	

	@Test(groups = {"SeleniumTests"})
	public void newTest1() {
		SeleniumPage seleniumPage = new SeleniumPage(DriverManager.getDriver());
		seleniumPage.openPage("https://demoqa.com/");	
	}

	@Test(groups = {"SeleniumTests"})
	public void newTest2() {
		SeleniumPage seleniumPage = new SeleniumPage(DriverManager.getDriver());
		seleniumPage.openPage("https://demoqa.com/");
		seleniumPage.click();
	}

	@Test(groups = {"SeleniumTests"})
	public void newTest3() {
		SeleniumPage seleniumPage = new SeleniumPage(DriverManager.getDriver());
		seleniumPage.openPage("https://demoqa.com/");	
	}

	@Test(groups = {"SeleniumTests"})
	public void newTest4() {
		SeleniumPage seleniumPage = new SeleniumPage(DriverManager.getDriver());
		seleniumPage.openPage("https://demoqa.com/");	
	}

	@Test(groups = {"SeleniumTests"})
	public void newTest5() {
		SeleniumPage seleniumPage = new SeleniumPage(DriverManager.getDriver());
		seleniumPage.openPage("https://demoqa.com/");	
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
