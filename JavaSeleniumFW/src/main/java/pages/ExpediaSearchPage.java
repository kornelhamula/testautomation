package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import drivers.DriverProvider;

public class ExpediaSearchPage {
    private static WebDriver driver = null;

    String homePageURL = "http://www.expedia.ie";
    private final By imgLogo = By.id("header-logo");
    private final By buttonFlyTab = By.xpath("//*[@id=\"tab-flight-tab-hp\"]");
    private final By buttonFlights = By.id("flight-type-roundtrip-label");
    private final By buttonRoundTrip = By.id("flight-type-roundtrip-label");
    private final By searchButton = By.xpath("//*[@id=\"gcw-flights-form-hp-flight\"]/div[8]/label/button");
    private final By textboxFlyingFrom = By.id("flight-origin-hp-flight");
    private String stringLocatorFlyingFrom = "//a[@role=\"option\" and @data-value='flyingFrom']";
    private String stringLocatorFlyingTo = "//a[@role=\"option\" and @data-value='flyingTo']";
    private final By textboxFlyingTo = By.id("flight-destination-hp-flight");
    private final By textboxDepartingDate = By.id("flight-departing-hp-flight");
    private final By textboxReturningDate = By.id("flight-returning-hp-flight");

    public void open() {
        driver = DriverProvider.getInstance().getDriver();
        driver.get(homePageURL);
        DriverProvider.getInstance().fluentWait(this.imgLogo);
        DriverProvider.getInstance().fluentWait(this.buttonFlyTab).click();
    }

    public void setTextboxFlyingFrom(String from) {
        DriverProvider.getInstance().fluentWait(textboxFlyingFrom).sendKeys(from);
    }

    public void selectFlyingFrom(String from) {
        DriverProvider.getInstance().fluentWait(By.xpath(this.stringLocatorFlyingFrom.replace("flyingFrom", from))).click();
    }

    public void selectFlyingTo(String to) {
        String selectFlyingToXPath = this.stringLocatorFlyingTo.replace("flyingTo", to);
        DriverProvider.getInstance().fluentWait(By.xpath(selectFlyingToXPath)).click();
    }

    public void setTextboxFlyingTo(String to) {
        DriverProvider.getInstance().fluentWait(this.textboxFlyingTo).sendKeys(to);
    }

    public void setDepartureDate(String departureDate) {
        DriverProvider.getInstance().fluentWait(this.textboxDepartingDate).clear();
        DriverProvider.getInstance().fluentWait(this.textboxDepartingDate).sendKeys(departureDate);
    }

    public void setReturningDate(String returningDate) {
        DriverProvider.getInstance().fluentWait(this.textboxReturningDate).clear();
        DriverProvider.getInstance().fluentWait(this.textboxReturningDate).sendKeys(returningDate);
    }

    public ExpediaResultPage clickOnSearch() {
        DriverProvider.getInstance().fluentWait(this.searchButton).click();
        return new ExpediaResultPage();
    }
    
    public void Close() {
        DriverProvider.getInstance().close();
    }

}
