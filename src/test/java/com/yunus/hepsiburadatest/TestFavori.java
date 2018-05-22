package com.yunus.hepsiburadatest;

import org.junit.Test;

import java.awt.*;

public class TestFavori extends TestCommon{
    @Test
    public void FavoriTest() throws InterruptedException, AWTException {
        new LoginFavori(webDriver).FavoriyeEkleCikar("yunustest1453@gmail.com", "bes55555");
    }
}
