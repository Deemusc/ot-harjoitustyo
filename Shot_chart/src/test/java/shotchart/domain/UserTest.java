package shotchart.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("testaaja", "testi123");
        User u2 = new User("testaaja", "testi123");
        assertTrue(u1.equals(u2));
    }

    @Test
    public void notEqualWhenDifferentUsername() {
        User u1 = new User("testaaja", "testi123");
        User u2 = new User("zorro", "lorenzo");
        assertFalse(u1.equals(u2));
    }

    @Test
    public void notEqualWhenDifferentType() {
        User u = new User("testaaja", "testi123");
        Object o = new Object();
        assertFalse(u.equals(o));
    }

    @Test
    public void getUsernameReturnsCorrectName() {
        User u = new User("Jokerit", "ifkizuks");
        assertEquals(u.getUsername(), "Jokerit");
    }

    @Test
    public void getPasswordReturnsCorrect() {
        User u = new User("Joksut", "rapu123");
        assertEquals(u.getPassword(), "rapu123");
    }

}
