# Vaatimusmäärittely

## Sovelluksen tarkoitus

Laukaisukarttasovelluksen avulla voidaan pitää kirjaa salibandyottelun laukauksista. Sovellus siirtää sähköiseen muotoon perinteisen paperisen laukaisukartan. Sovellus 
tallentaa päättyneen ottelun laukaisukartan myöhempää tarkastelua varten.

## Käyttäjät

Sovelluksessa on vain yksi käyttäjärooli, ns. *peruskäyttäjä*. Käyttäjä on tarkoitettu joukkuekohtaiseksi. Kukin käyttäjä pääsee käsiksi vain itse tallentamiinsa
laukaisukarttoihin.

## Käyttöliittymäkuvaus

Tekstimuotoinen kuvaus sovelluksen käyttöliittymästä.

1. Kirjautumisikkuna: kenttä käyttäjätunnukselle ja salasanalle, *login*-nappi, *create new user* -nappi sekä *close*-nappi.
2. Valintaikkuna: napit *create new game* ja *view previous games*.
3. Uuden ottelun perustiedot: kenttä ottelun päiväykselle ja vastustajan nimelle, *create game* -nappi ja *back to menu* -nappi.
4. Uusi ottelu: pohjana tyhjä salibandykentän kartta, ylhäällä valintanapit eri tyyppisille laukauksille (maali, torjunta, ohilaukaus), napit *finish* ja *cancel*, hiiren klikkaus kenttäpohjaan piirtää symbolin. Myös *erase*-valintanappi, jolloin hiirenklikkaus pyyhkii kohtaan jo piirretyn laukauksen.
4. Edellisten otteluiden tarkastelu: listana edelliset ottelut, vieressä *view game* - nappi. Nappia painamalla kyseisen ottelun laukaisukartta aukeaa näkyville.

## Sovelluksen toiminnallisuus

### Ennen kirjautumista

- Sovellukseen voidaan luoda käyttäjätunnus, jonka tulee olla uniikki ja vähintään kahden merkin pituinen. Salasanan tulee myös olla vähintään kaksi merkkiä pitkä.
- Käyttäjä voi kirjautua sovellukseen. Jos käyttäjänimi tai salasana on väärin, järjestelmä ilmoittaa tästä.

### Kirjautumisen jälkeen

- Käyttäjä voi valita uuden ottelun tai vanhojen otteluiden tarkastelun.

#### Uusi ottelu

- Uusi ottelu avaa lomakkeen, johon syötetään uuden pelin taustatiedot (päivämäärä ja vastustaja). Päivämäärä syötetään muodossa yyyy-mm-dd, vastustajan nimessä saa olla 2-32 kirjainta.
- Seruaavaksi avautuu tyhjä laukaisukarttapohja, johon laukaukset merkitään. Yläpalkista valitaan symboli (*maali*, *torjunta* tai *ohilaukaus*) ja hiirellä klikkaamalla merkitään laukaus karttapohjalle. Laukauksen voi pyyhkiä valitsemalla *erase*-napin päälle.
- Ottelu päätetään yläosan napista, jolloin ottelun laukaisukartta tallentuu järjestelmään ja näkymä palaa valikkoon, johon tullaan kirjautumisen jälkeen. *Cancel*-napista pääsee takaisin päävalikkoon ilman, että ottelua tallennetaan.

#### Vanhojen otteluiden tarkastelu

- Järjestelmä listaa aiemmat ottelut, joiden viereisestä napista pääsee tarkastelemaan ottelua.
- Ottelun laukaisukarttaa voi tarkastella. Ottelun voi poistaa *delete*-napista. Tarkastelusta pääsee takaisin *back*-napilla.

## Jatkokehitysideoita

Sovellusta voidaan jatkokehittää esimerkiksi seuraavin tavoin:

- Laukaisukartta tukemaan molempien joukkueiden laukauksia (esim. laukaukset eri väreillä).
- Tuki useammille eri tyyppisille laukauksille (puolustajan blokkaus, rannelaukaus, lyöntilaukaus...).
- Laukojan pelinumero.
- Eräkohtainen tarkastelu.
- Käyttäjähallinnan parantelu (admin-käyttäjät, käyttäjien muokkaaminen/poistaminen yms.).
