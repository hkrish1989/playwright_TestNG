package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import driver.DriverManager;
import java.io.File;
import java.io.IOException;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // 1. Retrieve the thread-isolated driver instance safely
        WebDriver driver = DriverManager.getDriver();
        
        if (driver != null) {
            // 2. Capture the screenshot file
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotName = result.getName() + "_" + System.currentTimeMillis() + ".png";
            File targetFile = new File(System.getProperty("user.dir") + "/target/screenshots/" + screenshotName);
            
            try {
                FileUtils.copyFile(srcFile, targetFile);
                System.out.println("Screenshot captured for failed test: " + result.getName());
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}
