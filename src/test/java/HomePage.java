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

public class HomePage extends DeviantArtPageBase {

    Actions action = new Actions(driver);
    private By LandingPageMenuItem = By.xpath("//*[@id='root']/div[1]/div/main/div[1]/header/div[2]/div/div/a[1]/span");
    private By userIcon = By.xpath("//*[@id='root']/header/div[1]/div[4]");
    private By searchInput = By.xpath("//*[@id='search-input']");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public String getLandingPageMenuItem(){
        return this.waitAndReturnElement(this.LandingPageMenuItem).getText();
    }

    public void search(String searchText){
        WebElement search = this.waitAndReturnElement(this.searchInput);
        search.click();
        search.sendKeys(searchText+"\n");
    }

    public void goToUserPage(){
        WebElement userDropDownMenu = this.waitAndReturnElement(this.userIcon);
        action.moveToElement(userDropDownMenu).build().perform();
    }
}
