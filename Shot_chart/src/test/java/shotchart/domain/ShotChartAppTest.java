package shotchart.domain;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class ShotChartAppTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    FakeShotChartDao shotChartDao;
    FakeUserDao userDao;
    ShotChartApp app;
    File shotChartFile;

    @Before
    public void setUp() throws Exception {
        shotChartFile = testFolder.newFile("testfile_shotcharts.txt");

        shotChartDao = new FakeShotChartDao();
        userDao = new FakeUserDao();
        User u1 = new User("botnia", "antti123");
        User u2 = new User("klubi", "nakkimuki");
        userDao.create(u1);
        userDao.create(u2);
        shotChartDao.create(new ShotChart("2019-02-02", "Veteli", u1));
        app = new ShotChartApp(shotChartDao, userDao);
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

    @Test
    public void createNewGameWorks() {
        app.login("botnia", "antti123");
        boolean createOk = app.createNewGame("2018-05-05", "hpk");
        assertTrue(createOk);
    }

    @Test
    public void savingGameWorks() {
        app.login("botnia", "antti123");
        app.createNewGame("2019-10-11", "MSS");
        app.addShot(100, 150, "G");
        app.addShot(400, 400, "B");
        boolean saveOk = app.saveGame();
        assertTrue(saveOk);

    }

}
