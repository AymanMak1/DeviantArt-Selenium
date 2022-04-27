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

public class FileUpload extends DeviantArtPageBase {

    private By fileInput = By.xpath("//*[@id='stash-form']/a/input");
    private By submitDeviationBtn = By.xpath("//*[@id='ile-contents']/span/button");

    public FileUpload(WebDriver driver){
        super(driver);
    }

    public void submitADeviation(String filePath){
        System.out.println(this.driver.getCurrentUrl());
        driver.switchTo().frame("deviation-0");
        String inputSpan = "Choose a file to upload";
        WebElement inputSpanElement = driver.findElement(By.xpath("//*[@id='stash-form']/a/span"));
        System.out.println("Frame identified. Input Element has been found inside the frame with the xpath: " + inputSpanElement.getText());
        Assert.assertEquals(inputSpanElement.getText(),inputSpan);
        WebElement fileInputElement = driver.findElement(fileInput);
        fileInputElement.sendKeys(getFilePath(filePath));
        this.waitAndReturnElement(submitDeviationBtn).click();
    }

    public String getFilePath(String filePath){
        return new File(filePath).getAbsolutePath();
    }

}
