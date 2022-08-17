package com.herokupp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
    @Test
    public void loginTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys("tomsmith");

        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.tagName("button")).click();


        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl,"Actual page url is not same as expected.");

        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));

        Assert.assertTrue(logoutButton.isDisplayed(),"Logout button not displayed.");

        String actualMessage = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedMessage = "You logged into a secure area!";

      //  Assert.assertEquals(expectedMessage,actualMessage,"Actual Message is not as per expected.");
        Assert.assertTrue(actualMessage.contains(expectedMessage),"actual message does not contain expected message.");
        logoutButton.click();

        Thread.sleep(1000);
        driver.quit();



    }
}
