package shotchart.domain;

// @deemus
import java.util.ArrayList;
import java.util.Objects;

/**
 * Yksittäistä laukaisukarttaa kuvaava luokka.
 */
public class ShotChart {

    private int id;
    private String date;
    private String opponent;
    private User user;
    private ArrayList<Shot> shots;

    // konstruktori laukaisukarttaoliolle, joka haetaan ja muodostetaan tiedostosta Daossa.
    public ShotChart(int id, String date, String opponent, User user, ArrayList<Shot> shots) {
        this.id = id;
        this.date = date;
        this.opponent = opponent;
        this.shots = shots;
        this.user = user;
    }

    // Konstruktori laukaisukarttaoliolle, joka luodaan tyhjästä.
    public ShotChart(String date, String opponent, User user) {
        this.user = user;
        this.date = date;
        this.opponent = opponent;
        this.shots = new ArrayList<>();
    }

    /**
     * Laukaisukartan laukaukset merkkijonomuotoon muuttava metodi.
     * Merkkijonomuodossa ne voidaan tallentaa tiedostoon.
     *
     * @return Merkkijono, jossa laukaukset muodossa
     * 'tyyppi';'x-koord.';'y-koord.'
     */
    public String getShotsAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shots.size(); i++) {
            sb.append(";").append(shots.get(i).getType()).append(";").append(shots.get(i).getX()).append(";").append(shots.get(i).getY());
        }
        return sb.toString();
    }

    /**
     * Laukauksen lisääminen laukaisukartalle.
     *
     * @param x laukauksen x-koordinaatti
     * @param y laukauksen y-koordinaatti
     * @param type laukauksen tyyppi (maali, blokki, ohilaukaus)
     */
    public void addShot(int x, int y, String type) {
        this.shots.add(new Shot(x, y, type));
    }

    /**
     * Laukauksen poistaminen laukaisukartalta. Käytetään kumittaessa laukausta
     * laukasten syöttöikkunassa. Metodi poistaa yhden laukauksen 10x10
     * koordinaatin kokoiselta alueelta kumituksen hiirenklikkauksesta.
     *
     * @param x kumituksen hiirenklikkauksen x-koordinaatti
     * @param y kumituksen hiirenklikkauksen y-koordinaatti
     * @return Sen laukauksen, joka poistetaan laukaisukartalta.
     */
    public Shot deleteShot(int x, int y) {
        Shot returnable = null;
        int index = -1;
        for (int i = 0; i < shots.size(); i++) {
            if ((shots.get(i).getX() > x - 10 && shots.get(i).getX() < x + 10) && (shots.get(i).getY() > y - 10 && shots.get(i).getY() < y + 10)) {
                index = i;
            }
        }
        if (index != -1) {
            returnable = shots.get(index);
            shots.remove(index);
        }
        return returnable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public ArrayList getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Hajautusarvon luominen.
     *
     * @return Hajautusarvon laukaisukartta-oliolle.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.opponent);
        hash = 53 * hash + Objects.hashCode(this.user);
        return hash;
    }

    /**
     * Vertailumetodi, kertoo onko laukaisukartta sama kuin vertailtava
     * laukaisukartta.
     *
     * @param obj Vertailtava laukaisukartta.
     * @return False, jos vertailtava olio on null tai jos sen luokka, tunnus,
     * päiväys tai vastustaja on eri kuin laukaisukartan. Muuten True.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShotChart other = (ShotChart) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.opponent, other.opponent);
    }
}
