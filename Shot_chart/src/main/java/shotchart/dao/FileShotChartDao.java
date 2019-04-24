package shotchart.dao;

// @deemus
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import shotchart.domain.Shot;
import shotchart.domain.ShotChart;
import shotchart.domain.User;

public class FileShotChartDao implements ShotChartDao {

    public ArrayList<ShotChart> shotCharts;
    private String file;

    // Haetaan tiedostosta laukaisukarttojen tiedot ja luodaan niistä lista laukaisukartoista
    public FileShotChartDao(String file, UserDao users) throws Exception {
        shotCharts = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                String date = parts[1];
                String opponent = parts[2];
                User user = users.getAll().stream().filter(u -> u.getUsername().equals(parts[3])).findFirst().orElse(null);
                ArrayList<Shot> shots = new ArrayList<>();
                for (int i = 4; i < parts.length; i += 3) {
                    shots.add(new Shot(Integer.parseInt(parts[i + 1]), Integer.parseInt(parts[i + 2]), parts[i]));
                }
                ShotChart shotChart = new ShotChart(id, date, opponent, user, shots);
                shotCharts.add(shotChart);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));            
            writer.close();
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (ShotChart shotChart : shotCharts) {
                writer.write(shotChart.getId() + ";" + shotChart.getDate() + ";" + shotChart.getOpponent() + ";" + shotChart.getUser().getUsername()
                        + shotChart.getShotsAsString() + "\n");
            }
        }
    }

    private int generateId() {
        return shotCharts.size() + 1;
    }

    @Override
    public void delete(ShotChart shotChart) throws Exception {
        int index = -1;
        for (int i = 0; i < shotCharts.size(); i++) {
            if (shotCharts.get(i).getId() == shotChart.getId()) {
                index = i;
            }
        }
        if (index != -1) {
            shotCharts.remove(index);            
            save();
        }
    }

    @Override
    public ShotChart getChart(int id) throws Exception {
        for (int i = 0; i < shotCharts.size(); i++) {
            if (shotCharts.get(i).getId() == id) {
                return shotCharts.get(i);
            }
        }
        return null;
    }
    
    @Override
    public ShotChart update(ShotChart shotChart) throws Exception {
        int index = -1;
        for (int i = 0; i < shotCharts.size(); i++) {
            if (shotCharts.get(i).getId() == shotChart.getId()) {
                index = i;
            }
        }
        if (index != -1) {
            shotCharts.remove(index);
            shotCharts.add(shotChart);
            save();
        }
        return shotChart;
    }

    @Override
    public ShotChart create(ShotChart shotChart) throws Exception {
        shotChart.setId(generateId());
        shotCharts.add(shotChart);
        save();
        return shotChart;
    }

    @Override
    public ArrayList<ShotChart> getAll() {
        return shotCharts;
    }

}
