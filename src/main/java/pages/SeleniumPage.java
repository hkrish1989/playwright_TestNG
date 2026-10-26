package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverManager;

public class SeleniumPage {

	WebDriver driver = DriverManager.getDriver();
	
	public SeleniumPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	public void openPage(String url) {
		driver.get(url);
	}
	
	@FindBy(xpath="//div/h5[contains(text(),'Elements')]")
	public WebElement element1;
	public void click() {
		element1.sendKeys("test");
		
	}
	
}
