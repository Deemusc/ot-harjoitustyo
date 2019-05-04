# Arkkitehtuurikuvaus

## Sovelluksen rakenne

Sovellus rakentuu kolmesta tasosta. Ylimpänä on käyttöliittymän sisältävä pakkaus *shotchart.ui*, sitä seuraa sovelluslogiikan sisältävä pakkaus *shotchart.domain* ja
alimpana on pakkaus *shotchart.dao*, joka sisältää tietojen tallennuksesta vastaavan koodin.

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/rakenne.png)

## Käyttöliittymä

Sovelluksen käyttöliittymä on toteuttu JavaFX:llä luokassa shotchart.ui.UserInterface. Käyttöliittymässä on seitsemän eri näkymää:

- sisäänkirjautuminen
- uuden käyttäjän luominen
- päävalikko
- uuden ottelun tietojen syöttäminen
- uuden ottelun laukausten piirtäminen
- vanhojen otteluiden listaaminen
- vanhan ottelun laukasten tarkastelu

Käyttöliittymän näkymät on toteutettu omina *Scene*-olioinaan. *Stagessa* näytetään aina yksi näistä näkymistä. Jokaisen näkymän luonnista vastaa oma metodi.

Käyttöliittymä on pääasiassa erotettu sovelluslogikaasta, josta vastaa luokka shotchart.domain.ShotChartApp.

## Sovelluslogiikka

Sovelluksen datamalli muodostuu luokista *User*, *ShotChart* ja *Shot*. Käyttäjällä on useita laukaisukarttoja, joilla puolestaan on useita laukauksia.

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/datamalli.png)

Sovelluksen toiminnasta vastaa luokka *ShotChartApp*. Käyttäjienhallinasta vastaavat seuraavat metodit:

- login(String username, String password)
- getLoggedUser()
- logout()
- createUser(String username, String password)

Otteluidenhallinnasta vastaavat metodit:

- createNewGame(String date, String opponent)
- deleteGame() ja deleteGameById(int id)
- saveGame()

Vanhojen otteluiden tarkastelusta vastaavat metodit:

- getShotCharts()
- getShots(int id)

Laukausten piirtämisestä vastaavat metodit:

- addShot(int x, int y, String type)
- deleteShot(int x, int y)

Pakkauksessa *shotchart.dao* sijaitsevat luokat *FileShotChartDao* ja *FileUserDao* toteuttavat rajapinnat *ShotChartDao* ja *UserDao*. Nämä luokat mahdollistavat
*ShotChartAppin* käsiksi pääsyn tallennettuihin käyttäjä- ja laukaisukarttatietoihin.

Sovelluksen osien suhteet on kuvattu pakkauskaaviossa:

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/pakkauskaavio.png)

## Tietojen pysyväistallennus

Tietojen tallennuksesta tiedostoihin vastaavat siis luokat *FileShotChartDao* ja *FileUserDao*, jotka sijaitsevat pakkauksessa *shotchart.dao*. Luokat noudattavat
**DAO**-mallia (Data Access Object) ja ovat ns. eristetty rajapintojen *ShotChartDao* ja *UserDao* taakse.

### Tallennustiedostot

Sovelluksen tiedot (käyttäjät ja laukaisukartat) tallennetaan omiin tiedostoihinsa, joiden nimet määritellään tiedostossa *config.properties*.

Käyttäjät tallennetaan muodossa, jossa yhdellä rivillä on yhden käyttäjän tiedot: käyttäjänimi ja salasana, jotka on erotettu puolipisteellä.

```botnia;antti13```

Laukaisukartat tallennetaan samantyyppisesti. Yhdellä rivillä on yhden laukaisukartan tiedot: tunnus, päiväys, vastustaja, käyttäjä ja laukausten tiedot.

```1;2019-02-03;pallokerho;botnia;G;133;245;M;341;200;B;190;259```

Laukaukset on siis tallennettu muodossa: laukauksen tyyppi; x-koordinaatti; y-koordinaatti.

## Päätoiminnallisuuksien kuvaus

#### Uuden käyttäjän luonti

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/sekvenssikaavio_kayttajan_luonti.png)

