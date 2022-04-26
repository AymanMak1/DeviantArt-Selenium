import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import java.util.*;


public class DeviantArtTest {

    public WebDriver driver;
    
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLandingAndLogin(){
        LandingPage landingPage = new LandingPage(this.driver);
        System.out.println(landingPage.getBodyText());
        System.out.println("\n" + landingPage.getLandingPageTitle());
        Assert.assertTrue(landingPage.getLandingPageTitle().contains("Home"));

        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        System.out.println(homePage.getLandingPageMenuItem());
        Assert.assertTrue(homePage.getLandingPageMenuItem().contains("Deviations"));

        homePage.search("Naruto");
        //homePage.goToUserPage();
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
