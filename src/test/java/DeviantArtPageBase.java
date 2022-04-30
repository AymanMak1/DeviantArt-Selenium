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
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import java.util.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.*;
//import org.openqa.selenium.support.ui.*;

class DeviantArtPageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions builder;
    protected By searchInput = By.xpath("//*[@id='search-input']");

    public DeviantArtPageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.builder = new Actions(driver);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }
    public void historyBack(){
        JavascriptExecutor js = (JavascriptExecutor) driver; 
        js.executeScript("window.history.go(-1)");
        System.out.println("History Back | The Current URL is :" + this.driver.getCurrentUrl());
    }
    
    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    public void search(String searchText){
        WebElement search = this.waitAndReturnElement(this.searchInput);
        search.click();
        search.sendKeys(searchText+"\n");
    }

}
