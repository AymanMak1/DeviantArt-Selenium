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
    private By homePageMenuItem = By.xpath("//*[@id='root']/div[1]/div/main/div[1]/header/div[2]/div/div/a[1]/span");
    private By userProfileCTA = By.xpath("/html/body/div[1]/header/div[1]/div[4]/a");
    private By searchInput = By.xpath("//*[@id='search-input']");
    private By logoutCTA = By.xpath("//*[@id='site-header-user-menu-group-4-option-1']/span"); 
    private By submitDeviationCTA = By.xpath("//*[@id='site-header-submit-button']");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public String getHomePageMenuItem(){
        return this.waitAndReturnElement(this.homePageMenuItem).getText();
    }

    public void search(String searchText){
        WebElement search = this.waitAndReturnElement(this.searchInput);
        search.click();
        search.sendKeys(searchText+"\n");
    }

    public UserPage goToUserPage(){
        WebElement userDropDownMenu = this.waitAndReturnElement(this.userProfileCTA);
        userDropDownMenu.click();
        return new UserPage(this.driver);
        //action.moveToElement(userDropDownMenu).build().perform();
    }

    public FileUpload goToDeviationSubmissionPage(){
        this.waitAndReturnElement(this.submitDeviationCTA).click();
        return new FileUpload(this.driver);
    }

    public LandingPage logout(){
        this.waitAndReturnElement(this.logoutCTA).click();
        return new LandingPage(this.driver);
    }
}
