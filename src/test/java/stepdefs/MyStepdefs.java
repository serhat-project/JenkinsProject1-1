package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WindowType;

import static stepdefs.Locator.*;

public class MyStepdefs extends BaseSteps{

    String originalHandle;

    @Given("User is on Opencart website {string}")
    public void userIsOnOpencartWebsite(String url) {
        driver.get(url);
    }

    @When("user searches for {string}")
    public void userSearchesFor(String product) {
        sendkeys(lSearchArea, product);
        click(lSearchButton);
    }

    @And("stores the name of the last product in System.setProperty")
    public void storesTheNameOfTheLastProductInSystemSetProperty() {
        String text = driver.findElement(lProduct).getText();
        System.setProperty("Product", text);
    }


    @And("user switches to a new Tab {string}")
    public void userSwitchesToANewTab(String url) {
        originalHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    @And("user clicks on Login")
    public void userClicksOnLogin() {
        sendkeys(lUsername, "Admin");
        sendkeys(lPassword, "admin123");
        click(lLoginButton);
        waitForVisibility(lAdmin);
        click(lAdmin);
    }

    @And("inputs the stored product name in username")
    public void inputsTheStoredProductNameInUsername() {
        sendkeys(lUsernameInput, System.getProperty("Product"));
    }

    @Then("user takes a screen shot")
    public void userTakesAScreenShot() {
        getScreenshot("JenkinsProject");
        driver.switchTo().window(originalHandle);
    }


}
