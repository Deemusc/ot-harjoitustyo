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
import shotchart.domain.FakeUserDao;
import shotchart.domain.ShotChart;
import shotchart.domain.User;

public class FileShotChartDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File scFile;
    ShotChartDao scDao;

    @Before
    public void setUp() throws Exception {
        scFile = testFolder.newFile("testfile_scs.txt");

        UserDao userDao = new FakeUserDao();
        userDao.create(new User("hoocee", "hc12"));

        try (FileWriter file = new FileWriter(scFile.getAbsolutePath())) {
            file.write("1;2019-01-01;IFK;hoocee;G;100;99\n");
        }

        scDao = new FileShotChartDao(scFile.getAbsolutePath(), userDao);
    }

    @Test
    public void shotChartsAreReadCorrectlyFromFile() {
        ArrayList<ShotChart> shotcharts = scDao.getAll();
        assertEquals(1, shotcharts.size());
        ShotChart shotchart = shotcharts.get(0);
        assertEquals("2019-01-01", shotchart.getDate());
        assertEquals("IFK", shotchart.getOpponent());
        assertEquals(1, shotchart.getId());
        assertEquals("hoocee", shotchart.getUser().getUsername());
    }

    @Test
    public void createdShotChartsAreListed() throws Exception {
        scDao.create(new ShotChart("2019-04-04", "KyrPa", new User("pallokerho", "passu")));

        List<ShotChart> shotcharts = scDao.getAll();
        assertEquals(2, shotcharts.size());
        ShotChart shotchart = shotcharts.get(1);
        assertEquals("2019-04-04", shotchart.getDate());
        assertEquals("KyrPa", shotchart.getOpponent());
        assertNotEquals(1, shotchart.getId());
        assertEquals("pallokerho", shotchart.getUser().getUsername());
    }

    @Test
    public void getChartReturnsCorrectChart() throws Exception {
        ShotChart lukko = scDao.create(new ShotChart("2019-05-05", "Lukko", new User("hpk", "ritari")));
        assertEquals(lukko, scDao.getChart(2));
        assertEquals(null, scDao.getChart(10));
    }

    @Test
    public void updatingChartUpdatesCorrectly() throws Exception {
        ShotChart sc = scDao.getChart(1);
        sc.setOpponent("tappara");
        scDao.update(sc);
        assertEquals("tappara", scDao.getChart(1).getOpponent());
        ShotChart fakeSc = new ShotChart("2000-09-09", "Jokerit", new User("jesse", "tauno"));
        scDao.update(fakeSc);
        assertEquals(1, scDao.getAll().size());
    }

    @Test
    public void deletingChartRemovesChart() throws Exception {
        ShotChart tps = scDao.create(new ShotChart("2018-10-04", "TPS", new User("pori", "pata")));
        ArrayList<ShotChart> scs = scDao.getAll();
        scDao.delete(scs.get(0));
        assertEquals(1, scs.size());
        assertEquals(tps, scs.get(0));
    }

    @After
    public void tearDown() {
        scFile.delete();
    }
}
