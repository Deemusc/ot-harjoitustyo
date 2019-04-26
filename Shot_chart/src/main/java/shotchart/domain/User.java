package shotchart.domain;

/**
 * Sovelluksen käyttäjää kuvaava luokka.
 *
 */
public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Vertailumetodi, kertoo onko käyttäjä sama kuin vertailtava käyttäjä.
     *
     * @param obj Vertailtava käyttäjä-olio.
     * @return True, jos käyttäjien käyttäjänimet ovat samat, muuten false.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return username.equals(other.username);
    }
}
