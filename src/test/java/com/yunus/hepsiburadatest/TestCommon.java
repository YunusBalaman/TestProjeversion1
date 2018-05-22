package com.yunus.hepsiburadatest;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;


public abstract class TestCommon {
        public WebDriver webDriver;

        @Before
        public void beforeCreateWebDriver() {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\yunus\\Downloads\\TestProje\\driver\\chromedriver.exe");
            webDriver = new ChromeDriver();

            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            webDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }


        @After
        public void afterQuitWebDriver() {
            webDriver.quit();
        }

    }

