package shotchart.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShotChartAppTest {

    FakeShotChartDao shotChartDao;
    FakeUserDao userDao;
    ShotChartApp app;

    @Before
    public void setUp() {
        shotChartDao = new FakeShotChartDao();
        userDao = new FakeUserDao();
        User u1 = new User("botnia", "antti123");
        User u2 = new User("klubi", "nakkimuki");
        userDao.create(u1);
        userDao.create(u2);
        shotChartDao.create(new ShotChart("2019-02-02", "Veteli", new User("botnia", "antti123")));
        app = new ShotChartApp(shotChartDao, userDao);
        //app.login("botnia", "antti123");
    }

    @Test
    public void nonExistingUserCantLogIn() {
        boolean result = app.login("tappara", "ojanen");
        assertFalse(result);

        assertEquals(null, app.getLoggedUser());
    }

    @Test
    public void existingUserCanLogIn() {
        boolean result = app.login("botnia", "antti123");
        assertTrue(result);

        User loggedIn = app.getLoggedUser();
        assertEquals("botnia", loggedIn.getUsername());
    }

    @Test
    public void loggedInUserCanLogout() {
        app.login("botnia", "antti123");
        app.logout();

        assertEquals(null, app.getLoggedUser());
    }

    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = app.createUser("botnia", "antti321");
        assertFalse(result);
    }

    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = app.createUser("huuhkajat", "litti");
        assertTrue(result);

        boolean loginOk = app.login("huuhkajat", "litti");
        assertTrue(loginOk);

        User loggedIn = app.getLoggedUser();
        assertEquals("huuhkajat", loggedIn.getUsername());
    }
}
