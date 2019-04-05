package shotchart.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShotChartTest {

    ShotChart sc;
    ShotChart sc2;
    ShotChart sc3;
    User user;
    Shot shot;
    Shot shot2;

    @Before
    public void setUp() {
        user = new User("hifk", "lasse");
        sc = new ShotChart("2019-04-04", "Jokerit", user);
        sc2 = new ShotChart("2018-01-01", "Klubi", user);
        shot = new Shot(99, 50, "G");
        shot2 = new Shot(299, 554, "M");
    }

    @Test
    public void equalsWhenSameShotChart() {
        sc3 = new ShotChart("2019-04-04", "Jokerit", user);
        assertTrue(sc.equals(sc3));
    }

    @Test
    public void notEqualsWhenDifferentShotChart() {
        assertFalse(sc.equals(sc2));
    }

    @Test
    public void getShotChartAsStringReturnsCorrect() {
        ArrayList<Shot> shots = new ArrayList<>();
        shots.add(shot);
        shots.add(shot2);
        sc.setShots(shots);
        assertEquals(sc.getShotsAsString(), ";G;99;50;M;299;554");
    }

}
