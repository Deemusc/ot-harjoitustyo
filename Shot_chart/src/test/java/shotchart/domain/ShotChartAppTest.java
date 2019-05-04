package shotchart.domain;

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

    @Before
    public void setUp() throws Exception {

        shotChartDao = new FakeShotChartDao();
        userDao = new FakeUserDao();
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

    @Test
    public void getShotsReturnsCorrectShots() {
        app.login("botnia", "antti123");
        app.createNewGame("2020-10-11", "Kisa");
        app.addShot(99, 200, "B");
        app.addShot(400, 250, "G");
        app.addShot(55, 55, "M");
        app.deleteShot(95, 203);
        app.saveGame();

        String[][] testShots = app.getShots(1);
        assertEquals("", testShots[99][200]);
        assertEquals("G", testShots[400][250]);
        assertEquals("M", testShots[55][55]);
        assertEquals("", testShots[10][10]);
    }

    @Test
    public void getShotChartsReturnsCorrectList() {
        app.login("botnia", "antti123");
        app.createNewGame("2020-10-11", "Kisa");
        app.createNewGame("2019-10-11", "MSS");
        assertEquals(2, app.getShotCharts().size());
    }

    @Test
    public void deleteGameByIdDeletesCorrectGame() {
        app.login("botnia", "antti123");
        app.createNewGame("2020-10-11", "Kisa");
        app.createNewGame("2019-10-11", "MSS");
        app.deleteGameById(1);
        assertEquals("MSS", app.getShotCharts().get(0).getOpponent());
    }
    
}
