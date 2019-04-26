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

Tietojen tallennuksesta tiedostoihin vastaavat siis luokat *FileShotChartDao* ja *FileUserDao*, jotka sijaitsevat pakkauksessa *shotchart.dao*. Luokat noudattavat **DAO**-
mallia (Data Access Object) ja ovat ns. eristetty rajapintojen *ShotChartDao* ja *UserDao* taakse.

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

## Sovelluksen rakenteen puutteet, rajoitteet ja heikkoudet

Käyttöliittymä on toteutettu kokonaisuudessaan yhdessä luokassa, joskin sentään usealla eri metodilla. Nämä metodit voitaisiinkin eriyttää omiksi luokikseen.
