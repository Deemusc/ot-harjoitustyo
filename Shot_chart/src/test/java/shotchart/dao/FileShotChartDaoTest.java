
package shotchart.dao;

import java.io.File;
import java.io.FileWriter;
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
  
    File userFile;  
    ShotChartDao dao;    
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        UserDao userDao = new FakeUserDao();
        userDao.create(new User("botnia", "antti123"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;2019-02-01;tps;botnia\n");
        }
        
        dao = new FileShotChartDao(userFile.getAbsolutePath(), userDao);        
    }
   
    @Test
    public void shotChartsAreReadCorrectlyFromFile() {
        List<ShotChart> shotcharts = dao.getAll();
        assertEquals(1, shotcharts.size());
        ShotChart shotchart = shotcharts.get(0);
        assertEquals("2019-02-01", shotchart.getDate());
        assertEquals("tps", shotchart.getOpponent());
        assertEquals(1, shotchart.getId());
        assertEquals("botnia", shotchart.getUser().getUsername());
    }              
    
    @Test
    public void createdShotChartsAreListed() throws Exception {    
        dao.create(new ShotChart("2019-04-04", "KyrPa", new User("botnia", "antti123")));
        
        List<ShotChart> shotcharts = dao.getAll();
        assertEquals(2, shotcharts.size());
        ShotChart shotchart = shotcharts.get(1);
        assertEquals("2019-04-04", shotchart.getDate());
        assertEquals("KyrPa", shotchart.getOpponent());
        assertNotEquals(1, shotchart.getId());
        assertEquals("botnia", shotchart.getUser().getUsername());
    }     
    
    @After
    public void tearDown() {
        userFile.delete();
    }   
}