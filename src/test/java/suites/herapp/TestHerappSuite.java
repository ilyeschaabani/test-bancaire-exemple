package suites.herapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

public class TestHerappSuite {

    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() {
        baseUrl = System.getProperty("BASE_URL", "https://the-internet.herokuapp.com");
        driver = new HtmlUnitDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testLoginSuccess() {
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test(priority = 2)
    public void testLoginFailure() {
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("wrong");
        driver.findElement(By.id("password")).sendKeys("wrong");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains("Your username is invalid!"));
    }

    @Test(priority = 3)
    public void testCheckbox() {
        driver.get(baseUrl + "/checkboxes");
        // Checkbox 1 (première case) : initialement unchecked, on la coche
        var cb1 = driver.findElements(By.cssSelector("input[type='checkbox']")).get(0);
        if (!cb1.isSelected()) {
            cb1.click();
        }
        assertTrue(cb1.isSelected());

        // Checkbox 2 (deuxième case) : initialement checked, on la décoche
        var cb2 = driver.findElements(By.cssSelector("input[type='checkbox']")).get(1);
        if (cb2.isSelected()) {
            cb2.click();
        }
        assertFalse(cb2.isSelected());
    }

    @Test(priority = 4)
    public void testFileUpload() {
        // Nécessite un fichier de test dans le répertoire "src/test/resources" de ton projet Maven.
        // Place un fichier "test.txt" (créé au préalable) à la racine de src/test/resources.
        driver.get(baseUrl + "/upload");
        String filePath = System.getProperty("user.dir") + "/src/test/resources/test.txt";
        driver.findElement(By.id("file-upload")).sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();
        String message = driver.findElement(By.id("uploaded-files")).getText();
        assertTrue(message.contains("test.txt"));
    }

    @Test(priority = 5)
    public void testSecurePageAfterLogin() {
        // Se logguer d'abord
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Accéder à la page protégée
        driver.get(baseUrl + "/secure");
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Secure Area"));
        assertTrue(driver.findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
    }

    @Test(priority = 6)
    public void testLogout() {
        // Login
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Logout
        driver.findElement(By.cssSelector("a[href='/logout']")).click();
        String message = driver.findElement(By.id("flash")).getText();
        assertTrue(message.contains("You logged out"));
    }

    @Test(priority = 7)
    public void testDropdown() {
        driver.get(baseUrl + "/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");
        String selectedText = dropdown.getFirstSelectedOption().getText();
        assertEquals(selectedText, "Option 1");
    }

@Test(priority = 8)
public void testPageNotFound() {
    driver.get(baseUrl + "/random-url");
    String bodyText = driver.findElement(By.tagName("body")).getText();
    assertTrue(bodyText.contains("Not Found"));
}
}