package com.yunus.hepsiburadatest;

import org.junit.Test;

import java.awt.*;

public class TestAdresTemizleyici extends TestCommon{

    @Test
    public void AdresTemizTest() throws InterruptedException, AWTException {
        new AdresTemizleyici(webDriver).AdresiTemizle("yunustest1453@gmail.com", "bes55555");
    }
}
