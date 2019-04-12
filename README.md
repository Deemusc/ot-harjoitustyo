# Laukaisukarttasovellus

Sovellus siirtää perinteisen salibandyn laukaisukartan sähköiseen muotoon. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, ohjelma tallentaa käyttäjän luomat laukaisukartat myöhempää tarkastelua varten.

Sovellus on Helsingin yliopiston kurssin Ohjelmistotekniikka (kevät 2019) harjoitustyö. Tämä repositorio sisältää tästä syystä myös kurssin harjoitustehtävien vastauksia.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/tyoaikakirjanpito.md)

## Julkaisut

[Viikko 5](https://github.com/Deemusc/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```mvn test```

Testikattavuusraportti testaamisen yhteydessä luodaan komennolla

```mvn test jacoco:report```

Testikattavuusraporttia voi tarkastella avaamalla selaimella tiedosto */target/site/jacoco/index.html*

### Suoritettavan jarin generointi

Komento

```mvn package```

generoi hakemistoon target suoritettavan jar-tiedoston *ShotChart-1.0-SNAPSHOT.jar.*

**Huom. sovellus vaatii toimiakseen config.properties-tiedoston samaan kansioon, missä sovellus sijaitsee.**

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla

```mvn jxr:jxr checkstyle:checkstyle```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto */target/site/checkstyle.html*
