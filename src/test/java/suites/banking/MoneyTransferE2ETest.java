package suites.banking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyTransferE2ETest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = createChromeDriver();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void findsASimpleElementOnTheHomePage() {
        System.out.println("Opening demo.testfire.net and checking the Sign In link");
        driver.get("https://demo.testfire.net");

        boolean signInLinkVisible = driver.findElement(By.linkText("Sign In")).isDisplayed();
        System.out.println("Sign In link visible: " + signInLinkVisible);

        assertTrue(signInLinkVisible);
        driver.findElement(By.linkText("Sign In")).click();
        assertTrue(driver.getCurrentUrl().contains("login.jsp"));
    }

    private WebDriver createChromeDriver() {
        // If ChromeDriver is not on the PATH, set -Dwebdriver.chrome.driver=<path-to-binary>.
        String chromeDriverPath = System.getProperty("webdriver.chrome.driver");
        if (chromeDriverPath == null || chromeDriverPath.isBlank()) {
            System.out.println("No webdriver.chrome.driver set, resolving ChromeDriver with WebDriverManager.");
            WebDriverManager.chromedriver().setup();
        } else {
            System.out.println("Using ChromeDriver binary from webdriver.chrome.driver=" + chromeDriverPath);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1366,768");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}