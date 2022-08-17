package com.herokupp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
    }

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = {"negativeTest", "smokeTest"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) throws InterruptedException {
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys(username);

        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.tagName("button")).click();


        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();


        //  Assert.assertEquals(expectedMessage,actualMessage,"Actual Message is not as per expected.");
        Assert.assertTrue(actualMessage.contains(expectedErrorMessage), "Actual message does not contain expected message." +
                "\n Actual Message - " + actualMessage +
                "\n expectedMessage - " + expectedErrorMessage);
    }

    @Test(priority = 1, groups = {"positiveTest", "smokeTest"})
    public void positiveloginTest() throws InterruptedException {
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys("tomsmith");

        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.tagName("button")).click();


        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not same as expected.");

        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));

        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button not displayed.");

        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedMessage = "You logged into a secure area!";

        //  Assert.assertEquals(expectedMessage,actualMessage,"Actual Message is not as per expected.");
        Assert.assertTrue(actualMessage.contains(expectedMessage), "actual message does not contain expected message.");
        logoutButton.click();

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.quit();
    }
}
