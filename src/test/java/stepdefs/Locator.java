package stepdefs;

import org.openqa.selenium.By;

public interface Locator {
    By lSearchArea = By.name("search");
    By lSearchButton = By.xpath("(//button[contains(@type,'button')])[4]");
    By lProduct=By.xpath("//div[contains(@class,'product-layout')][last()]//div[@class='caption']/h4");
    By lUsername = By.xpath("//input[@name='username']");
    By lPassword = By.xpath("//input[@name='password']");
    By lLoginButton = By.xpath("//button[contains(@type,'submit')]");
    By lAdmin = By.xpath("(//span[contains(.,'Admin')])[1]");
    By lUsernameInput = By.xpath("(//input[contains(@class,'oxd-input oxd-input--active')])[2]");



}
