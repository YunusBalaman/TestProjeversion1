package com.yunus.hepsiburadatest;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.util.List;

public class LoginSepet extends LoginCommon{
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public LoginSepet(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30, 150);
    }

    public void SepetUrunAlim(String username, String password) throws InterruptedException, AWTException {
        /*Loginin ortak olması sebebiyle LoginCommon adında abstract bir sinif olusturduk
        bu degerleri gondermek zorundayiz extends edildigi icin kendi sinifimizda cagirabiliyoruz
         */
        new LoginSepet(webDriver).login(username, password,webDriver,webDriverWait) ;
        /*en sinir bozucu kisim arama yapmadan once cikabilme ihtimali olan davetsiz bir popup adindan amlasilacagi gibi
        buyuk bir zaman kaybina sebep olsada programimiz bu konuda hata vermeyecek buyuk ihtimalle
         */

        Boolean popupainkiller = webDriver.findElements(By.className("insider-opt-in-notification-title")).size() > 0;
        if (popupainkiller) {
            WebElement popup = webDriver.findElement(By.cssSelector(".insider-opt-in-notification-button.insider-opt-in-disallow-button"));
            popup.click();
        }
        // 1 saniyenin altinda ufak bir zaman diliminde buton basilamaz oluyor popup cikip kapatildiysa
        Thread.sleep(1000);
        WebElement fillSearch = webDriver.findElement(By.id("productSearch"));
        fillSearch.sendKeys("asus laptop");


            WebElement searchBox = webDriver.findElement(By.id("buttonProductSearch"));
            searchBox.click();

           // Sorun olmasin diye yazdiklarimin aksine alttakini aktif ettim. Hemen alt paragrafta sebebi mevcut.

           /*ilk listemiz mevcut sayfadaki urunleri alıyor ve aynı zamanda urunun uzerine geldigimizde gozuken sepete
             ekle butonu kucuk olması sebebiyle test etmek icin daha dikkat cekici bir secenek. O yuzden secimim bu
             Butonun tek dezavataji ise beni direk sepete gitmek zorunda birakmasi.
             Liste secmemiz sebebiyle kodumuz hata vermiyor ama istedigimiz urun yeni urun eklenince degisiyor
            o yuzden size urun numarasini tekrar yollamak zorunda kalabilirim ve kalacagim*/

            /* Urune tiklayip oradan sepete gitmek icin  bu kod kismini kapatip alttaki kismi acabilirsiniz   */
/*
            List<WebElement> miniBasketElements = webDriver.findElements(By.cssSelector(".add-to-basket.button.small"));

            //ikinci listemiz fiyat listemiz yine bu da muhtemelen degisecek

            List<WebElement> priceElements = webDriver.findElements(By.cssSelector(".price.product-price"));

            // urunumuzun fiyatini listeden cekiyoruz

            String fiyat1 = priceElements.get(2).getText();
            System.out.println(fiyat1);

            //Olasi bir hataya karsi 2 saniye bekletiyorum. Sanirim hata almisim fazla hizli olmasi sebebiyle

            Thread.sleep(2000);

            //Fiyati aldik ve o urunun listedeki sepete ekle mini butonunu tikliyoruz
            miniBasketElements.get(4).click();
            webDriver.findElement(By.cssSelector("#cartItemCount")).click();
*/


            /* ikisininde aktif olmadigindan emin olunuz  */

            /*   Alttaki kodu kapatip ustteki kodu aktif edip deneyebilirsiniz*/

            List<WebElement> priceElements = webDriver.findElements(By.cssSelector(".price.product-price"));
            String fiyat1 = priceElements.get(2).getText();
            System.out.println(fiyat1);
            Thread.sleep(1000);
            priceElements.get(2).click();
            Thread.sleep(4000);
            webDriver.findElement(By.id("addToCart")).click();


            /* Eger hata aliyorsaniz ustteki kismi kontrol ediniz */

            String fiyat2 = webDriver.findElement(By.xpath("//*[@id='short-summary']/div[1]/div[1]/div")).getText();
            System.out.println(fiyat2);

            /*String fiyat2 = webDriver.findElement(By.xpath("//div[@class='price']")).getText();
            System.out.println(fiyat2);*/
            if (fiyat1.equals(fiyat2)) {
                System.out.println("Eklenen ürünle sepetteki ürünün fiyati uyuşuyor.");
            } else  {
                System.out.println("Eklenen ürünle favorideki ürünün fiyati uyuşmuyor.");
            }
            //Fiyat kiyaslamamizi yaptik fiyat uyumsuz cikarsa kapanmasini istemedim ustte belirttigim sebeple
            //Sirada butına basip adres bilgilerimizi giriyoruz
            // Testimizi Turkiyeyle sinirli tutup asagidaki metotu pas geciyoruz.

            Thread.sleep(3000);
            webDriver.findElement(By.cssSelector(".btn.btn-primary.full")).click();
            webDriver.findElement(By.id("first-name")).sendKeys("Yunus");
            webDriver.findElement(By.id("last-name")).sendKeys("YtuMathEngineer");
            webDriver.findElement(By.xpath("//button[@data-id='country']")).click();
            webDriver.findElement(By.xpath("//li[@rel='152']")).click();

            /* Gorsellik acisindan guzel durmasi bakimindan altta indiriyoruz ekrani ve iste uzun zamanimi alan
            kisimlardan en zevklisi daha kolay yolu oldugunu sonradan ogrensemde bu sekilde cozume ulastim.
            Ulke, sehir, semt ve mahallenin birbiriyle ilişkili olmasi sebebiyle adresbuttonkismi metodunu olusturdum
            Hepsiburada sitesinin algoritma mantigina gore  duzenledim. Aslinda hazirdaki listelerden site
            kodlama mantriğina gore liste cekilip bizim kullanacagimiz li rel kismindaki degerlere donusuyor
            Burdaki problem ise ornegin sehir olarak rel=4 u sectik Ayni zamanda ulke ve diger butonlar icinde bulunuyor.
            Bu problemi buton secerek cozuyoruz. Butonlar arasi gecislerdeki gecikmeyi hesaba katip metodumuzda 1 saniye
            gecikme eklendi. butonsec sehir icin 1 semt icin 2 mahalle icin 3
             */

            JavascriptExecutor jse1 = (JavascriptExecutor) webDriver;
            jse1.executeScript("window.scrollBy(0,500)", "");

            adresButonKismi("city", "41", 1);
            adresButonKismi("town", "7", 2);
            adresButonKismi("district", "4", 3);

            webDriver.findElement(By.id("address")).sendKeys("Fevzi cakmak mah");

            webDriver.findElement(By.id("address-name")).sendKeys("ilkAdres");

            webDriver.findElement(By.id("phone")).sendKeys("5555555555");

            Thread.sleep(3000);

            webDriver.findElement(By.xpath("//div[@class='proceed-container']")).click();
            // Son kisim odeme kisminda ise son kullanim tarihine liste yoluyla ulasip sonkullanim tarihi adli
        // metodumuzla tarihimizi sectik tabi butonu unutmadan

            webDriver.findElement(By.id("card-no")).sendKeys("1453145314531453");
            webDriver.findElement(By.id("holder-Name")).sendKeys("YunusB");
            sonKullanimTarihi("8",0);
            sonKullanimTarihi("3",1);

            webDriver.findElement(By.id("cvc")).sendKeys("1234");

        Thread.sleep(6000);
        }

        private WebElement findElement (By by){
            WebElement webElement = webDriver.findElement(by);
            new Actions(webDriver).moveToElement(webElement).build().perform();
            scroll(webElement);
            return webElement;
        }

        public void scroll (WebElement webElement){
            ((JavascriptExecutor) webDriver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
        }


        private void adresButonKismi (String xpathyolunusec, String listeKodu,int butonSec) throws InterruptedException
        {
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("//button[@data-id='" + xpathyolunusec + "']")).click();
            List<WebElement> webElements = webDriver.findElements(By.xpath("//li[@rel='" + listeKodu + "']"));
            webElements.get(butonSec).click();
        }
    private void sonKullanimTarihi (String tarihSec, int butonSec2) throws InterruptedException
    {
        Thread.sleep(1000);
        List<WebElement> webElements1 = webDriver.findElements(By.xpath("//button[@data-toggle='dropdown']"));
        webElements1.get(butonSec2).click();
        List<WebElement> webElements2 = webDriver.findElements(By.xpath("//li[@rel='"+tarihSec+"']"));
        webElements2.get(butonSec2).click();
    }

}