Kun uden käyttäjän luomisnäkymässä klikataan painiketta *Create*, kutsutaan sovelluslogiikan metodia *createUser*, joka tarkistaa *userDao*:n avulla, onko käyttäjätunnus
jo varattu. Mikäli ei, luo metodi uuden käyttäjän, *User*-olion, ja tallentaa sen *userDao*:n metodin *create* avulla.

#### Sisäänkirjautuminen

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/sekvenssikaavio_kirjautuminen.png)

Kun sisäänkirjautumisvalikossa syötetään käyttäjätunnus ja salasana, ja klikataan *login* -nappia, kutsutaan sovelluslogiikan metodia *login*, joka tarkistaa *userDao*:n
avulla, ovatko käyttäjätunnus ja salasana oikein. Mikäli ovat, palautetaan käyttäjä, merkataan se sisäänkirjautuneeksi ja palautetaan *true* käyttöliittymään. Tällöin
käyttöliittymä tietää vaihtaa näkymän päävalikkoon.

#### Uuden ottelun luonti

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/sekvenssikaavio_ottelun_luonti.png)

Kun päävalikossa klikataan *create new game* -nappia, käyttöliittymä vaihtaa näkymäksi uuden ottelun tietojen syöttämiseen tarkoitetun ikkunan. Kun sinne syötetään uuden
ottelun tiedot ja klikataan *confirm* -nappia, kutsutaan sovelluslogiikan metodia *createNewGame*, joka tallentaa uuden ottelun *ShotChartDao*:n avulla. Jos ottelun luonti
onnistuu, palautetaan *true* käyttöliittymään, joka tietää tällöin vaihtaa näkymäksi uuden ottelun ikkunan.

#### Laukauksen piirtäminen ja ottelun päättäminen

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/sekvenssikaavio_laukauksen_piirto_ja_pelin_tallennus.png)

Kun uuden ottelun ikkunassa valitaan (esim.) piirtotilaksi maali, *Goal*, ja klikataan kenttään, kutsutaan sovelluslogiikan metodia *addShot*, joka kutsuu laukaisukarttaa
kuvaavan luokan metodia *addShot*. Samalla käyttöliittymä lisää maalin piirrettävien maalien taulukkoon ja päivittää kentän piirrosta vastaavaa metodia. Kun ottelu
päätetään klikkaamalla *finish* -nappia, kutsutaan sovelluslogiikan metodia *saveGame*, joka tallentaa laukaisukartan *ShotChartDao*:n avulla. Jos tallennus onnistuu,
palautetaan *true*, jolloin käyttöliittymä tietää vaihtaa näkymän päävalikkoon.

#### Muut toiminnallisuudet

Sovelluksen muut toiminnallisuudet noudattelevat samaa periaatetta. Käyttöliittymästä kutsutaan sovelluslogiikan metodeja, jotka kutsuvat laukaisukarttaa tai laukausta
kuvaavien luokkien metodeja ja/tai *daoja*, jolloin laukaisukartan/karttojen tilannetta päivitetään. Käyttöliittymä muuttaa näkymää tilanteen mukaan saadessaan kontrollin
takaisin.

## Sovelluksen rakenteen puutteet, rajoitteet ja heikkoudet

- Käyttöliittymä on toteutettu kokonaisuudessaan yhdessä luokassa, joskin sentään usealla eri metodilla. Nämä metodit voitaisiinkin eriyttää omiksi luokikseen.

- Laukausten piirtämisessä hyödynnetään käyttöliittymässä määriteltävää taulukkoa. Olisi siistimpää, jos dynaamisen piirtämisen saisi toimimaan sovelluslogiikan kautta.

- Laukaisukarttojen muokkaamisessa yms. laukaisukarttaa haetaan hieman kömpelösti listalta id:n perusteella. Tähän olisi varmasti kehitettävissä näppärämpi tapa.

- *Daojen* toteutuksessa on toisteista koodia tiedoston lukemiseen ja kirjoittamiseen liittyen. Se olisi hyvä eriyttää omaan luokkaansa.
