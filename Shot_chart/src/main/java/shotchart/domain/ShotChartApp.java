package shotchart.domain;

// @deemus
import java.util.ArrayList;
import shotchart.dao.ShotChartDao;
import shotchart.dao.UserDao;

/**
 * Tämä luokka vastaa sovelluksen toimintalogiikasta.
 */
public class ShotChartApp {

    private ShotChartDao shotChartDao;
    private UserDao userDao;
    private User loggedIn;
    private ShotChart shotChart;

    public ShotChartApp(ShotChartDao shotChartDao, UserDao userDao) {
        this.shotChartDao = shotChartDao;
        this.userDao = userDao;
    }

    /**
     * Sisäänkirjautumistoiminto. Asettaa sisäänkirjautuneeksi käyttäjäksi ko.
     * käyttäjän.
     *
     * @param username - käyttäjätunnus
     * @param password - salasana
     * @return True, jos sisäänkirjautuminen onnistuu, muuten false.
     */
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

    /**
     * Metodi sisäänkirjautuneen käyttäjän kysymiseen.
     *
     * @return Käyttäjän (User), joka on kirjautuneena sisään. Voi olla myös
     * null.
     */
    public User getLoggedUser() {
        return loggedIn;
    }

    /**
     * Uloskirjautuminen. Käytännössä sisäänkirjautuneeksi käyttäjäksi asetetaan
     * null.
     */
    public void logout() {
        loggedIn = null;
    }

    /**
     * Uuden käyttäjän luominen.
     *
     * @param username - käyttäjänimi
     * @param password - salasana
     * @return True, jos käyttäjän luonti onnistuu. False, jos käyttäjänimi on
     * jo käytössä.
     */
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

    /**
     * Uuden ottelun luominen.
     *
     * @param date - ottelun päivämäärä
     * @param opponent - ottelun vastustajan nimi
     * @return True, jos uuden ottelun luonti onnistui, muuten false.
     */
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

    /**
     * Tietyn ottelun laukausten asettaminen kentän 'koordinaatistoon'. Luodaan
     * ensin tyhjä 'koordinaatisto', haetaan lista ottelun laukauksista ja
     * merkitään ne taulukkoon.
     *
     * @param id - ottelun tunnus
     * @return Kaksiuloitteisen taulukon, jossa tieto kunkin kohdan
     * laukauksesta.
     */
    public String[][] getShots(int id) {
        try {
            this.shotChart = shotChartDao.getChart(id);
        } catch (Exception e) {
            return new String[600][950];
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

    /**
     * Lisää laukauksen laukaisukartalle.
     *
     * @param x - laukauksen x-koordinaatti
     * @param y - laukauksen y-koordinaatti
     * @param type - laukauksen tyyppi, eli maali, blokki tai ohilaukaus
     */
    public void addShot(int x, int y, String type) {
        shotChart.addShot(x, y, type);
    }

    /**
     * Poistaa laukauksen laukaisukartalta.
     *
     * @param x - laukauksen x-koordinaatti
     * @param y - laukauksen y-koordinaatti
     * @see shotchart.domain.ShotChart#deleteShot(int, int)
     * @return Sen laukaus-olion, joka poistettiin.
     */
    public Shot deleteShot(int x, int y) {
        return shotChart.deleteShot(x, y);
    }

    /**
     * Poistetaan tietty ottelu sen tunnuksen perusteella. Hyödynnetään metodia
     * deleteGame()
     *
     * @param id - poistettavan ottelun tunnus
     * @see shotchart.domain.ShotChartApp#deleteGame()
     * @return True, jos ottelu löytyy ja sen poistaminen onnistuu, muuten
     * false.
     */
    public boolean deleteGameById(int id) {
        try {
            this.shotChart = shotChartDao.getChart(id);
        } catch (Exception e) {
            return false;
        }
        return deleteGame();
    }

    /**
     * Ottelun poistaminen.
     *
     * @see shotchart.dao.ShotChartDao#delete(shotchart.domain.ShotChart) 
     * @return True, jos ottelun poistaminen onnistui, muuten false.
     */
    public boolean deleteGame() {
        try {
            shotChartDao.delete(this.shotChart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Ottelun tietojen tallentaminen
     *
     * @see shotchart.dao.ShotChartDao#update(shotchart.domain.ShotChart)
     * @return True, jos ottelun tallentaminen onnistui, muuten false.
     */
    public boolean saveGame() {
        try {
            shotChart = shotChartDao.update(this.shotChart);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Hakee kaikki kirjautuneena olevan käyttäjän laukaisukartat.
     *
     * @see shotchart.dao.ShotChartDao#getAll() 
     * @return Listan, jossa on kaikki kirjautuneen käyttäjän laukaisukartat.
     */
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
