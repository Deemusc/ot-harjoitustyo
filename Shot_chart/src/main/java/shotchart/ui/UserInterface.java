package shotchart.ui;

// @deemus

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shotchart.dao.FileUserDao;


public class UserInterface extends Application {
    // private ShotChartApp shotChartApp;
    
    private Scene shotChartScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String shotChartFile = properties.getProperty("shotChartFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
        //FileShotChartDao shotChartDao = new FileShotChartDao(shotChartFile, userDao);
        //shotChartApp = new ShotChartApp(shotChartDao, userDao);
    }
            
    @Override
    public void start(Stage stage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
