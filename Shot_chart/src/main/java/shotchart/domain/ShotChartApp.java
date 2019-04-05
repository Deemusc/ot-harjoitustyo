package shotchart.domain;

// @deemusc
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import shotchart.dao.ShotChartDao;
import shotchart.dao.UserDao;

// Sovelluslogiikasta vastaava luokka.
public class ShotChartApp {

    private ShotChartDao shotChartDao;
    private UserDao userDao;
    private User loggedIn;

    public ShotChartApp(ShotChartDao shotChartDao, UserDao userDao) {
        this.shotChartDao = shotChartDao;
        this.userDao = userDao;
    }

    // Sisäänkirjautuminen, palauttaa true, jos käyttäjä on olemassa ja salasana on oikein.
    // Asettaa sisäänkirjautuneeksi käyttäjäksi ao. käyttäjän.
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        if (!user.getPassword().equals(password)) {
            return false;
        }

        loggedIn = user;

        return true;
    }

    // Palautetaan sisäänkirjautunut käyttäjä.
    public User getLoggedUser() {
        return loggedIn;
    }

    // Kirjataan käyttäjä ulos, eli 'sisäänkirjautuneeksi käyttäjäksi asetetaan null'.
    public void logout() {
        loggedIn = null;
    }

    // Uuden käyttäjän luominen. Jos käyttäjänimi on jo olemassa, palautetaan false.
    public boolean createUser(String username, String password) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }

        User user = new User(username, password);

        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // Uuden pelin luominen. Piirretään tyhjä kenttä (oma metodi?) ja luodaan uudelle pelille tiedosto,
    // jonne syötetään 'tyhjä' koordinaatisto (oma metodi?). Filu tallennetaan tässä kohtaa.
    
    public void createNewGame() {

    }

}
