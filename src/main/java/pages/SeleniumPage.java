package pages;

import org.openqa.selenium.WebDriver;

import driver.DriverManager;

public class SeleniumPage {

	WebDriver driver = DriverManager.getDriver();
	
	public SeleniumPage(WebDriver driver) {
		this.driver = driver;
	}
	public void openPage(String url) {
		driver.get(url);
	}
}
