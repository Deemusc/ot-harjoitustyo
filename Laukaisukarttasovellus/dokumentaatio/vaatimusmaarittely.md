# Vaatimusmäärittely

## Sovelluksen tarkoitus

Laukaisukarttasovelluksen avulla voidaan pitää kirjaa salibandyottelun laukauksista. Sovellus siirtää sähköiseen muotoon perinteisen paperisen laukaisukartan. Sovellus tallentaa päättyneen ottelun laukaisukartan myöhempää tarkastelua varten.

## Käyttäjät

Sovelluksessa on ainakin alkuvaiheessa vain yksi käyttäjärooli, *peruskäyttäjä*. Kukin käyttäjä pääsee käsiksi vain itse tallentamiinsa laukaisukarttoihin. Jatkossa sovellukseen saatetaan lisätä *pääkäyttäjä*, joka voi tarkastella kaikkia laukaisukarttoja.

## Käyttöliittymäluonnos

Tekstimuotoinen kuvaus käyttöliittymäluonnoksesta.

1. Kirjautumisikkuna: kenttä käyttäjätunnukselle, *login*-nappi, *new user* -nappi.
2. Valintaikkuna: napit *new game* ja *view previous games*.
3. Uusi ottelu: pohjana tyhjä salibandykentän kartta, ylhäällä kenttä ottelun tiedoille ja *save*-nappi, vasemmalla symbolit eri tyyppisille laukauksille (esim. rasti, ympyrä ja neliö), alhaalla nappi *save game*. Symboleista aina yksi kerrallaan valittuna, hiiren klikkaus kenttäpohjaan piirtää symbolin.
4. Edellisten otteluiden tarkastelu: listana nappeina edelliset ottelut, nappia painamalla kyseisen ottelun laukaisukartta aukeaa näkyville.

## Sovelluksen toiminnallisuus

### Ennen kirjautumista

- Sovellukseen voidaan luoda käyttäjätunnus, jonka tulee olla uniikki.
- Käyttäjä voi kirjautua sovellukseen. Salasanaa ei vaadita ainakaan alkuvaiheessa. Jos käyttäjää ei ole luotu, järjestelmä ilmoittaa tästä.

### Kirjautumisen jälkeen

- Käyttäjä voi valita uuden ottelun tai vanhojen otteluiden tarkastelun.

#### Uusi ottelu

- Uusi ottelu avaa tyhjän laukaisukarttapohjan, johon laukaukset merkitään. Sivupalkista valitaan symboli (alkuvaiheessa käytössä *maali*, *torjunta* ja *ohilaukaus*) ja hiirellä klikkaamalla merkitään laukaus karttapohjalle. Ottelun tiedot merkitään yläosan kenttään.
- Ottelu päätetään alaosan napista, jolloin ottelun laukaisukartta tallentuu järjestelmään ja näkymä palaa valikkoon, johon tullaan kirjautumisen jälkeen.

#### Vanhojen otteluiden tarkastelu

- Järjestelmä listaa aiemmat ottelut, joita voi klikata.
- Klikkaamalla ottelua aukeaa sen laukaisukartta, jota voi tarkastella. Tarkastelusta pääsee takaisin *takaisin*-napilla.

## Jatkokehitysideoita

Sovellusta voidaan jatkokehittää esimerkiksi seuraavin tavoin:

- Laukaisukartta tukemaan molempien joukkueiden laukauksia.
- Tuki useammille eri tyyppisille laukauksille (puolustajan blokkaus, rannelaukaus, lyöntilaukaus...).
- Laukojan pelinumero.
- Eräkohtainen tarkastelu.
- Käyttäjähallinnan parantelu (salasana, käyttäjien muokkaaminen/poistaminen tms.).
