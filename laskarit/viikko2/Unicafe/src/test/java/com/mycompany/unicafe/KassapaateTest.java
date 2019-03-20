package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void rahamaaraAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void myydytLounaatAlussaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKateisellaToimii() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100480, kassa.kassassaRahaa());
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKateisellaIlmanRiittavaaRahasummaa() {
        kassa.syoEdullisesti(150);
        assertEquals(150, kassa.syoEdullisesti(150));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKateisellaToimii() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(600, kassa.syoMaukkaasti(1000));
        assertEquals(100800, kassa.kassassaRahaa());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKateisellaIlmanRiittavaaRahasummaa() {
        kassa.syoMaukkaasti(150);
        assertEquals(150, kassa.syoMaukkaasti(150));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKortillaToimii() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaIlmanRiittavaaRahasummaa() {
        Maksukortti k = new Maksukortti(100);
        assertEquals(false, kassa.syoEdullisesti(k));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100, k.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaToimii() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaIlmanRiittavaaRahasummaa() {
        Maksukortti k = new Maksukortti(100);
        assertEquals(false, kassa.syoMaukkaasti(k));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100, k.saldo());
    }

    @Test
    public void ladattaessaKorttiaSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }

    @Test
    public void ladattaessaKorttiaKassanRahamaaraMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 750);
        assertEquals(100750, kassa.kassassaRahaa());
    }

    @Test
    public void ladattaessaNegatiivinenSummaSaldotEiMuutu() {
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }

}
