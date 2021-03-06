package shotchart.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shotchart.domain.User;

/**
 * Käyttäjän DAO.
 */
public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String parts[] = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPassword() + "\n");
            }
        }
    }

    /**
     * Käyttäjän luominen ja tallentaminen tiedostoon.
     *
     * @param user tallennettava käyttäjä-olio.
     * @return tallennettu käyttäjä
     * @throws Exception Virheen käsittely.
     */
    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
    }

    /**
     * Käyttäjän hakeminen käyttäjänimen perusteella.
     *
     * @param username etsittävä käyttäjänimi
     * @return Löydetty käyttäjä, jos käyttäjää ei löydy, null.
     */
    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Kaikkien käyttäjien haku.
     *
     * @return Listan kaikista tiedostosta löytyneistä käyttäjistä.
     */
    @Override
    public List<User> getAll() {
        return users;
    }
}
