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

class DeviationPage extends DeviantArtPageBase {

    private By deviationTitle = By.xpath("//*[@id='root']/main/div/div[3]/div/div[1]/div/div[2]/div[1]/h1");

    private By deviationOwner = By.xpath("//*[@id='root']/main/div/div[3]/div/div[1]/div/div[2]/div[2]/div/div/a/span[1]");

    private By deviationExample = By.xpath("//*[@id='root']/div[1]/div/main/div[2]/div/div/div[2]/div/div/div[1]/div/div/div/section/a");

    public DeviationPage(WebDriver driver){
        super(driver);
    }

    public String getDeviationTitle(){
        return this.waitAndReturnElement(deviationTitle).getText();
    }

    public String getDeviationOwner(){
        return this.waitAndReturnElement(deviationOwner).getText();
    }

    /*public submitComment(){
        return this.waitAndReturnElement(deviationExample).click();
    }*/



}
