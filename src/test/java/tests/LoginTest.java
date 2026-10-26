package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.microsoft.playwright.*;

import driver.BaseTest;
import pages.LoginPage;

public class LoginTest {
    private Playwright playwright;
    private Browser browser;
   // private Page page;
    //private LoginPage loginPage;
    BaseTest baseTest = new BaseTest();

    @BeforeMethod
    public void childsetUp() {
    	baseTest.setUp(); // Allocates clean instance for current thread
    }
    @AfterMethod
    public void closeAll() {
    	baseTest.tearDown(); // Cleans up resources for current thread

    }

    @Test
    void testSuccessfulLogin() {
    	//baseTest.setUp(); // Initialize Playwright, Browser, Context, and Page for this thread
    	Page page = baseTest.getPage();// Get the thread-specific Page instance
    	LoginPage loginPage = new LoginPage(page);
    	loginPage.navigateTo();
        try {
			loginPage.login("First Name", "Address detaisl from method 1");
		
			//page.waitForTimeout(5000); // Wait for 2 seconds to observe the result
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Test
    void testSuccessfulLogin2() {
    	
    	//baseTest.setUp(); // Initialize Playwright, Browser, Context, and Page for this thread
    	Page page = baseTest.getPage();// Get the thread-specific Page instance
    	LoginPage loginPage = new LoginPage(page);
    	loginPage.navigateTo();
        try {
        	loginPage.login("Second Name", "Address details from method 22");
			page.waitForTimeout(5000); // Wait for 2 seconds to observe the result
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      
    }
    /*
    @AfterClass
    void tearDown() {
        browser.close();
        playwright.close();
    }
    */
}
