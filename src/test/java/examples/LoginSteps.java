package examples;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    WebDriver webDriver;
    DriverFactory driverFactory = new DriverFactory();
    String browser = "chrome",
           username = "John Doe",
           password = "ThisIsNotAPassword";

    String expectedCuraTitle = "CURA Healthcare Service",
           actualCuraTitle,
           expectedCuraWelcome = "Appointment",
           actualCuraWelcome;

    WebElement inputUser, inputPass, submitBtn;

    @Before
    public void before() {
        webDriver = driverFactory.getDriver(browser);
        webDriver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");

        inputUser = webDriver.findElement(By.id("txt-username"));
        inputPass = webDriver.findElement(By.id("txt-password"));
        submitBtn = webDriver.findElement(By.xpath("//button[@type='submit']"));
    }

    @Given("A user is on the login page")
    public void user_is_on_the_login_page() {
        actualCuraTitle = webDriver.getTitle();
        assertEquals(expectedCuraTitle, actualCuraTitle);
    }

    //Escenario 1 con datos correctos
    @When("The user enters correct username")
    public void user_enters_correct_username() {
        inputUser.sendKeys(username);
    }

    @And("The user enters correct password")
    public void user_enters_correct_password() {
        inputPass.sendKeys(password);
        submitBtn.click();
    }

    //Resultado esperado (el usuario debe ver la página de bienvenida)
    @Then("The user sees the welcome page")
    public void user_sees_welcome() throws InterruptedException {
        Thread.sleep(5000);
        actualCuraWelcome = webDriver.findElement(By.xpath("//h2[contains(text(),'Make Appointment')]")).getText();
        assertTrue(actualCuraWelcome.contains(expectedCuraWelcome));
    }

    @After
    public void after() {
        if(webDriver!=null){
            driverFactory.quitDriver();
        }
    }
}
