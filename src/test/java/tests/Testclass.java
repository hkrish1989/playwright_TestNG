package tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;

import driver.BaseTest;
import pages.LoginPage;

@Test(singleThreaded = true)
public class Testclass {
	  BaseTest baseTest = new BaseTest();

	@Test(priority = 1)
	public void testMethod() {
		System.out.println("This is a test method.");
		System.out.println("This is a test method 1 second line");
	}
	
	@Test
	public void testMethod2() {
		System.out.println("This is a test method 2.");
		System.out.println("This is a test method 2.1");
		System.out.println("This is a test method 2.2");
	}
	
	@Test
	public void testMethod3() {
		System.out.println("This is a test method 3.");
	}
	@Test(priority = 2)
	public void testMethod4() {
		System.out.println("This is a test method 4.");
	}
	 @BeforeMethod
	 public void childsetUp() {
	   	baseTest.setUp(); // Allocates clean instance for current thread
	 }
	 @AfterMethod
	 public void closeAll() {
	   	baseTest.tearDown(); // Cleans up resources for current thread

	 }
	
	@Test(priority = 1)
	void testSuccessfulLogin3() {
		
    	Page page = baseTest.getPage();// Get the thread-specific Page instance
    	LoginPage loginPage = new LoginPage(page);
    	loginPage.navigateTo();
        try {
			loginPage.login("Different Name", "Another class address");
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/*
	public void openBrowser() {
		System.out.println("This is a test method 5.");
		
		try (Playwright playwright = Playwright.create()) {
            // Launch Google Chrome using LaunchOptions
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)
            );
            Page page = browser.newPage();
            
            page.navigate("https://demoqa.com/");
            try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Wait for 5 seconds to see the page
            //WebElement ele = browser.findElement("//div/h5[contains(text(),\"Elements\")]");
            
            browser.close();
		}
		
	}
	*/
	
}
