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
import java.util.*;
import java.io.IOException;

class LoginPage extends DeviantArtPageBase {
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password"); 
    private LandingPage landingPage;
    private LoginPage loginPage;
    private ConfigFileReader reader;
    private Properties props;
    public LoginPage(WebDriver driver){
        super(driver);
    }

    public HomePage login(String usernameProp, String passwordProp) throws IOException{
        // Open the Landing page
        landingPage = new LandingPage(this.driver);
        // Go to Login Page and submit the Login Form
        loginPage = landingPage.goToLoginPage();
        reader = new ConfigFileReader();
        props = reader.readConfigurationFile();
        this.waitAndReturnElement(usernameInput).sendKeys(props.getProperty(usernameProp));
        this.waitAndReturnElement(passwordInput).sendKeys(props.getProperty(passwordProp)+"\n");
        return new HomePage(this.driver);
    }

}
