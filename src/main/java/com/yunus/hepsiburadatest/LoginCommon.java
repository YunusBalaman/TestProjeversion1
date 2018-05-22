package com.yunus.hepsiburadatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public abstract class LoginCommon
{
// Ortak login butonumuz ona soylenecek bir soz yok bizi korkunc bir tekrardan kurtarmis olmasi disinda
        public void login(String username, String password, WebDriver webDriver, WebDriverWait webDriverWait) throws InterruptedException, AWTException {
            webDriver.get("https://www.hepsiburada.com/");
            //ilk popup biraz daha uysal
            webDriver.findElement(By.cssSelector(".insider-opt-in-notification-button.insider-opt-in-disallow-button")).click();
            WebElement mainLoginButton = webDriver.findElement(By.id("myAccount"));
            mainLoginButton.click();
            Actions actions = new Actions(webDriver);
            actions.moveToElement(mainLoginButton).build().perform();
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
            webDriver.findElement(By.id("email")).sendKeys(username);

            webDriver.findElement(By.id("password")).sendKeys(password);

            webDriver.findElement(By.cssSelector(".btn.full.btn-login-submit")).click();
}
   }
