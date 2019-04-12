package shotchart.domain;

// @deemusc
import java.util.ArrayList;
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
    private ShotChart shotChart;

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

    // Uuden pelin luominen.
    public boolean createNewGame(String date, String opponent) {
        // luodaan uusi peli-olio, jolle annetaan date, vastustaja ja käyttäjä       
        ShotChart sc = new ShotChart(date, opponent, loggedIn);
        try {
            shotChart = shotChartDao.create(sc);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void addShot(int x, int y, String type) {
        shotChart.addShot(x, y, type);
        //System.out.println(shotChart.getShotsAsString());
    }

    public String[][] drawShots() {
        return shotChart.shotsToDraw();              
    }
    
    public boolean saveGame() {
        try {
            shotChart = shotChartDao.update(this.shotChart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
