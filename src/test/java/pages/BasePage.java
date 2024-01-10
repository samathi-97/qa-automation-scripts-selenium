package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BasePage {
    public static WebDriver driver;
    //String url;
   String url = "https://academybugs.com/find-bugs/";


    @BeforeClass(alwaysRun=true)
    public void setup(ITestContext context){

        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        context.setAttribute("WebDriver", driver);

//--------To Open With Edge browsers-------------------------------------------------------------------------
//        System.setProperty("webdriver.edge.driver", "C:\\chromeDriver\\edgedriver_win64\\msedgedriver.exe");
//        driver = new EdgeDriver(); // Remove "WebDriver" here
//        driver.manage().window().maximize();
//        driver.get(url);
//        context.setAttribute("WebDriver", driver);
    }

    @AfterTest(alwaysRun=true)
    public void tearDown() {
        driver.quit();
    }
}
