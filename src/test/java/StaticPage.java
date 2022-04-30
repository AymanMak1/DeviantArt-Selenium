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
import java.io.*;

class StaticPage extends DeviantArtPageBase {

    public StaticPage(WebDriver driver){
        super(driver);
    }

    public void loadedCorrectlyOrNot(String[] pages){
        for (int i = 0; i < pages.length; i++) {
            this.driver.navigate().to(pages[i]);
            if(this.driver.getCurrentUrl() != null){
                Assert.assertEquals(pages[i],this.driver.getCurrentUrl());
                System.out.println("Static page has opened up correctly.("+pages[i]+")");
            }
        }

    }

}
