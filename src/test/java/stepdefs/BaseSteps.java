package stepdefs;

import driver.Driver1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BaseSteps {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BaseSteps(){
        driver = Driver1.getDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(100));
        wait = Driver1.getWait();
    }

    public void click(By locator){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        click(element);
    }


    public void click(WebElement element){

        wait.until(driver1 -> {
            try {
                element.click();
                return true;
            }catch (Exception e1){
                try {
                    new Actions(driver1).moveToElement(element).click().perform();
                    return true;
                }catch (Exception e2){
                    try {
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].click()", element);
                        return true;
                    }catch (Exception e3){
                        return false;
                    }
                }
            }
        });

    }



    public void sendkeys(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        sendkeys(element, text);
    }

    public void sendkeys(WebElement element, String text){
        wait.until(driver1 -> {
            try {
                element.clear();
                element.sendKeys(text);
                return true;
            }catch (Exception e1){
                try {
                    element.clear();
                    new Actions(driver1).moveToElement(element).sendKeys(text).perform();
                    return true;
                }catch (Exception e2){
                    try {
                        element.clear();
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].value='" + text + "'", element);
                        return true;
                    }catch (Exception e3){
                        return false;
                    }
                }
            }
        });

    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public By getXpathOfButtonOfListedProduct(String text, int index){
        return By.xpath("//div[@class='product-thumb' and .//div[@class='caption' and .//*[contains(.,'" + text + "')]]]//button[" + index + "]");
    }

    public By getXpathOfButtonOfListedProduct(String text, String button){
        int index = 3;
        if (button.equalsIgnoreCase("wish")) index = 2;
        if (button.equalsIgnoreCase("cart")) index = 1;

        return By.xpath("//div[@class='product-thumb' and .//div[@class='caption' and .//*[contains(.,'" + text + "')]]]//button[" + index + "]");
    }


    public By getXpathOfButtonOfListedProduct(String text, Buttons button){
        int index = button.ordinal()+1;
        return By.xpath("//div[@class='product-thumb' and .//div[@class='caption' and .//*[contains(.,'" + text + "')]]]//button[" + index + "]");
    }

    public enum Buttons {
        cart,
        wish,
        compare;
    }



    public void scrollIntoView(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
    }
    public void bekle(long secont){
        try {
            Thread.sleep(secont);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public WebElement asideMenu(String text){
        WebElement element = driver.findElement(By.xpath("//aside [@id='column-left']/div/a[contains(text(),'"+text+"')]"));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void hover(WebElement element){
        new Actions(driver)
                .moveToElement(element).perform();
    }

    public List<WebElement> getMenuElements(){

        return driver.findElements(By.xpath("//nav[@id='menu']//ul[@class='nav navbar-nav']/li//a"));
    }
    public void hoverClick(List<WebElement> elements){
        //List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            hover(element);
            List<WebElement> elements1 = element.findElements(By.xpath(".//li"));
            for (WebElement webElement : elements1) {
                hover(webElement);
            }

        }
    }
    public void getScreenshot(String name) {

        String isim = "screenShots/" + name + " " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".png";

        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File target = new File(isim);

        try {
            FileUtils.copyFile(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

