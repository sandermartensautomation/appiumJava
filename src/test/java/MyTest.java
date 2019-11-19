import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTest {

    private AppiumDriver driver;
    private AppiumDriverLocalService service;

    @Before
    public void startUp(){
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @Test
    public void app() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "\\apidemo.apk");

        driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='Accessibility']"))).click();
    }

    @Test
    public void web() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir") + "\\chromedriver.exe");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");

        driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.get("http://www.google.com");
    }

    @After
    public void teardown(){
        driver.quit();
        service.stop();
    }
}
