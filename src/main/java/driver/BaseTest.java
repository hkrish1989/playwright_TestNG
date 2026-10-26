package driver;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    // ThreadLocal containers to isolate objects per thread
    private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();

    	
    public void setUp() {
        // Initialize a completely new Playwright and Browser instance for each thread
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        playwrightThread.set(playwright);
        browserThread.set(browser);
        contextThread.set(context);
        pageThread.set(page);
    }

    // Helper method to retrieve the thread-specific Page instance
    public Page getPage() {
        return pageThread.get();
    }

    
    public void tearDown() {
        if (pageThread.get() != null) pageThread.get().close();
        if (contextThread.get() != null) contextThread.get().close();
        if (browserThread.get() != null) browserThread.get().close();
        if (playwrightThread.get() != null) playwrightThread.get().close();

        // Clean up ThreadLocal references
        pageThread.remove();
        contextThread.remove();
        browserThread.remove();
        playwrightThread.remove();
    }
}
