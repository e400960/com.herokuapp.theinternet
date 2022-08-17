package com.herokupp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
    /*@Test(priority = 1, groups = {"negativeTest","smokeTest"})
    public void wrongUsername() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys("tomsmith1");

        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.tagName("button")).click();


        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedMessage = "Your username is invalid!";

        //  Assert.assertEquals(expectedMessage,actualMessage,"Actual Message is not as per expected.");
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message." +
                "\n Actual Message - "+actualMessage+
        "\n expectedMessage - "+expectedMessage);

        Thread.sleep(1000);
        driver.quit();

    }*/
    @Parameters({"username","password","expectedMessage"})
    @Test(priority = 2,groups = {"negativeTest"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys(username);

        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.tagName("button")).click();


        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();


        //  Assert.assertEquals(expectedMessage,actualMessage,"Actual Message is not as per expected.");
        Assert.assertTrue(actualMessage.contains(expectedErrorMessage), "Actual message does not contain expected message." +
                "\n Actual Message - "+actualMessage+
                "\n expectedMessage - "+expectedErrorMessage);

        Thread.sleep(1000);
        driver.quit();

    }
}
