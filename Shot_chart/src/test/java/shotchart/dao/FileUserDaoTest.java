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
import shotchart.domain.User;

public class FileUserDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File userFile;
    UserDao dao;

    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");

        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("botnia;antti123\n");
        }

        dao = new FileUserDao(userFile.getAbsolutePath());
    }

    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("botnia", user.getUsername());
        assertEquals("antti123", user.getPassword());
    }

    @Test
    public void existingUserIsFound() {
        User user = dao.findByUsername("botnia");
        assertEquals("botnia", user.getUsername());
        assertEquals("antti123", user.getPassword());
    }

    @Test
    public void nonExistingUserIsFound() {
        User user = dao.findByUsername("klubi");
        assertEquals(null, user);
    }

    @Test
    public void savedUserIsFound() throws Exception {
        User nawUser = new User("klubi", "sonski");
        dao.create(nawUser);

        User user = dao.findByUsername("klubi");
        assertEquals("klubi", user.getUsername());
        assertEquals("sonski", user.getPassword());
    }

    @After
    public void tearDown() {
        userFile.delete();
    }
}
