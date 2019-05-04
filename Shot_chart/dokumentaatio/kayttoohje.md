# Käyttöohje

Lataa ohjelma [ShotChart](https://github.com/Deemusc/ot-harjoitustyo/releases/tag/Loppupalautus). Lataa myös konfiguraatiotiedosto *config.properties*.

### Konfigurointi

Ohjelma vaatii toimiakseen konfiguraatiotiedoston *config.properties*, jossa määritellään käyttäjien ja laukaisukarttojen tallentamiseen käytettävät tiedostot.

### Ohjelman käynnistäminen

Käynnistä ohjelma kommennolla

```java -jar ShotChart.jar```

### Uuden käyttäjän luominen

Kirjautumisvalikosta päästään luomaan uusi käyttäjä napilla *Create new user*. Uudelle käyttäjälle annetaan käyttäjänimi ja salasana, ja käyttäjä luodaan napilla *Create*.
Mikäli käyttäjänimi on jo käytössä toisella käyttäjällä, ohjelma ilmoittaa tästä. Valitse tällöin toinen käyttäjänimi. Onnistuneesta käyttäjän luonnista ilmoitataan myös.
Palaa kirjautumisvalikkoon napilla *Back to login screen*.

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/ohje_uusi_kayttaja.png)

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/ohje_kayttaja_luotu.png)

### Sisäänkirjautuminen

Syötä käyttäjänimi sekä salasana ja paina *Login*-nappia. Jos käyttäjätunnus tai salasana on väärin, ohjelma ilmoittaa tästä.

### Uuden ottelun luominen

Valitse *New game*. Syötä ottelulle perustiedot, eli päivämäärä ja vastustajajoukkueen nimi. Peli luodaan napista *Create game*. Ottelulle syötetään laukauksia valitsemalla
laukauksen tyyppi yläpalkin *radiobutton*-valikosta. Laukaus voi mennä maaliin (*Goal*), tulla torjutuksi (*Block*) tai mennä ohi (*Missed shot*). Kun oikea laukaustyyppi on
valittu, klikkaa kentällä kohtaa, josta laukaus lähti. Tällöin ohjelma merkkaa laukauksen kirjaimella siihen kohtaan. Laukauksen voi myös kumittaa valitsemalla *Erase* ja
klikkaamalla piirrettyä laukausta.

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/ohje_laukausten_piirto.png)

Kun ottelun kaikki laukaukset on merkitty, päätä peli napista *Finish game*. Jos haluat peruuttaa ottelun tallentamisen, valitse *Cancel*, jolloin sovellus palaa päävalikkoon.

### Vanhojen otteluiden selaaminen ja tarkastelu

Napista *View previous games* päästään tarkastelemaan tallennettuja otteluita. Ottelut on listattu tallentamisjärjestyksessään. Yksittäistä ottelua päästään tarkastelemaan
painamalla sen kohdalla *Show shotchart*. Tällöin ohjelma näyttää kyseisen ottelun laukaisukartan, eli otteluun merkatut laukaukset.

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/ohje_vanhojen_tarkastelu.png)

Yläpalkista pääsee takaisin listaukseen tai voi poistaa kyseisen ottelun napeista *Back to list* ja *Delete game*.

