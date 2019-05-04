# Laukaisukarttasovellus

Sovellus siirtää perinteisen salibandyn laukaisukartan sähköiseen muotoon. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, ohjelma tallentaa käyttäjän luomat laukaisukartat myöhempää tarkastelua varten.

Sovellus on Helsingin yliopiston kurssin Ohjelmistotekniikka (kevät 2019) harjoitustyö. Tämä repositorio sisältää tästä syystä myös kurssin harjoitustehtävien vastauksia.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/tyoaikakirjanpito.md)

## Julkaisut

[Loppupalautus](https://github.com/Deemusc/ot-harjoitustyo/releases/tag/Loppupalautus)

[Viikko 6](https://github.com/Deemusc/ot-harjoitustyo/releases/tag/viikko6)

[Viikko 5](https://github.com/Deemusc/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla:

```mvn test```

Testikattavuusraportti testaamisen yhteydessä luodaan komennolla:

```mvn test jacoco:report```

Testikattavuusraporttia tarkastellaan selaimella avaamalla tiedosto */target/site/jacoco/index.html*.

### Suoritettavan jarin generointi

Suoritettava jar-tiedosto luodaan komennolla:

```mvn package```

Kansioon target generoidaan tällöin suoritettava jar-tiedosto *ShotChart-1.0-SNAPSHOT.jar.*.

**Huom. sovellus vaatii toimiakseen config.properties-tiedoston samaan kansioon, missä sovellus sijaitsee.**

### JavaDoc

Sovelluksen JavaDoc generoidaan komennolla:

```mvn javadoc:javadoc```

JavaDocia voi tarkastella selaimessa avaamalla tiedoston *target/site/apidocs/index.html*.

### Checkstyle

[Checkstyle-tiedostossa](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla:

```mvn jxr:jxr checkstyle:checkstyle```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto */target/site/checkstyle.html*
