import static org.testng.Assert.*;

import drivers.DriverProvider;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ExpediaResultPage;
import pages.ExpediaSearchPage;

import java.util.List;

public class Testcases {

    ExpediaSearchPage expediaSearchPage;
    ExpediaResultPage expediaResultPage;

    public Testcases() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        expediaSearchPage = new ExpediaSearchPage();
    }

    @Test
    public void openPage() {
        expediaSearchPage.open();
    }

   @Test(dependsOnMethods={"openPage"})
    public void searchOnPage() throws InterruptedException {
        expediaSearchPage.setTextboxFlyingFrom("London");
        expediaSearchPage.selectFlyingFrom("London, England, UK (LHR-Heathrow)");
        expediaSearchPage.setTextboxFlyingTo("Dublin");
        expediaSearchPage.selectFlyingTo("Dublin, Ireland (DUB)");
        expediaSearchPage.setDepartureDate("12/07/2018");
        expediaSearchPage.setReturningDate("12/08/2018");
        this.expediaResultPage = expediaSearchPage.clickOnSearch();
    }

    @Test(dependsOnMethods={"searchOnPage"})
    public void priceOnTheFirstRowCheck() {
        List<String> priceList = this.expediaResultPage.getPrices();
        String firstPrice = priceList.get(0).replace("€","").replace("$","");
        Assert.assertEquals(firstPrice, "105");
    }

    @Test(dependsOnMethods={"searchOnPage"})
    public void priceOnTheFirstRowIsNot1EUR() {
        List<String> priceList = this.expediaResultPage.getPrices();
        String firstPrice = priceList.get(0).replace("€","").replace("$","");
        Assert.assertNotEquals(firstPrice, "1");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
     DriverProvider.getInstance().close();
    }

}
