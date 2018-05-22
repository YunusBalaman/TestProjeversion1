package com.yunus.hepsiburadatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public class AdresTemizleyici extends LoginCommon {

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public AdresTemizleyici(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30, 150);
    }

    public void AdresiTemizle(String username, String password) throws InterruptedException, AWTException {
        new AdresTemizleyici(webDriver).login(username, password, webDriver, webDriverWait);
    /* Biraz algoritma kurmam lazimdi sanirim for kullanmak paha bicilemez. Tek amaci adreslerin sayisini ogrenip hepsini
      silmek olan kodumuz adres yoksa bunu belirtip kapaniyor. Ortak xpath lere sahip olmasi addressCleaner metodumuzu
      islevsel bir duruma getiriyor.(Teslimat Adresi ve Fatura Adresi)
      */

        WebElement MyAccount = webDriver.findElement(By.id("myAccount"));
        MyAccount.click();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(MyAccount).build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='myAccount']/div[2]/div/ul/li[2]/a"))).click();
        webDriver.findElement(By.xpath("//*[@id='root']/div/div[4]/div/div[1]/div/div/div[2]/ul/li[2]/a")).click();

        List<WebElement> webElements1 = webDriver.findElements(By.xpath("//li[@class='list-item']"));
        int teslimatAdresSayiniz = new Integer(webElements1.size());

        if(teslimatAdresSayiniz>1){
            addressCleaner(teslimatAdresSayiniz);
            System.out.println("Teslimat adresleri itinayla temizlendi.");
        }
        else{
            System.out.println("Teslimat adres listeniz boş.");
        }

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id='customer-addresses']/div/ul/li[2]/a")).click();
        Thread.sleep(1000);

        List<WebElement> webElements2 = webDriver.findElements(By.xpath("//li[@class='list-item']"));
        int faturaAdresSayiniz = new Integer(webElements2.size());
        if(faturaAdresSayiniz>1){
            addressCleaner(faturaAdresSayiniz);
            System.out.println("Fatura adresleri itinayla temizlendi.");
        }
        else{
            System.out.println("Fatura adres listeniz boş.");
        }

        Thread.sleep(3000);
    }
     // -1 in sebebi adresin tutuldugu kisimda bos olan daha dogrusu yeni liste olusturulan kisimda sayilmakta
     // yukardaki if sorgumuzda da bunu dikkate aliyoruz
    private void addressCleaner(int adresSayisi) throws InterruptedException {
        for (int i = 0; i < adresSayisi - 1; i++) {
            List<WebElement> webElements2 = webDriver.findElements(By.cssSelector(".btn-delete.btn-text"));
            webElements2.get(0).click();
            Thread.sleep(1000);
            List<WebElement> webElements3 = webDriver.findElements(By.cssSelector(".btn.btn-md"));
            webElements3.get(1).click();
            Thread.sleep(3000);
        }
    }


}
