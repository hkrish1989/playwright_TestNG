package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	private DriverManager() {
	}
	
	private static void initialiseDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driverThreadLocal.set(driver);
	}
	public static WebDriver getDriver() {
		if (driverThreadLocal.get() == null) {
			initialiseDriver();
		}
		return driverThreadLocal.get();
	}
	public static void quitDriver() {
		if (driverThreadLocal.get() != null) {
			driverThreadLocal.get().quit();
			driverThreadLocal.remove();
		}
	}
}
