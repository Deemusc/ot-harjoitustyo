package shotchart.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShotTest {

    Shot s1;
    Shot s2;    

    @Before
    public void setUp() {
        s1 = new Shot(100, 94, "M");
        s2 = new Shot(555, 871, "G");
    }

    @Test
    public void getXReturnsCorrectValue() {
        assertEquals(s1.getX(), 100);
        assertEquals(s2.getX(), 555);
    }

    @Test
    public void getYReturnsCorrectValue() {
        assertEquals(s1.getY(), 94);
        assertEquals(s2.getY(), 871);
    }

    @Test
    public void getTypeReturnsCorrectValue() {
        assertEquals(s1.getType(), "M");
        assertEquals(s2.getType(), "G");
    }

}
