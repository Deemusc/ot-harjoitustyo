package shotchart.dao;

// @deemus

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import shotchart.domain.ShotChart;
import shotchart.domain.User;


public class FileShotChartDao implements ShotChartDao {
    public List<ShotChart> shotCharts;
    private String file;
    
    public FileShotChartDao(String file, UserDao users) throws Exception {
        shotCharts = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                String date = parts[1];
                String homeTeam = parts[2];
                String awayTeam = parts[3];
                User user = users.getAll().stream().filter(u -> u.getUsername().equals(parts[4])).findFirst().orElse(null);
                int[][] shoots = new int[200][400];
                for (int i = 5; i < parts.length; i += 3) {
                    shoots[i+1][i+2] = i;
                }
                ShotChart shotChart = new ShotChart(id, date, homeTeam, awayTeam, user, shoots);
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
                writer.write(shotChart.getId() + ";" + shotChart.getDate() + ";" + shotChart.getHomeTeam() + ";" + shotChart.getAwayTeam()
                + ";" + shotChart.getUser() + ";" + shotChart.getShootsAsString() + "\n");
            }
        }
    }
    
    private int generateId() {
        return shotCharts.size() + 1;
    }
    
    @Override
    public ShotChart create(ShotChart shotChart) throws Exception {
        shotChart.setId(generateId());
        shotCharts.add(shotChart);
        save();
        return shotChart;
    }

    @Override
    public List<ShotChart> getAll() {
        return shotCharts;
    }
    
}
