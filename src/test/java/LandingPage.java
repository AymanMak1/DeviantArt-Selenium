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

public class LandingPage extends DeviantArtPageBase {

    private By LandingPageTitle = By.xpath("//*[@id='root']/div[1]/div/main/div[1]/header/div[1]/h1");
    private By loginBtn = By.xpath("//*[@id='root']/header/div[1]/div[2]/a[2]");
    final private String rootUrl = "https://www.deviantart.com";
    private By LandingPageMenuItem = By.xpath("//*[@id='root']/div[1]/div/main/div[1]/header/div[2]/div/div/a[1]/span");

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver.get(rootUrl);
    }

    public String getLandingPageTitle(){
        return this.waitAndReturnElement(LandingPageTitle).getText();
    }

    public LoginPage goToLoginPage(){
        this.waitAndReturnElement(loginBtn).click();
        return new LoginPage(this.driver);
    }

    public String getLandingPageMenuItem(){
        return this.waitAndReturnElement(LandingPageMenuItem).getText();
    }

}
