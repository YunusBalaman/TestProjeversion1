package com.yunus.hepsiburadatest;

import org.junit.Test;

import java.awt.*;

public class TestSepet extends TestCommon {
    /* Hata almamak amaciyla testi baslatmadan once sepet ve adres bolumunun bos oldugundan emin olmak icin
     TestAdresTemizleyiciyi ve TestSTemizleyiciyi calistiriniz */
    @Test
    public void loginTest() throws InterruptedException, AWTException {
        new LoginSepet(webDriver).SepetUrunAlim("yunustest1453@gmail.com", "bes55555");
    }
}

