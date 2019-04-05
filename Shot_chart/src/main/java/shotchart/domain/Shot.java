package shotchart.domain;

//@deemus
// Yksittäistä laukausta kuvaava luokka
public class Shot {

    private int x;
    private int y;
    private String type;

    public Shot(int y, int x, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
