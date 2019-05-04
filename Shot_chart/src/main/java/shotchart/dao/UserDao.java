package shotchart.dao;

// @deemus
/**
 * Rajapinta, jonka avulla huolehditaan käyttäjien pysyväistallennuksesta.
 */
import java.util.List;
import shotchart.domain.User;

public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}
