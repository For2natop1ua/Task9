package BrowserTesting;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.FileUtils.copyFile;

public class TestDifferentBrowser {

    WebDriver driver;
    //IE, CHROME AND FIREFOX.
    DriverType driverType = DriverType.valueOf("IE");
    String input = "SoftServe";
    By search = By.cssSelector("input[type=text]");
    String url = "https://www.google.com/";
    By linkSoftServe = new By.ByPartialLinkText("SoftServe");


    @BeforeMethod
    public void setUP() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.createWebDriver(driverType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test() {
        driver.get(url);
        WebElement searchElement = driver.findElement(search);
        searchElement.sendKeys(input);
        searchElement.submit();
        WebElement searchElementSoft = driver.findElement(linkSoftServe);
        searchElementSoft.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("softserve"));
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess())
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now() + "][" + System.currentTimeMillis() + "].png"));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
    }

    @AfterMethod(dependsOnMethods = "takeScreenshot")
    public void quitBrowser() {
        driver.quit();
    }
}
