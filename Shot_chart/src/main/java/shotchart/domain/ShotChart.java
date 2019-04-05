package shotchart.domain;

// @deemus
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

// Yksittäistä laukaisukarttaa kuvaava luokka.
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

    public String getShotsAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shots.size(); i++) {
            sb.append(";").append(shots.get(i).getType()).append(";").append(shots.get(i).getX()).append(";").append(shots.get(i).getY());
        }
        return sb.toString();
    }
//    public void addGoal(int x, int y) {
//        // maalin merkki on G 
//        this.shots[x][y] = "G";
//    }
//
//    public void addSavedShot(int x, int y) {
//        // torjutun laukauksen merkki on B
//        this.shots[x][y] = "B";
//    }
//
//    public void addMissedShot(int x, int y) {
//        // ohilaukauksen merkki on M
//        this.shots[x][y] = "M";
//    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.opponent);
        hash = 53 * hash + Objects.hashCode(this.user);
        return hash;
    }

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
