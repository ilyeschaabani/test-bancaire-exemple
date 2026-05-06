package suites.authentification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class TestLoginOK {
    @Test
    public void testConnexionReussie() {
        // Récupère l’URL de base depuis une propriété système
        String baseUrl = System.getProperty("BASE_URL", "https://recette-web.attijaribank.com");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // pour exécution sans écran
        WebDriver driver = new ChromeDriver(options);

        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("pass123");
        driver.findElement(By.id("login-btn")).click();

        String welcomeText = driver.findElement(By.id("welcome")).getText();
        assertTrue(welcomeText.contains("Bonjour"));

        driver.quit();
    }
}