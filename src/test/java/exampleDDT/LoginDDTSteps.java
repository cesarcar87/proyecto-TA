package exampleDDT;

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

public class LoginDDTSteps {
    WebDriver webDriver;
    DriverFactory driverFactory;
    String browser = "chrome";

    String url = "https://katalon-demo-cura.herokuapp.com/profile.php#login";
    String expectedCuraTitle = "CURA Healthcare Service",
            actualCuraTitle,
            expectedCuraWelcome = "Appointment",
            actualCuraWelcome;

    WebElement inputUser, inputPass, submitBtn;

    @Before
    public void beforeScenario() {
        if (webDriver == null) {
            driverFactory = new DriverFactory();
            webDriver = driverFactory.getDriver(browser);
        }
        webDriver.get(url);

        inputUser = webDriver.findElement(By.id("txt-username"));
        inputPass = webDriver.findElement(By.id("txt-password"));
        submitBtn = webDriver.findElement(By.xpath("//button[@type='submit']"));
    }

    @Given("^User is on the login page$")
    public void user_is_on_the_login_page() {
        actualCuraTitle = webDriver.getTitle();
        assertEquals(expectedCuraTitle, actualCuraTitle);
    }

    //Escenario 2 con datos correctos, escenarios 3 y 4 con datos incorrectos (se usa DDT) - es correcto que 3 y 4 fallen.
    @When("^The user enters username (.*)$")
    public void user_enters_username(String username) {
        inputUser.sendKeys(username);
    }

    @And("^The user enters password (.*)$")
    public void user_enters_password(String password) {
        inputPass.sendKeys(password);
        submitBtn.click();
    }

    //Resultado esperado (el usuario debe ver la página de bienvenida)
    @Then("^The user sees the Welcome page$")
    public void user_sees_welcome() {
        actualCuraWelcome = webDriver.findElement(By.xpath("//h2[contains(text(),'Make Appointment')]")).getText();
        assertTrue(actualCuraWelcome.contains(expectedCuraWelcome));
    }

    @After
    public void afterScenario() throws InterruptedException {
        Thread.sleep(3000);
        if(webDriver!=null) {
            driverFactory.quitDriver();
        }
    }
}
