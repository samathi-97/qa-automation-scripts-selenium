package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;
    By checkoutButtonLocator = By.xpath("//*[@id=\"post-1374\"]/div/section/div[4]/div[7]/a");
    By updateButtonLocator = By.className("ec_cartitem_update_button");
    By plusButtonLocator = By.cssSelector("td.ec_plus_column input.ec_plus");
    //Absolute Xpath
    By quantityInputLocator = By.xpath("/html/body/div[3]/div/div/div[1]/main/article/div/section/div[3]/table/tbody/tr[3]/td[5]/table/tbody/tr[1]/td[2]/input");
    By subtotalLocator = By.xpath("//div[@class='ec_cart_price_row_total' and @id='ec_cart_subtotal']");
    By shippingLocator = By.xpath("//div[@class='ec_cart_price_row_total' and @id='ec_cart_shipping']");
    By grandTotalOfCartLocator = By.xpath("//*[@id=\"ec_cart_total\"]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //To check cart page loaded successfully.
    public boolean isCartPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButtonLocator)).isDisplayed();
    }

    //Increase the Quantity of the item
    public void addItemsToCart(int itemCount) {
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(plusButtonLocator));
        for (int i = 0; i < itemCount; i++) {
            //driver.findElement(plusButtonLocator).click();
            plusButton.click();
        }
    }

    //Get the actual quantity of item in the cart
    public int getQuantityValue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(quantityInputLocator));
        String qty = driver.findElement(quantityInputLocator).getAttribute("value");
        if (!qty.isEmpty()) {
            String onlyNumbers = qty.replaceAll("[^0-9]", "");
            if (!onlyNumbers.isEmpty()) {
                int actualQuantityInTheCart;
                actualQuantityInTheCart = Integer.parseInt(onlyNumbers);
                return actualQuantityInTheCart;
            }
        }
        return -1;
    }

    //Update quantity of one good
    public void clickUpdateButton() {
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(updateButtonLocator));
        updateButton.click();
    }

    //Calculate the total of the sopping cart
    public double calculateCartTotal() {
        wait.until(ExpectedConditions.presenceOfElementLocated(subtotalLocator));
        wait.until(ExpectedConditions.presenceOfElementLocated(shippingLocator));
        String subtotalText = driver.findElement(subtotalLocator).getText().replaceAll("[^0-9.]", "");
        String shippingText = driver.findElement(shippingLocator).getText().replaceAll("[^0-9.]", "");
        double subtotal = Double.parseDouble(subtotalText);
        double shipping = Double.parseDouble(shippingText);
        return subtotal + shipping;
    }

    //Get the Grand Total given in the web page
    public double getCartGrandTotal() {
        wait.until(ExpectedConditions.presenceOfElementLocated(grandTotalOfCartLocator));
        String total = driver.findElement(grandTotalOfCartLocator).getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(total);
    }

}

