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

public class UserPage extends DeviantArtPageBase {

    private By username = By.xpath("//*[@id='content-container']/div[2]/div[1]/div/div[2]/h1/a/span[1]");

    public UserPage(WebDriver driver){
        super(driver);
    }

    public String getAuthentifiedUser(){
        return this.waitAndReturnElement(this.username).getText();
    }

}
