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



## Pakkauskaavio

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/pakkauskaavio.png)

## Sekvenssikaavio - uuden käyttäjän luonti

![](https://github.com/Deemusc/ot-harjoitustyo/blob/master/Shot_chart/dokumentaatio/kuvat/sekvenssikaavio_kayttajan_luonti.png)
