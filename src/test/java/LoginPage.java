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

public class LoginPage extends DeviantArtPageBase {
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password"); 
    private By loginCTA = By.xpath("//*[@id='loginbutton']"); 

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public HomePage login(String username, String password){
        this.waitAndReturnElement(usernameInput).sendKeys(username);
        this.waitAndReturnElement(passwordInput).sendKeys(password+"\n");
        return new HomePage(this.driver);
    }

}
