package org.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class homework {
    WebDriver driver;
    //не ми се получи нещо с жокера от домашното и затова изнесох wait отгоре, надявам се не е проблем по принцип
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("http://auto.pragmatic.bg/manage");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulLogin() {
        WebElement usernameField = driver.findElement(By.id("input-username"));
        WebElement passwordField = driver.findElement(By.id("input-password"));
        WebElement loginButton = driver.findElement(By.cssSelector(".btn"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("parola123!");
        loginButton.click();
        //чакам да зареди контейнера на logout button-a, за да си подсигуря, че ще се вижда
        WebElement dashboardElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                        ".nav")));

        WebElement logoutButton = driver.findElement(By.xpath(
                "//span[@class='d-none d-md-inline']"));

        Assert.assertEquals(logoutButton.getText(),"Logout");
    }
}
