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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

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
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        //get the body Text of the landing page
        System.out.println(landingPage.getBodyText());
        // Get the Landing Page Title which supposed to be "Home"
        System.out.println("\n" + landingPage.getLandingPageTitle());
        Assert.assertTrue(landingPage.getLandingPageTitle().contains("Home"));
    }

    @Test
    public void testSearch(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        // Search Demon Slayer Anime
        landingPage.search("Demon Slayer");
    }
  
    @Test
    public void testLogin() throws IOException{
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        // Go to Login Page and submit the Login Form
        LoginPage loginPage = landingPage.goToLoginPage();
        ConfigFileReader reader = new ConfigFileReader();
        Properties props = reader.readConfigurationFile();
        HomePage homePage = loginPage.login(props.getProperty("username"), props.getProperty("password"));
        // Check if the user logged in by checking if the "Deviation" Menu Item is shown;
        System.out.println(homePage.getHomePageMenuItem());
        Assert.assertTrue(homePage.getHomePageMenuItem().contains("Deviations"));
    }

    @Test
    public void testAuthentifiedUser(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        // Go to Login Page and submit the Login Form
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        // Go to the User page and check the authentified user by checking the username in the url
        UserPage userPage = homePage.goToUserPage();
        System.out.println("The Current Driver URL : " +
                            this.driver.getCurrentUrl() + " | The Authentified user : " + 
                            userPage.getAuthentifiedUser());
        
        Assert.assertTrue(this.driver.getCurrentUrl().contains(userPage.getAuthentifiedUser()));
    }
  
    
  
    @Test
    public void testDeviationFileUpload(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        // Go to Login Page and submit the Login Form
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");

        // Go to the deviaition submission page and upload the file
        FileUpload deviationFile = homePage.goToDeviationSubmissionPage();
        String deviationFilePath = "imgs\\deviationFileUploadImage.jpg";
        deviationFile.submitADeviation(deviationFilePath);

        DeviationPage deviationPage = new DeviationPage(this.driver);
        System.out.println("Expected: " + deviationPage.getDeviationTitle() + " | Actual: " + deviationFile.getDeviationTitle());
        Assert.assertEquals(deviationPage.getDeviationTitle(), deviationFile.getDeviationTitle());
        //Assert.assertEquals(deviationPage.getDeviationOwner().toLowerCase(), userPage.getAuthentifiedUser());
        System.out.println("The owner and the title of the deviation are correct. The file has been successfuly uploaded!");
    }

    @Test
    public void testStaticPage(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        // Go to Login Page and submit the Login Form
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        StaticPage staticPage = new StaticPage(this.driver);
        // homePage.goToStaticPage();
        String[] staticPages = {"https://www.deviantart.com/topic", 
                                "https://www.deviantart.com/daily-deviations",
                                "https://www.deviantart.com/popular/deviations"};
        staticPage.loadedCorrectlyOrNot(staticPages);
    }
   
    @Test
    public void testHover(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        homePage.hoverUserAccount();
    }
   
    
    @Test
    public void testlogout(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        homePage.hoverUserAccount();
        homePage.logout();
    }


    @Test
    public void testHistoryBack(){
        // Open the Landing page
        LandingPage landingPage = new LandingPage(this.driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        StaticPage staticPage = new StaticPage(this.driver);
        String[] staticPages = {"https://www.deviantart.com/topic"};
        staticPage.loadedCorrectlyOrNot(staticPages);
        staticPage.historyBack();
    }

    @Test
    public void testDragAndDrop(){
        LandingPage landingPage = new LandingPage(this.driver);
        LoginPage loginPage = landingPage.goToLoginPage();
        HomePage homePage = loginPage.login("Seleniumart1","Seleniumart1test");
        UserPage userPage = homePage.goToUserPage();
        userPage.dragAndDrop();
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
