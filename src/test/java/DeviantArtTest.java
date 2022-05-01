import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void testLandingPage(){
        LandingPage landingPage = new LandingPage(this.driver);
        System.out.println(landingPage.getBodyText());
        System.out.println("\n" + landingPage.getLandingPageTitle());
        Assert.assertTrue(landingPage.getLandingPageTitle().contains("Home"));
    }

    @Test
    public void testSearch(){
        LandingPage landingPage = new LandingPage(this.driver);
        landingPage.search("Demon Slayer");
        Assert.assertTrue(landingPage.getBodyText().contains("Deviations"));
        Assert.assertTrue(landingPage.getBodyText().contains("Demon Slayer"));
    }
  
    @Test
    public void testLogin(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        System.out.println("Check if the user logged in by checking if the \"Deviations\" Menu Item is shown : " + 
                            homePage.getHomePageMenuItem());
        Assert.assertTrue(homePage.getHomePageMenuItem().contains("Deviations"));
    }

    @Test
    public void testAuthentifiedUser(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        // Go to the User page and check the authentified user by checking the username in the url
        UserPage userPage = homePage.goToUserPage();
        System.out.println("The Current Driver URL : " +
                            this.driver.getCurrentUrl() + " | The Authentified user : " + 
                            userPage.getAuthentifiedUser());
        Assert.assertTrue(this.driver.getCurrentUrl().contains(userPage.getAuthentifiedUser()));
    }

    @Test
    public void testHover(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        homePage.hoverUserAccount();
    }

    @Test
    public void testStaticPage(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        StaticPage staticPage = new StaticPage(this.driver);
        String[] staticPages = {"https://www.deviantart.com/topic", 
                                "https://www.deviantart.com/daily-deviations",
                                "https://www.deviantart.com/popular/deviations"};
        staticPage.loadedCorrectlyOrNot(staticPages);
    }

    @Test
    public void testHistoryBack(){
        // navigate to a static page and go back to the home page
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        StaticPage staticPage = new StaticPage(this.driver);
        String[] staticPages = {"https://www.deviantart.com/topic"};
        staticPage.loadedCorrectlyOrNot(staticPages);
        staticPage.historyBack();
    }
  
    @Test
    public void testDeviationFileUpload(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");

        // Go to the deviaition submission page and upload the file
        FileUpload deviationFile = homePage.goToDeviationSubmissionPage();
        String deviationFilePath = "imgs\\deviationFileUploadImage.jpg";
        deviationFile.submitADeviation(deviationFilePath);

        DeviationPage deviationPage = new DeviationPage(this.driver);
        System.out.println("Expected: " + deviationPage.getDeviationTitle() + " | Actual: " + deviationFile.getDeviationTitle());
        Assert.assertEquals(deviationPage.getDeviationTitle(), deviationFile.getDeviationTitle());
        System.out.println("The owner and the title of the deviation are correct. The file has been successfuly uploaded!");
    }

    @Test
    public void testDragAndDrop(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        UserPage userPage = homePage.goToUserPage();
        userPage.dragAndDrop();
    }

    @Test
    public void testlogout(){
        LoginPage loginPage = new LoginPage(this.driver);
        HomePage homePage = loginPage.login("username", "password");
        homePage.hoverUserAccount();
        homePage.logout();
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
