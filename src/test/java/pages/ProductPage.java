package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;
    By addToCartButtonLocator = By.xpath("//*[@id=\"ec_add_to_cart_4\"]");
    By viewCartLocator = By.xpath("//*[@id=\"ec_product_page\"]/div[2]/a");
    By itemAddedMessageLocator = By.xpath("//*[@id=\"ec_product_page\"]/div[2]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //Add an item to shopping cart
    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));
        driver.findElement(addToCartButtonLocator).click();
    }
    //Click cart icon to navigate to the shopping cart
    public void clickCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLocator));
        driver.findElement(viewCartLocator).click();
    }
    //check item added successful message
    public boolean IsItemAddedToCart(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemAddedMessageLocator)).isDisplayed();
    }
    //Testing
    public String getTitle() {
        System.out.println(driver.getTitle());
        return driver.getTitle();
    }

}
