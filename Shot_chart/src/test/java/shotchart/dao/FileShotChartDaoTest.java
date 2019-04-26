package shotchart.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import shotchart.domain.FakeShotChartDao;
import shotchart.domain.FakeUserDao;
import shotchart.domain.ShotChart;
import shotchart.domain.User;

public class FileShotChartDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File userFile;
    File shotChartFile;
    ShotChartDao scDao;

    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");
        shotChartFile = testFolder.newFile("testfile_shotcharts.txt");
        UserDao userDao = new FakeUserDao();
        userDao.create(new User("botnia", "antti123"));
        scDao = new FakeShotChartDao();
        scDao.create(new ShotChart("2019-02-01", "tps", new User("botnia", "antti123")));
    }

    @Test
    public void shotChartsAreReadCorrectlyFromFile() {
        List<ShotChart> shotcharts = scDao.getAll();
        assertEquals(1, shotcharts.size());
        ShotChart shotchart = shotcharts.get(0);
        assertEquals("2019-02-01", shotchart.getDate());
        assertEquals("tps", shotchart.getOpponent());
        assertEquals(1, shotchart.getId());
        assertEquals("botnia", shotchart.getUser().getUsername());
    }

    @Test
    public void createdShotChartsAreListed() throws Exception {
        scDao.create(new ShotChart("2019-04-04", "KyrPa", new User("botnia", "antti123")));

        List<ShotChart> shotcharts = scDao.getAll();
        assertEquals(2, shotcharts.size());
        ShotChart shotchart = shotcharts.get(1);
        assertEquals("2019-04-04", shotchart.getDate());
        assertEquals("KyrPa", shotchart.getOpponent());
        assertNotEquals(1, shotchart.getId());
        assertEquals("botnia", shotchart.getUser().getUsername());
    }

    @Test
    public void getChartReturnsCorrectChart() throws Exception {
        ShotChart lukko = scDao.create(new ShotChart("2019-04-04", "KyrPa", new User("botnia", "antti123")));
        assertEquals(lukko, scDao.getChart(2));
    }

    @Test
    public void deletingChartRemovesChart() throws Exception {
        ShotChart hpk = scDao.create(new ShotChart("2018-10-04", "HPK", new User("pori", "pata")));
        ArrayList<ShotChart> scs = scDao.getAll();
        scDao.delete(scs.get(0));
        assertEquals(1, scs.size());
        assertEquals(hpk, scs.get(0));
    }

    @After
    public void tearDown() {
        userFile.delete();
    }
}
