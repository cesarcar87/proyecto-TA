package examples;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static org.junit.jupiter.api.Assertions.*;

import utils.DriverFactory;

public class GoogleSearchSteps {
    WebDriver webDriver;
    DriverFactory driverFactory = new DriverFactory();
    String browser = "chrome";

    @Before
    public void setUp() {
        webDriver = driverFactory.getDriver(browser);
        webDriver.get("https://www.google.com/");
    }

    @Given ("A user is on the Google search page")
    public void background() {
        String actualGoogleTitle = webDriver.getTitle();
        assertEquals("Google", actualGoogleTitle);
    }

    @When("Enters {string} in the search box")
    public void userEnterInSearchBox(String searchTerm) throws InterruptedException {
        webDriver.findElement(By.name("q")).sendKeys(searchTerm);
        Thread.sleep(5000);
    }
    @And("Clicks the Search button")
    public void userClickSearchButton() {
        webDriver.findElement(By.name("btnK")).click();
//        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Then("The user should see results related to {string}")
    public void userShouldSeeSearchResults(String searchTerm) throws InterruptedException {
        String actualPageTitle = webDriver.getTitle();
        assertTrue(actualPageTitle.contains(searchTerm));
        Thread.sleep(7000);
    }


    @After
    public void tearDown() {
        if(webDriver!=null){
            driverFactory.quitDriver();
        }
    }
}
