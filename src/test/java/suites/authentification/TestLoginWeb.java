package suites.authentification;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class TestLoginWeb {
    @Test
    public void testLoginReussi() {
        String baseUrl = System.getProperty("BASE_URL", "https://the-internet.herokuapp.com");
        String loginUrl = baseUrl + "/login";

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get(loginUrl);
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains("You logged into a secure area!"));

        driver.quit();
    }
}