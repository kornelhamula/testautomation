package drivers;

import com.google.common.base.Function;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DriverProvider {
    private static DriverProvider driverProvider = null;
    private WebDriver webDriver = null;

    private DriverProvider(){}
    private DriverProvider(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    public static DriverProvider getInstance() {
        if (driverProvider==null) {
            if (Config.getInstance().getBrowserType().equals("firefox")) {
                driverProvider = new DriverProvider(new FirefoxDriver());
            }
            if (Config.getInstance().getBrowserType().equals("chrome")) {
                driverProvider = new DriverProvider(new ChromeDriver());
            }
        }
        return driverProvider;
    }

    public WebElement fluentWait(final By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Config.getInstance().getMaximumWaitInSeconds(), TimeUnit.SECONDS)
                .pollingEvery(Config.getInstance().getPollingInMilliSeconds(), TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    public boolean waitForElementToDisappear(By element) throws InterruptedException {
        for (int i = 0; i <= Config.getInstance().getMaximumWaitInSeconds(); i++) {
            if (webDriver.findElements(element).isEmpty()) {
                return true;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {

                }
            }
            if (i == Config.getInstance().getMaximumWaitInSeconds())
                System.out.println("Element didn't disappear");
        }
        return false;
    }



    public void waitImplicitlyForSeconds(int seconds) {
        webDriver.manage().timeouts().implicitlyWait(seconds, SECONDS);
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

    public void close() {
        this.webDriver.close();
    }
}
