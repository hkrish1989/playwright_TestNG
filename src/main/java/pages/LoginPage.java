package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import driver.BaseTest;

public class LoginPage {
    // 1. Declare the Playwright Page and Locators
	BaseTest baseTest = new BaseTest();
	//Page page = baseTest.getPage();
	private Page page = baseTest.getPage();
    public LoginPage(Page page) {
    	this.page=page;
    }
    Locator element1 = page.locator("//div/h5[contains(text(),'Elements')]");
    Locator textboxbtn = page.locator("//span[contains(text(),'Text Box')]");
    Locator usernameInput = page.locator("//input[@id = 'userName']");
    Locator emailtxt = page.locator("//input[@id = 'userEmail']");
    Locator currentAddress = page.locator("//textarea[@id = 'currentAddress']");
  
   /*
    public LoginPage() {
        //this.page = page;
        element1 = page.locator("//div/h5[contains(text(),'Elements')]");
        textboxbtn = page.locator("//span[contains(text(),'Text Box')]");
        usernameInput = page.locator("//input[@id = 'userName']");
        emailtxt = page.locator("//input[@id = 'userEmail']");
        currentAddress = page.locator("//textarea[@id = 'currentAddress']");
        //this.passwordInput = page.locator("#password");
        //this.loginButton = page.locator("button#login-submit");
    }
*/
   
    public void navigateTo() {
        page.navigate("https://demoqa.com/");
    }

    public void waitForElement(Locator element) {
		element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}
    public void login(String username, String address) throws InterruptedException {
    	
    	waitForElement(element1);
		element1.click();
		waitForElement(textboxbtn);
		textboxbtn.click();
		waitForElement(usernameInput);
		usernameInput.fill(username);
		emailtxt.fill("test@gmail.com");
		currentAddress.fill(address);
		
    }
    
}
