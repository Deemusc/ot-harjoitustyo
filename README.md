# Laukaisukarttasovellus

Sovellus siirtää perinteisen salibandyn laukaisukartan sähköiseen muotoon. Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, ohjelma tallentaa käyttäjän luomat laukaisukartat myöhempää tarkastelua varten.

Sovellus on Helsingin yliopiston kurssin Ohjelmistotekniikka (kevät 2019) harjoitustyö. Tämä repositorio sisältää tästä syystä myös kurssin harjoitustehtävien vastauksia.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Deemusc/ot-harjoitustyo/blob/master/tyoaikakirjanpito.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

'mvn test'

Testikattavuusraportti testaamisen yhteydessä luodaan komennolla

'mvn test jacoco:report'

Testikattavuusraporttia voi tarkastella avaamalla selaimella tiedosto */target/site/jacoco/index.html*

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla

'mvn jxr:jxr checkstyle:checkstyle'

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto */target/site/checkstyle.html*
