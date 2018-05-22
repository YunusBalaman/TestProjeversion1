package com.yunus.hepsiburadatest;

import org.junit.Test;

import java.awt.*;

public class TestSTemizleyici extends TestCommon{

    @Test
    public void SepetTemizTest() throws InterruptedException, AWTException {
        new SepetTemizleyici(webDriver).SepetiBosalt("yunustest1453@gmail.com", "bes55555");
    }
}

