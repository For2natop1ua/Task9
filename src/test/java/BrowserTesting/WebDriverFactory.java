package BrowserTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
    public WebDriver createWebDriver(DriverType driverType){
        WebDriver webDriver;
        switch (driverType){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/Driver/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            case IE:
                System.setProperty("webdriver.ie.driver", "src/Driver/IEDriverServer.exe");
                webDriver = new InternetExplorerDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/Driver/geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Not supported Driver: " + driverType);
        }
        return webDriver;
    }
}
