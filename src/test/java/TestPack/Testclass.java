package TestPack;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;

@Test(singleThreaded = true)
public class Testclass {

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
	
	@Test(priority = 3)
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
	
}
