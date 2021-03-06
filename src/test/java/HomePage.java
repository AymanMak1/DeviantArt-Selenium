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

class HomePage extends DeviantArtPageBase {

    Actions action = new Actions(driver);
    private By homePageMenuItem = By.xpath("//*[@id='root']/div[1]/div/main/div[1]/header/div[2]/div/div/a[1]/span");
    private By userProfileCTA = By.xpath("/html/body/div[1]/header/div[1]/div[4]/a");
    private By userProfileDropdown = By.xpath("//*[@id='root']/header/div[1]/div[4]/div");
    private By logoutCTA = By.xpath("//*[@id='site-header-user-menu-group-4-option-1']"); 
    private By submitDeviationCTA = By.xpath("//*[@id='site-header-submit-button']"); 

    public HomePage(WebDriver driver){
        super(driver);
    }

    public String getHomePageMenuItem(){
        return this.waitAndReturnElement(homePageMenuItem).getText();
    }

    public void hoverUserAccount() {
        Actions action = new Actions(driver);
        WebElement userDropDownMenu = this.waitAndReturnElement(userProfileCTA);
        action.moveToElement(userDropDownMenu).perform();
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ie){
        }
    }

    public UserPage goToUserPage(){
        WebElement userDropDownMenu = this.waitAndReturnElement(userProfileCTA);
        userDropDownMenu.click();
        return new UserPage(this.driver);
    }

    public FileUpload goToDeviationSubmissionPage(){
        this.waitAndReturnElement(submitDeviationCTA).click();
        return new FileUpload(this.driver);
    }

    public LandingPage logout(){
        this.waitAndReturnElement(logoutCTA).click();
        return new LandingPage(this.driver);
    }
}
