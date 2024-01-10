package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDetailPage {

    WebDriver driver;
    WebDriverWait wait;
    By ProductImageLocator = By.xpath("//*[@id=\"ec_product_image_effect_4481370\"]/a");
    By ManufactureLinkLocator = By.xpath("//*[@id=\"manufacturer-bug\"]/a");
    By TwitterShareButtonLocator = By.xpath("//*[@id=\"post-1675\"]/div/section/div[1]/div[3]/div[2]/div[2]/a");
    By PriceRangeLocator = By.xpath("//*[@id=\"ec_pricepointwidget-2\"]/div/div[2]/a[1]");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    // view one item with description
    public void NavigateToItemDescriptionPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductImageLocator));
        driver.findElement(ProductImageLocator).click();
    }
    // navigate to manufacture detail page
    public void clickManufactureLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ManufactureLinkLocator));
        driver.findElement(ManufactureLinkLocator).click();
    }
    // navigate to share the item on Twitter
    public void clickTwitterShareButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TwitterShareButtonLocator));
        driver.findElement(TwitterShareButtonLocator).click();
    }
    //verify wheather a link return 200 success code or not
    public boolean verifyLink(String url) throws IOException {
        URL link = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
        httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
        httpURLConnection.connect();
        System.out.println("URL is :" + link);
        System.out.println("http connection is :" + httpURLConnection);
        if (httpURLConnection.getResponseCode() == 200) {
            return true;
        } else {
            return false;
        }
    }
    // get the URL of current page
    public String getCurrentPageUrl() {
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String currentPageUrl = driver.getCurrentUrl();
        return currentPageUrl;

    }
    //Check Validity of URL
    public boolean isValidURL(String url) {
        String urlPattern = "^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}(/.*)?$";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
    //Click Price range
    public void clickPriceRange() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PriceRangeLocator));
        driver.findElement(PriceRangeLocator).click();
    }
    //get the sourse page script
    public String getPageSource() {
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String initialPageSource = driver.getPageSource();
        return initialPageSource;
    }
    //Switching to a new tab when opening a link
    public void swichToNewTab() {
        String parentWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

    }
    //Move back to parent window
    public void swichToParentWindow() {
        String parentWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(parentWindowHandle);
    }
}
