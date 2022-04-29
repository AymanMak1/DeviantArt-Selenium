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

    private By username = By.xpath("//*[@id='root']/main/div/div[3]/div/div[1]/div/div[2]/div[2]/div/div/a/span[1] ");

    public UserPage(WebDriver driver){
        super(driver);
    }

    public String getAuthentifiedUser(){
        String url = this.driver.getCurrentUrl();
        String segments[] = url.split("/");
        String AuthentifiedUser = segments[segments.length - 1];
        return AuthentifiedUser;
    }

}
