import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import java.util.*;
import java.io.*;

class FileUpload extends DeviantArtPageBase {

    private By fileInput = By.xpath("//*[@id='stash-form']/a/input");
    private By submitDeviationBtn = By.xpath("//*[@id='ile-contents']/span/button");
    private By deviationTitle = By.xpath("//*[@id='devtitle']");
    private By deviationDescription = By.cssSelector(".writer.selectable.no-lub.put-art-here.ui-droppable");
    public FileUpload(WebDriver driver){
        super(driver);
    }

    public void submitADeviation(String filePath){
        driver.switchTo().frame("deviation-0");
        String inputSpan = "Choose a file to upload";
        WebElement inputSpanElem = driver.findElement(By.xpath("//*[@id='stash-form']/a/span"));
        System.out.println("Frame identified. Input Element has been found inside the frame with the xpath: " + inputSpanElem.getText());
        Assert.assertEquals(inputSpanElem.getText(),inputSpan);
        WebElement fileInputElement = driver.findElement(fileInput);
        fileInputElement.sendKeys(getFilePath(filePath));
        WebElement deviationTitleElem = this.waitAndReturnElement(deviationTitle);
        deviationTitleElem.clear();
        deviationTitleElem.sendKeys("MySeleniumDeviation");
        try{
            WebElement deviationDescElem = this.waitAndReturnElement(deviationDescription);
            deviationDescElem.click();
            String script = "arguments[0].innerHTML='This my deviation upload using Selenium.'";
            ((JavascriptExecutor) driver).executeScript(script, deviationDescElem);
            Thread.sleep(3000);
            this.waitAndReturnElement(submitDeviationBtn).click();
        }
        catch(InterruptedException ie){
        }

    }

    public String getDeviationTitle(){
        String expectedDeviationTitle = "MySeleniumDeviation";
        return expectedDeviationTitle;
    }

    public String getFilePath(String filePath){
        return new File(filePath).getAbsolutePath();
    }

}
