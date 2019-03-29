package shotchart.domain;

// @deemusc
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

    public User getLoggedUser() {
        return loggedIn;
    }

    public void logout() {
        loggedIn = null;
    }

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

}
