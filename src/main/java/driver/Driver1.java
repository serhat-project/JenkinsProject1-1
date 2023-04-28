package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.DriverFactory1.*;

public class Driver1 {
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> waits = new ThreadLocal<>();
    private static ThreadLocal<Browsers1> browsers = new ThreadLocal<>();

    public static WebDriver getDriver(){
        if (browsers.get()==null)
            browsers.set(Browsers1.chrome);
        return getDriver(browsers.get());
    }
    public static WebDriver getDriver(Browsers1 browser){
        browsers.set(browser);
        if (drivers.get() == null){
            switch (browser){
                case firefox:
                    drivers.set(createFirefox());
                    break;
                case edge:
                    drivers.set(createEdge());
                    break;
                case ie:
                    drivers.set(createIe());
                    break;
                case safari:
                    drivers.set(createSafari());
                    break;
                default:
                    drivers.set(createChrome());
                    break;
            }
        }
        waits.set(new WebDriverWait(drivers.get(), Duration.ofSeconds(10)));
        return drivers.get();
    }

    public static WebDriverWait getWait(){
        return waits.get();
    }

    public static void quitDriver(){
        if (drivers.get() != null) {
            drivers.get().quit();
            drivers.set(null);
            waits.set(null);
        }
    }

}
