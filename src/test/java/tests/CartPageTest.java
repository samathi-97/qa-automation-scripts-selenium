package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CartPage;
import pages.ProductPage;

import java.time.Duration;

public class CartPageTest extends BasePage {

    @Test(priority = 0 )
    public void navigateToCart( ) {
        ProductPage productpage = new ProductPage(driver);
        productpage.addToCart();
        productpage.clickCart();
        CartPage checkoutpage = new CartPage(driver);
        Assert.assertTrue(checkoutpage.isCartPageLoaded());
    }

    @Test(priority = 1, dependsOnMethods="navigateToCart")
    public void updateCart() {
        CartPage cartpage = new CartPage(driver);
        cartpage.addItemsToCart(4);
        cartpage.clickUpdateButton();

        // Implicit Wait
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Poll for the updated quantity with a timeout
        int timeoutInSeconds = 5;
        int pollingInterval = 1;
        int expectedQuantity = 5;
        // Poll until the actual quantity matches the expected quantity
        boolean isQuantityUpdated = false;
        for (int i = 0; i < timeoutInSeconds / pollingInterval; i++) {
            int actualQuantity = cartpage.getQuantityValue();
            if (actualQuantity == expectedQuantity) {
                isQuantityUpdated = true;
                break;
            } else {
                try {
                    Thread.sleep(Duration.ofSeconds(pollingInterval).toMillis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        Assert.assertTrue(isQuantityUpdated, "Quantity not updated as expected.");
    }

    @Test(priority = 2 , dependsOnMethods="navigateToCart" )
    public void checkGrandTotal() {
        CartPage cartpage = new CartPage(driver);
        double expectedGrandTotal = cartpage.calculateCartTotal();
        Assert.assertEquals(cartpage.getCartGrandTotal() , expectedGrandTotal ,"Grand total in the cart does not match actual total value of cart");
    }
}
