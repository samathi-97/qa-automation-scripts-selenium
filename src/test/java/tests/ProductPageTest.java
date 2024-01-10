package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ProductPage;

public class ProductPageTest extends BasePage {

    @Test(priority=0 ,  groups = {"smoke"})
    public void verifyItemAddedToCart(){
        ProductPage productpage = new ProductPage(driver);
        productpage.addToCart();
        Assert.assertTrue(productpage.IsItemAddedToCart());
    }

}
