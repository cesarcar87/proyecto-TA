package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private WebDriver webDriver;

    //#region Configuration
    private final String MAXIMIZED = "start-maximized";
    private final String CERTIFICATE = "--ignore-certificate-errors";
    private final Duration DURATION = Duration.ofSeconds(10);
    //#endregion

    public DriverFactory(){
    };

    public WebDriver getDriver(String browser) {
        if (webDriver == null) {
            webDriver = createDriver(browser);
        }
        return webDriver;
    }

    public void quitDriver(){
        if(webDriver!=null) {
            webDriver.quit();
        }
    }


    private WebDriver createDriver(String browser) {
        WebDriver driver;
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(MAXIMIZED);
            chromeOptions.addArguments(CERTIFICATE);
            ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
            chromeDriver.manage().timeouts().implicitlyWait(DURATION);
            chromeDriver.manage().deleteAllCookies();
            driver = chromeDriver;
        } else if(browser.equalsIgnoreCase("firefox")) {
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.manage().timeouts().implicitlyWait(DURATION);
            firefoxDriver.manage().window().maximize();
            firefoxDriver.manage().deleteAllCookies();
            driver = firefoxDriver;
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
