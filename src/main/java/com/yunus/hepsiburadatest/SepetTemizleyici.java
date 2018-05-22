package com.yunus.hepsiburadatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

public class SepetTemizleyici extends LoginCommon {

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public SepetTemizleyici(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30, 150);
    }

    public void SepetiBosalt(String username, String password) throws InterruptedException, AWTException {
        new SepetTemizleyici(webDriver).login(username, password, webDriver, webDriverWait);
     // Yine adres temizleyici gibi tek amaci sepeti tamamen temizlemek olan sinifimiz

        Thread.sleep(3000);
        WebElement element1 = webDriver.findElement(By.xpath("//span[@id='cartItemCount']"));
        /* Calisma mantigi sepetteki urun sayisini login isleminin hemen ardindan kontrol edip urun varsa silmek
        ayni urun birden fazlaysa toptan silmekte ve ana ekrandaki sepet sayisi ayni urunu 1 urun olan gormesi
         sayesinde dongunun basit bir mantikla gerceklesmesini sagliyor.
         Not: Farkli kisiler yada kurumlar satiyorsa ayri olarak gozukuyor. Tabi bizim icin problem degil.
         */
        String sepettekiUrunSayisi = element1.getText();
        // zaman kaybetmemek text olarak cektigimiz degeri integer yaparak sepette urun var mi kontrolunu yapiyoruz
        System.out.println(sepettekiUrunSayisi);
        int urunSayimiz =Integer.valueOf(sepettekiUrunSayisi);

        if(urunSayimiz>0){
            webDriver.findElement(By.xpath("//span[@class='cart-copy']")).click();
            for(int i=0;i<urunSayimiz;i++) {
                Thread.sleep(4000);
                webDriver.findElement(By.xpath("//a[@class='btn-delete']")).click();

            }
            System.out.println("Sepet itinayla temizlendi.");
        }
        else{
            System.out.println("Sepette urun yok.");
        }
        Thread.sleep(3000);

    }
}
