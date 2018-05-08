package pages;

import drivers.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ExpediaResultPage {
    private static WebDriver driver = null;

    private By priceList = By.xpath("//*[@data-test-id=\"listing-price-dollars\"]");
    private By notificationBox = By.xpath("//*[@class=\"notification-text\"]");

    public ExpediaResultPage() {
        this.driver = DriverProvider.getInstance().getDriver();
    }

    public List<java.lang.String> getPrices() {
        DriverProvider.getInstance().fluentWait(this.notificationBox);
        DriverProvider.getInstance().fluentWait(priceList);
        List<WebElement> prices = this.driver.findElements(priceList);
        List<java.lang.String> listPrices = new ArrayList<>();
        for (int i=0; i<prices.size(); i++) {
            listPrices.add(prices.get(i).getText());
        }
        return listPrices;
    }

    public void Close() {
        DriverProvider.getInstance().close();
    }

}
