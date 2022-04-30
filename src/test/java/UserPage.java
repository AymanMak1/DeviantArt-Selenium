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

class UserPage extends DeviantArtPageBase {

    private By username = By.xpath("//*[@id='root']/main/div/div[3]/div/div[1]/div/div[2]/div[2]/div/div/a/span[1] ");
    private By galleryCta = By.xpath("//*[@id='content-container']/div[2]/div[2]/div/div/nav/div[2]/a[2]");
    private By editGallery = By.xpath("//*[@id='content-container']/div[2]/div[3]/div/div[1]/div/button");
    private By featured = By.xpath("/html/body/div[5]/div/div/section/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]");
    private By source = By.xpath("/html/body/div[5]/div/div/section/div[2]/div/div[1]/div[2]/div/div/div[1]");
    private By target = By.xpath("/html/body/div[5]/div/div/section/div[2]/div/div[1]/div[2]/div/div/div[2]"); 
    private By doneDragAndDrop = By.xpath("/html/body/div[5]/div/div/section/div[3]/div/div[2]/div/button");

    public UserPage(WebDriver driver){
        super(driver);
    }

    public String getAuthentifiedUser(){
        String url = this.driver.getCurrentUrl();
        String segments[] = url.split("/");
        String AuthentifiedUser = segments[segments.length - 1];
        return AuthentifiedUser;
    }

    public void dragAndDrop(){
        this.waitAndReturnElement(galleryCta).click();
        this.waitAndReturnElement(editGallery).click();
        this.waitAndReturnElement(featured).click();
        WebElement sourceEle = this.waitAndReturnElement(source);
        WebElement targetEle = this.waitAndReturnElement(target);
        int targetEleXOffset = targetEle.getLocation().getX() -50;
        int targetEleYOffset = targetEle.getLocation().getY() - 50;
        Actions actionProvider = new Actions(driver);
        // Performs dragAndDropBy onto the  target element offset position
        actionProvider.dragAndDropBy(sourceEle, targetEleXOffset, targetEleYOffset).build().perform();
    }

}
