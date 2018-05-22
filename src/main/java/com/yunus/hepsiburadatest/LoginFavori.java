package com.yunus.hepsiburadatest;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public class LoginFavori extends LoginCommon{
     WebDriver webDriver;
     WebDriverWait webDriverWait;

    public LoginFavori(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30, 150);
    }

    public void FavoriyeEkleCikar(String username, String password) throws InterruptedException, AWTException {
        new LoginFavori(webDriver).login(username, password,webDriver,webDriverWait);
        /* adrestemizleyici ve sepettemizleyiciyi class a cagirmamamin sebebi sureci fazla uzatmak istememem
         Ayrica ekstradan arada cikan popup ve test sureci sirasinda bilgisayar intermet gibi etkenler sebebiyle
         olusan zamansal dengesizligi kontrolde tutmak amaciyla Thread.sleep eklemem testin hizini oldukca yavaslatti
        ama hatalarida kisitladi. */

        Boolean popupainkiller = webDriver.findElements(By.className("insider-opt-in-notification-title")).size() > 0;
        if (popupainkiller) {
            WebElement popup = webDriver.findElement(By.cssSelector(".insider-opt-in-notification-button.insider-opt-in-disallow-button"));
            popup.click();
        }
        Thread.sleep(1000);
        WebElement fillSearch = webDriver.findElement(By.id("productSearch"));
        fillSearch.sendKeys("apple iphone");

        WebElement searchBox = webDriver.findElement(By.id("buttonProductSearch"));
        searchBox.click();
        // Bu sefer apple iphone aramasi yaptik yine loginsepette oldugu gibi kodlari guncellemem gerekebilir
        List<WebElement> webElements1 = webDriver.findElements(By.xpath("//h3[@class='product-title title']"));
        // Favorideki karsilastirmaya urun adini kontrol ederek cozecegiz butun kodlama favoride tek urun olmasi uzerine
        // kurulu ama ihtiyac halinde adrestemizleyici ve sepettemizleyici gibi bu sorunu cozecek bir class olusturabilirim
        String string1=webElements1.get(0).getText();
        System.out.println(string1);
        webElements1.get(0).click();
        // Alta inip favori butonun tikliyoruz 1 saniyelik beklemeler bazen olusan favoriye ekleyip favori popup inin
        // gozukmemesi sorununu engellemek
        JavascriptExecutor jse1 = (JavascriptExecutor)webDriver;
        jse1.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//li[@class='favorite']")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id='notification']/div/a[1]")).click();
        // Artik favorilerdeyiz buradanda urun ismini cekip kontrolumuzu yapiyoruz
        String string2=webDriver.findElement(By.id("ctl00_ContentPlaceHolder1_rptShoppingList_ctl01_hplProductName")).getText();
        System.out.println(string2);
        if(string1.equals(string2)){
            System.out.println("Eklenen ürünle favorideki ürün uyuşuyor.");
        }
        else{
            System.out.println("Eklenen ürünle favorideki ürün uyuşmuyor.");
        }
        // Urunumuzun yanina tik koyduk ve sil butonuna basiyoruz
        webDriver.findElement(By.id("ctl00_ContentPlaceHolder1_rptShoppingList_ctl01_chkSelect")).click();
        List<WebElement> webElements2 = webDriver.findElements(By.xpath("//span[@class='sprite']"));
        webElements2.get(0).click();
        // Kapatmak icin farkli bir koda ihtiyac olan son kisimdayiz. internetten arastirip buldugum bu kodda alert
        // sorununu bertaraf etti
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert().accept();

        Thread.sleep(5000);

    }
    //scroll butonlara basarken daha stabil sonuc almamizi sagliyor galiba :)
    private WebElement findElement(By by) {
        WebElement webElement = webDriver.findElement(by);
        new Actions(webDriver).moveToElement(webElement).build().perform();
        scroll(webElement);
        return webElement;
    }

    public void scroll(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
    }
}
