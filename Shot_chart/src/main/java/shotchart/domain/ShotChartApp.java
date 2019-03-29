package shotchart.domain;

// @deemus
import shotchart.dao.ShotChartDao;
import shotchart.dao.UserDao;

public class ShotChartApp {

    private ShotChartDao shotChartDao;
    private UserDao userDao;
    private User loggedIn;

    public ShotChartApp(ShotChart shotChart, UserDao userDao) {
        this.shotChartDao = shotChartDao;
        this.userDao = userDao;
    }

    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
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

    public boolean createUser(String username, String name) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }

        User user = new User(username, name);

        try {
            userDao.create(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
