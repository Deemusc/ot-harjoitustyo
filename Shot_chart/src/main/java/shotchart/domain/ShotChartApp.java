package shotchart.domain;

// @deemus
import java.util.ArrayList;
import java.util.List;
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
        // luodaan uusi peli-olio, jolle annetaan päiväys, vastustaja ja käyttäjä       
        ShotChart sc = new ShotChart(date, opponent, loggedIn);
        try {
            shotChart = shotChartDao.create(sc);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String[][] getShots(int id) {
        try {
            this.shotChart = shotChartDao.getChart(id);
        } catch (Exception e) {

        }
        String[][] shots = new String[600][950];
        for (int x = 0; x < 600; x++) {
            for (int y = 0; y < 950; y++) {
                shots[x][y] = "";
            }
        }

        ArrayList<Shot> shotsList = shotChart.getShots();
        for (int i = 0; i < shotsList.size(); i++) {
            shots[shotsList.get(i).getX()][shotsList.get(i).getY()] = shotsList.get(i).getType();
        }
        return shots;
    }

    public void addShot(int x, int y, String type) {
        shotChart.addShot(x, y, type);
    }
    
    public Shot deleteShot(int x, int y) {
        return shotChart.deleteShot(x, y);
    }
    

    // haetaan ensin haluttu laukaisukartta id:n perusteella ja poistetaan sitten
    public boolean deleteGameById(int id) {
        try {
            this.shotChart = shotChartDao.getChart(id);
        } catch (Exception e) {
            return false;
        }
        return deleteGame();
    }

    public boolean deleteGame() {
        try {
            shotChartDao.delete(this.shotChart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean saveGame() {
        try {
            shotChart = shotChartDao.update(this.shotChart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // metodi, joka palauttaa kaikki kirjautuneena olevan käyttäjän laukaisukartat
    public ArrayList<ShotChart> getShotCharts() {
        ArrayList<ShotChart> charts = new ArrayList<>();
        ArrayList<ShotChart> allCharts = shotChartDao.getAll();

        for (int i = 0; i < allCharts.size(); i++) {
            if (allCharts.get(i).getUser().getUsername().equals(loggedIn.getUsername())) {
                charts.add(allCharts.get(i));
            }
        }

        return charts;
    }

}
