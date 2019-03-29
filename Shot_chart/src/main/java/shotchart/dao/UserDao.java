package shotchart.dao;

// @deemus

import java.util.List;
import shotchart.domain.User;


public interface UserDao {
    
    User create(User user) throws Exception;
    
    User findByUsername(String username);
    
    List<User> getAll();
    
}
