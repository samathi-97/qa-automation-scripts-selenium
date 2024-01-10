package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ProductDetailPage;
import java.io.IOException;

public class ProductDetailsPageTest extends BasePage {

    @Test(priority = 0)
    public void verifyManufacturelink() throws IOException {
        ProductDetailPage productdetailpage = new ProductDetailPage(driver);
        productdetailpage.NavigateToItemDescriptionPage();
        productdetailpage.clickManufactureLink();
        String currentPageUrl = productdetailpage.getCurrentPageUrl();
        driver.navigate().back();
        boolean result = productdetailpage.verifyLink(currentPageUrl);
        Assert.assertTrue(result, "The link is broken.");
    }

    @Test(priority = 1 )
    public void verifyFilterByPrice() {
        ProductDetailPage productdetailpage = new ProductDetailPage(driver);
        String sourse1 = productdetailpage.getPageSource();
        productdetailpage.clickPriceRange();
        String sourse2 = productdetailpage.getPageSource();
        Assert.assertEquals(sourse1, sourse2, "Page has not change after clicking Price Range.");
    }

    @Test(priority = 2  )
    public void verifyTwiterShareButton() throws IOException {
        ProductDetailPage productdetailpage = new ProductDetailPage(driver);
        productdetailpage.clickTwitterShareButton();
        productdetailpage.swichToNewTab();
        String currentPageUrl = productdetailpage.getCurrentPageUrl();
        productdetailpage.swichToParentWindow();
        boolean result = productdetailpage.isValidURL(currentPageUrl);
        Assert.assertTrue(result, "The URL is incorrect.");
        productdetailpage.swichToParentWindow();
    }
}
