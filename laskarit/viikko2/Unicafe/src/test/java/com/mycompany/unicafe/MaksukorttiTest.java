package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
        kortti.lataaRahaa(3);
        assertEquals(18, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikeinKunRahaaOn() {
        assertEquals(true, kortti.otaRahaa(3));
        assertEquals(7, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuKunRahaaEiOle() {
        assertEquals(false, kortti.otaRahaa(15));
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void tulostusToimiiOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
}
