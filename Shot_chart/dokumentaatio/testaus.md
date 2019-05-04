# Testauksen dokumentointi

Sovelluksen toimintaa on testattu sekä automaattisilla testeillä että manuaalisesti. Pääasiassa manuaalisilla testeillä on huolehdittu järjestelmätestaamisesta ja 
automaattisilla yksikkö- ja integraatiotestaamisesta. Automaattiset testit on suoritettu JUnitilla. Käyttöliittymää ei ole testattu automaattisesti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikan testaus

Automaattisen testaamisen tärkein testiluokka on integraatiotestejä suorittava [ShotChartAppTest](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/domain/ShotChartAppTest.java),
joka testaa sovelluksen ydintoimintoja toteuttavan [ShotChartAppin](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/main/java/shotchart/domain/ShotChartApp.java)
toimintaa.

Integraatiotesteissä on hyödynnetty tiedon pysyväistallentamiseen keskusmuistitoteutusta DAO-rajapinnat toteuttavien luokkien [FakeShotChartDaon](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/domain/FakeShotChartDao.java)
ja [FakeUserDaon](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/domain/FakeUserDao.java) kautta.

Yksikkötestejä on suoritettu sovelluksen eri luokkia vastaavasti testanneiden luokkien [ShotTest](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/domain/ShotTest.java),
[ShotChartTest](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/domain/ShotChartTest.java) ja [UserTest](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/domain/UserTest.java)
avulla.

### DAO-luokkien testaus

Tiedon tallentamisesta huolehtivien DAO-luokkien toimintaa on testattu testiluokissa [FileShotChartDaoTest](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/dao/FileShotChartDaoTest.java)
ja [FileUserDaoTest](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/src/test/java/shotchart/dao/FileUserDaoTest.java). Testeissä on hyödynnetty JUnitin
*TemporaryFolder*:a, jonka avulla on voitu luoda tilapäiset testitiedostot. 

### Testikattavuus

Sovelluksen testauksessa saavutettiin rivikattavuus 94 % ja haarautumakattavuus 88 %. Käyttöliittymä ei ole mukana testikattavuudessa.

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/testikattavuus.png)

Pääosa testauksen puutteista liittyy poikkeusten käsittelyn testaamiseen.

## Järjestelmätestaus

Sovelluksen järjestelmätestaaminen on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellusta on testattu Linux-ympäristössä siten, että vaadittu *config.properties* -tiedosto on ollut käynnistyshakemistossa, kuten [käyttöohjeessa](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kayttoohje.md)
esitetään. Sovellusta ei ole testattu muilla käyttöjärjestelmillä.

### Toiminnallisuuksien läpikäynti

[Vaatimusmäärittelyssä](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/vaatimusmaarittely.md) ja/tai käyttöohjeessa listatut toiminnallisuudet
on testattu järjeslmätestauksessa. Syötekenttiä on testattu myös virheellisillä syötteillä.

